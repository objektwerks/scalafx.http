package fx

import akka.actor.ActorSystem
import akka.http.scaladsl.unmarshalling.Unmarshal

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest

import com.typesafe.config.ConfigFactory

import javafx.{concurrent => jfxc}

import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._

import scala.concurrent.ExecutionContext
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

import scalafx.application.JFXApp
import scalafx.beans.property.StringProperty
import scalafx.concurrent.Task
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.VBox
import scalafx.scene.web.WebView

class JokeTask(implicit val system: ActorSystem, val dispatcher: ExecutionContext) extends Task(new jfxc.Task[String] {
  implicit lazy val formats = DefaultFormats
  
  override def call(): String = {
    Await.result( getJoke, 10 seconds ) // Using Task and Future together has its limitations.
  }

  def getJoke: Future[String] = {
    val client = Http()
    client.singleRequest( HttpRequest(uri = "http://api.icndb.com/jokes/random/") ).flatMap { response =>
      Unmarshal(response)
        .to[String]
        .map { json => s"<p>${parseJson(json)}</p>" }
        .recover { case error => s"<p>${error.getMessage}</p>" }
    }
  }

  def parseJson(json: String): String = {
    val ast = parse(json)
    (ast \ "value" \ "joke").extract[String]
  }
})

object JokeApp extends JFXApp {
  val conf = ConfigFactory.load("app.conf")
  implicit val system = ActorSystem.create("joke", conf)
  implicit val dispatcher = system.dispatcher

  val webView: WebView = new WebView()

  val jokeProperty = new StringProperty()
  jokeProperty.onChange({
    webView.engine.loadContent(jokeProperty.value)
  })

  val jokeIndicator = new ProgressIndicator {
    prefWidth = 60
    prefHeight = 30
    progress = -1.0
    visible = false
  }

  val jokeButton = new Button {
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
  }

  val toolbar = new ToolBar {
    prefHeight = 40
    content = List(jokeButton, new Separator(), jokeIndicator)
  }

  val webViewPane = new VBox {
    id = "web-view-pane"
    spacing = 3
    padding = Insets(3)
    children = List(webView)
  }

  val contentPane = new VBox {
    maxWidth = 400
    maxHeight = 200
    spacing = 6
    padding = Insets(6)
    children = List(toolbar, webViewPane)
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Chuck Norris Jokes"
    scene = new Scene {
      stylesheets.add("app.css")
      root = contentPane
    }
  }
}