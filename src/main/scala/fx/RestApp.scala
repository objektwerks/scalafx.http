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
import scalafx.concurrent.Task
import scalafx.event.ActionEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.layout.VBox

class JokeTask extends Task(new jfxc.Task[String] {
  override def call(): String = {
    implicit val ec = ExecutionContext.global
    val ws = NingWSClient()
    val request = ws.url("http://api.icndb.com/jokes/random/")
    val response = request.get()
    val result = Await.ready(response, Duration.Inf).value.get
    result match {
      case Success(content) => parseJson(content.body)
      case Failure(failure) => s"The joke is on you: ${failure.getMessage}"
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

  val jokeLabel = new Label {
    id = "joke-label"
    text = "Joke:"
  }

  val jokeText = new TextArea {
    wrapText = true
  }

  val indicator = new ProgressIndicator {
    prefWidth = 50
    progress = -1.0
    visible = false
  }

  val jokeButton = new Button {
    text = "New Joke"
    onAction = (e: ActionEvent) => {
      val task = new JokeTask
      jokeText.text <== task.value
      indicator.visible <== task.running
      this.disable <== task.running
      ec.execute(task)
    }
  }

  val jokePane = new VBox {
    id = "joke-vbox"
    spacing = 6
    padding = Insets(6)
    children = List(jokeLabel, jokeText)
  }

  val toolbar = new ToolBar {
    content = List(jokeButton, new Separator(), indicator)
  }

  val appPane = new VBox {
    maxWidth = 400
    maxHeight = 200
    spacing = 6
    padding = Insets(6)
    children = List(toolbar, jokePane)
  }

  stage = new JFXApp.PrimaryStage {
    title.value = "Chuck Norris Jokes"
    scene = new Scene {
      stylesheets.add("rest.app.css")
      root = appPane
    }
  }
}