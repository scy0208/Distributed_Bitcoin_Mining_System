/**
 * Created by chunyangshen on 9/7/15.
 */

package main.scala

import java.util.concurrent.TimeUnit

import akka.actor._
import akka.routing.RoundRobinRouter
import com.roundeights.hasher.Implicits._
import com.typesafe.config.ConfigFactory
import org.apache.commons.lang3.RandomStringUtils

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration.Duration


object Client  {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Client Usage: sbt run <server IP> <port> (<time>) ")
      System.exit(-1)
    }
    val remoteIP = args(0)
    val port=args(1)
    var time=0
    if (args.length > 2){
      time =  args(2).toInt
    }

    val clientSystem = ActorSystem("client", ConfigFactory.load(ConfigFactory.parseString(
      """
   akka {
    actor {
      provider = "akka.remote.RemoteActorRefProvider"
    }
        remote {
            enabled-transports = ["akka.remote.netty.tcp"]
            transport = "akka.remote.netty.NettyRemoteTransport"
            netty.tcp {
              hostname = "127.0.0.1"
              port = 0
             }
           }
    log-dead-letters = off
  }
                                                                                        """)))

  val remotePath = "akka.tcp://serverSys@" + remoteIP + ":"+ port +"/user/server"
    //println(remotePath)

    val remoteServer = clientSystem.actorSelection(remotePath)


    val client = clientSystem.actorOf(Props(classOf[NodeClient], remoteServer), name = "client")

    import clientSystem.dispatcher

    if(time!=0){
      clientSystem.scheduler.scheduleOnce(Duration(time, TimeUnit.SECONDS), client, Termi)
    }
  }
}

class NodeClient(server: ActorSelection) extends Actor {
  var numofzero = 5
  var prestring = "scy0208"
  var workers = ArrayBuffer[ActorRef]()
  //server ! Register(self)

  val workerRouter = context.actorOf(
    Props(new Worker(self)).withRouter(RoundRobinRouter(5)), name = "workerRouter")

  override def receive: Receive = {

    case Work(pref: String,num:Int) => {
      this.numofzero=num
      this.prestring=pref
      workers.foreach(i=>i!Work(prestring,numofzero))
      /*
      val slave1 = context.actorOf(Props(classOf[Worker], self), name = "slave1")
      val slave2 = context.actorOf(Props(classOf[Worker], self), name = "slave2")
      val slave3 = context.actorOf(Props(classOf[Worker], self), name = "slave3")
      val slave4 = context.actorOf(Props(classOf[Worker], self), name = "slave4")
      val slave5 = context.actorOf(Props(classOf[Worker], self), name = "slave5")*/
    }

    case Register(worker: ActorRef) => {
     // worker ! Work(prestring, numofzero)
      workers.append(worker)
      println("New worker: " + worker.toString())
      if(workers.length==5) server ! Register(self)
    }

    case Result(code: String, hash: String) => {
      sender ! Work(prestring, numofzero)
      println("Receive Bitcoin: %s  With code: %s".format(hash, code))
      server ! Result(code, hash)
    }
    case Termi => {
      workers.foreach(i => i ! Termi)
      println("This Client terminated")
      context stop self
    }


  }

}


class Worker(client:ActorRef) extends Actor {
  client ! Register(self)
  override def receive: Receive = {
    case Work(pref: String,num:Int)=> {

      var ori_code= ""
      var hash = ""
      val prefix = "0" * num

      while (!hash.startsWith(prefix)) {
        ori_code = pref + context.toString + RandomStringUtils.random(16, true, true)
        hash = ori_code.sha256.hex
      }
      sender ! Result(ori_code, hash)

    }
    case Termi =>{
      println("worker end")
      context.stop(self)
    }
  }
}
