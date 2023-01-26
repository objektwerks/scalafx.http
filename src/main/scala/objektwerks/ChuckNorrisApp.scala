package objektwerks

import akka.actor.ActorSystem

import com.typesafe.config.ConfigFactory

import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.scene.Scene

import scala.concurrent.ExecutionContext

object ChuckNorrisApp extends JFXApp3:
  val conf = ConfigFactory.load("app.conf")
  given system: ActorSystem = ActorSystem.create("chuck-norris", conf)
  given dispatcher: ExecutionContext = system.dispatcher

  override def start(): Unit =
    val view = ChuckNorrisView()
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = "Chuck Norris Jokes"
      maxWidth = 400
      maxHeight = 200
      icons.add(view.logo)


  override def stopApp(): Unit = println("Joke app stopped.")