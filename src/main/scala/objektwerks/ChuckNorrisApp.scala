package objektwerks

import scalafx.application.JFXApp3
import com.typesafe.scalalogging.LazyLogging

object ChuckNorrisApp extends JFXApp3 with LazyLogging:
  override def start(): Unit =
    val view = ChuckNorrisView()
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = "Chuck Norris Jokes"
      maxWidth = 400
      maxHeight = 200
      icons.add(view.logo)
    logger.info("*** Chuck Norris app started.")


  override def stopApp(): Unit = logger.info("*** Chuck Norris app stopped.")