package objektwerks

import scalafx.application.JFXApp3

object ChuckNorrisApp extends JFXApp3:
  override def start(): Unit =
    val view = ChuckNorrisView()
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = "Chuck Norris Jokes"
      maxWidth = 400
      maxHeight = 200
      icons.add(view.logo)


  override def stopApp(): Unit = println("Joke app stopped.")