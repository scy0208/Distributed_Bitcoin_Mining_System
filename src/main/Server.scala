package main

import akka.actor.{Props, ActorSystem, ActorRef, Actor}
import akka.actor.Actor.Receive
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.ArrayBuffer



object ServerMain{
  def main(args: Array[String]): Unit = {
    val numofzero = 4
    val name = "scy0208"
    val serverSystem = ActorSystem("serverSys", ConfigFactory.load(ConfigFactory.parseString(
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
        port = 11111
      }
    }
    log-dead-letters = off
  }
                                                                                           """)))
  import serverSystem.dispatcher
    val server = serverSystem.actorOf(Props(classOf[Server], name, numofzero), name = "server")
  }
}

class Server(prestring: String, numofzero: Int ) extends Actor{
  var clients = ArrayBuffer[ActorRef]()


  override def receive: Receive = {
    case Register(client:ActorRef)=>{
      client!Work(prestring,numofzero)
      clients.append(client)
      println{"New connected node: "+client.toString}


    }
    case Result(code:String, hash:String)=>{
      sender!Work(prestring,numofzero)
      println("Receive Bitcoin: %s  With code: %s".format(hash,code))
    }

    case Termi=>{
      clients.foreach(i=>i!Termi)
      println("Server Terminate")
      context.stop(self)
    }

  }
}