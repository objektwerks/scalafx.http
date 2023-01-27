package objektwerks

import java.net.URI
import java.util.concurrent.Executor

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ProgressIndicator, Separator, ToolBar}
import scalafx.scene.layout.VBox
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.web.WebView

class ChuckNorrisView(executor: Executor, uri: URI):
  private def loadImageView(path: String): ImageView = new ImageView:
    image = new Image(Image.getClass.getResourceAsStream(path))
    fitHeight = 25
    fitWidth = 25
    preserveRatio = true
    smooth = true

  def logo = new Image(Image.getClass.getResourceAsStream("/cn.jpg"))

  def logoView = loadImageView("/cn.jpg")

  val webview = new WebView()

  val jokeProperty = ObjectProperty[String]("")
  jokeProperty.onChange { (_, _, newJoke) =>
    webview.engine.loadContent(newJoke)
  }

  val jokeButton = new Button:
    prefWidth = 80
    prefHeight = 30
    text = "New Joke"
    onAction = _ => {
      val task = ChuckNorrisTask(executor, uri)
      jokeProperty <== task.value
      busyIndicator.visible <== task.running
      this.disable <== task.running
      executor.execute(task)
    }

  val busyIndicator = new ProgressIndicator:
    prefWidth = 60
    prefHeight = 30
    progress = -1.0
    visible = false

  val toolbar = new ToolBar:
    prefHeight = 40
    content = List(jokeButton, new Separator(), busyIndicator)

  val webviewPane = new VBox:
    id = "web-view-pane" // see app.css
    spacing = 3
    padding = Insets(3)
    children = List(webview)

  val contentPane = new VBox:
    spacing = 6
    padding = Insets(6)
    children = List(toolbar, webviewPane)

  val scene = new Scene:
    root = contentPane
    stylesheets = List("/app.css")