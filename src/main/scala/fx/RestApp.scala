package fx

import javafx.{concurrent => jfxc}

import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.util.{Failure, Success}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.beans.property.StringProperty
import scalafx.concurrent.Task
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.VBox
import scalafx.scene.web.WebView

class JokeTask(val ec: ExecutionContext, val ws: NingWSClient) extends Task(new jfxc.Task[String] {
  override def call(): String = {
    val response = Await.ready(ws.url("http://api.icndb.com/jokes/random/").get, 10 seconds).value.get
    response match {
      case Success(content) => s"<p>${parseJson(content.body)}</p>"
      case Failure(failure) => s"<p>The joke is on you: ${failure.getMessage}</p>"
    }
  }

  private def parseJson(json: String): String = {
    implicit lazy val formats = DefaultFormats
    val ast = parse(json)
    (ast \ "value" \ "joke").extract[String]
  }
})

object RestApp extends JFXApp {
  private val ec = ExecutionContext.global
  private val ws = NingWSClient()

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
    onAction = (e: ActionEvent) => {
      val task = new JokeTask(ec, ws)
      jokeProperty <== task.value
      jokeIndicator.visible <== task.running
      this.disable <== task.running
      ec.execute(task)
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
      stylesheets.add("rest.app.css")
      root = contentPane
    }
  }
}