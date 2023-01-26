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
import scalafx.beans.property.StringProperty
import scalafx.concurrent.Task
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.*
import scalafx.scene.layout.VBox
import scalafx.scene.web.WebView

import ujson.*

class JokeTask(using system: ActorSystem, dispatcher: ExecutionContext) extends Task(new jfxc.Task[String]:
  override def call(): String =
    Await.result( getJoke, 10 seconds )

  def getJoke: Future[String] =
    val client = Http()
    client.singleRequest( HttpRequest(uri = "https://api.chucknorris.io/jokes/random") ).flatMap { response =>
      Unmarshal(response)
        .to[String]
        .map { json => s"<p>${parseJson(json)}</p>" }
        .recover { case error => s"<p>${error.getMessage}</p>" }
    }

  def parseJson(json: String): String = ujson.read(json).str
)

object JokeApp extends JFXApp3:
  val conf = ConfigFactory.load("app.conf")
  given system: ActorSystem = ActorSystem.create("joke", conf)
  given dispatcher: ExecutionContext = system.dispatcher

  override def start(): Unit =
    stage = new JFXApp3.PrimaryStage:
      title = "Chuck Norris Jokes"
      scene = new Scene {
        stylesheets.add("app.css")
        root = contentPane
      }

  override def stopApp(): Unit = println("Joke app stopped.")

  val webView = WebView()

  val jokeProperty = new StringProperty()
  jokeProperty.onChange({
    webView.engine.loadContent(jokeProperty.value)
  })

  val jokeIndicator = new ProgressIndicator:
    prefWidth = 60
    prefHeight = 30
    progress = -1.0
    visible = false

  val jokeButton = new Button:
    prefWidth = 60
    prefHeight = 30
    text = "Joke"
    onAction = _ => {
      val task = new JokeTask()
      jokeProperty <== task.value
      jokeIndicator.visible <== task.running
      this.disable <== task.running
      dispatcher.execute(task)
    }

  val toolbar = new ToolBar:
    prefHeight = 40
    content = List(jokeButton, new Separator(), jokeIndicator)

  val webViewPane = new VBox:
    id = "web-view-pane"
    spacing = 3
    padding = Insets(3)
    children = List(webView)

  val contentPane = new VBox:
    maxWidth = 400
    maxHeight = 200
    spacing = 6
    padding = Insets(6)
    children = List(toolbar, webViewPane)