package fx

import akka.actor.ActorSystem

import scalafx.Includes.*
import scalafx.beans.property.ObjectProperty
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, ProgressIndicator, Separator, TextArea, ToolBar}
import scalafx.scene.layout.VBox

import scala.concurrent.ExecutionContext

class JokeView(using system: ActorSystem, dispatcher: ExecutionContext):
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

  val scene = new Scene:
    root = contentPane
    stylesheets = List("/app.css")