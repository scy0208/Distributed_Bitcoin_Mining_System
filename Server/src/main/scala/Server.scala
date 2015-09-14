package main.scala

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable.ArrayBuffer



object ServerMain{
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: sbt run  <username> " +
        "<num of zero> ")
      System.exit(-1)
    }

    val name = args(0)
    val numofzero = args(1).toInt
    println("name: %s; numofzero: %s".format(name,numofzero))
    val serverSystem = ActorSystem("serverSys", ConfigFactory.load(ConfigFactory.parseString("""
  akka {
    actor {
      provider = "akka.remote.RemoteActorRefProvider"
    }

    log-dead-letters = off
  }
                                                                                             """)))
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