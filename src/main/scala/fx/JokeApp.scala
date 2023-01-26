package fx

import akka.actor.ActorSystem
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest

import com.typesafe.config.ConfigFactory

import javafx.{concurrent => jfxc}

import scala.concurrent.ExecutionContext
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.beans.property.ObjectProperty
import scalafx.concurrent.Task
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.*
import scalafx.scene.layout.VBox

import ujson.*

class JokeTask(using system: ActorSystem, dispatcher: ExecutionContext) extends Task(new jfxc.Task[String]:
  override def call(): String =
    Await.result( getJoke, 10 seconds )

  def getJoke: Future[String] =
    val client = Http()
    client.singleRequest( HttpRequest(uri = "https://api.chucknorris.io/jokes/random") ).flatMap { response =>
      Unmarshal(response)
        .to[String]
        .map { json => s"${parseJson(json)}" }
        .recover { case error => s"${error.getMessage}" }
    }

  def parseJson(json: String): String = ujson.read(json).str
)

object JokeApp extends JFXApp3:
  val conf = ConfigFactory.load("app.conf")
  given system: ActorSystem = ActorSystem.create("joke", conf)
  given dispatcher: ExecutionContext = system.dispatcher

  val jokeProperty = ObjectProperty[String]("")
  jokeProperty.onChange { (_, _, newJoke) =>
    jokeTextArea.text = newJoke
  }

  val jokeTextArea = new TextArea()

  val jokeTextAreaPane = new VBox:
    spacing = 3
    padding = Insets(3)
    children = List(jokeTextArea)

  val jokeButton = new Button:
    prefWidth = 60
    prefHeight = 30
    text = "Joke"
    onAction = _ => {
      val task = JokeTask()
      jokeProperty <== task.value
      jokeIndicator.visible <== task.running
      this.disable <== task.running
      dispatcher.execute(task)
    }

  val jokeIndicator = new ProgressIndicator:
    prefWidth = 60
    prefHeight = 30
    progress = -1.0
    visible = false

  val toolbar = new ToolBar:
    prefHeight = 40
    content = List(jokeButton, new Separator(), jokeIndicator)

  val contentPane = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(toolbar, jokeTextAreaPane)

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      scene = new Scene {
        root = contentPane
        stylesheets.add("app.css")
      }
      title = "Chuck Norris Jokes"
      maxWidth = 400
      maxHeight = 200

  override def stopApp(): Unit = println("Joke app stopped.")