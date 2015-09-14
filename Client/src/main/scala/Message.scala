package main.scala

import akka.actor.ActorRef

/**
 * Created by chunyangshen on 9/7/15.
 */

case class Work(pref: String, num:Int) extends Serializable
case class Register(self: ActorRef) extends Serializable
case class Result(code:String, hash:String) extends Serializable
case object Termi extends Serializable
