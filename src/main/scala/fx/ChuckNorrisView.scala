package fx

import akka.actor.ActorSystem

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ProgressIndicator, Separator, TextArea, ToolBar}
import scalafx.scene.layout.VBox
import scalafx.scene.image.Image
import scalafx.scene.web.WebView

import scala.concurrent.ExecutionContext

class ChuckNorrisView(using system: ActorSystem, dispatcher: ExecutionContext):
  def logo = new Image(Image.getClass.getResourceAsStream("/cn.jpg"))

  val jokeProperty = ObjectProperty[String]("")
  jokeProperty.onChange { (_, _, newJoke) =>
    jokeWebView.engine.loadContent(newJoke)
  }

  val jokeWebView = new WebView()

  val jokeWebViewPane = new VBox:
    id = "web-view-pane"
    spacing = 3
    padding = Insets(3)
    children = List(jokeWebView)

  val jokeButton = new Button:
    prefWidth = 60
    prefHeight = 30
    text = "Joke"
    onAction = _ => {
      val task = ChuckNorrisTask()
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

  val jokeToolBar = new ToolBar:
    prefHeight = 40
    content = List(jokeButton, new Separator(), jokeIndicator)

  val contentPane = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(jokeToolBar, jokeWebViewPane)

  val scene = new Scene:
    root = contentPane
    stylesheets = List("/app.css")