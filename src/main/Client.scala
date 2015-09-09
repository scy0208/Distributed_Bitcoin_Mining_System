/**
 * Created by chunyangshen on 9/7/15.
 */

package main

import akka.actor._

import akka.routing.RoundRobinRouter
import com.typesafe.config.ConfigFactory
import org.apache.commons.lang3.RandomStringUtils
import com.roundeights.hasher.Implicits._

import scala.collection.mutable.ArrayBuffer


object Client  {
  def main(args: Array[String]): Unit = {
    var remoteIP = "127.0.0.1"
    val clientSystem = ActorSystem("client", ConfigFactory.load(ConfigFactory.parseString(

      """
   akka {
    actor {
      provider = "akka.remote.RemoteActorRefProvider"
    }
    log-dead-letters = off
  }
                                                                                        """)))

  val remotePath = "akka.tcp://serverSys@" + remoteIP + ":11111/user/server"
    //println(remotePath)

    val remoteServer = clientSystem.actorSelection(remotePath)

    val client = clientSystem.actorOf(Props(classOf[NodeClient], remoteServer), name = "client")
  }
}

class NodeClient(server: ActorSelection) extends Actor {
  var numofzero = 5
  var prestring = "scy0208"
  var workers = ArrayBuffer[ActorRef]()
  server ! Register(self)

  val workerRouter = context.actorOf(
    Props(new Worker(self)).withRouter(RoundRobinRouter(5)), name = "workerRouter")

  override def receive: Receive = {
    case Register(worker: ActorRef) => {
      worker ! Work(prestring, numofzero)
      workers.append(worker)
      println("New worker: " + worker.toString())
    }

    case Result(code: String, hash: String) => {
      sender ! Work(prestring, numofzero)
      println("Receive Bitcoin: %s  With code: %s".format(hash, code))
      server ! Result(code, hash)
    }
    case Termi => {
      workers.foreach(i => i ! Termi)
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
