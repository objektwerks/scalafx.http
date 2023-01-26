package objektwerks

import com.typesafe.scalalogging.LazyLogging

import java.util.concurrent.Executors

import scalafx.application.JFXApp3

object ChuckNorrisApp extends JFXApp3 with LazyLogging:
  private val executors = Executors.newVirtualThreadPerTaskExecutor()

  override def start(): Unit =
    val view = ChuckNorrisView(executors)
    stage = new JFXApp3.PrimaryStage:
      scene = view.scene
      title = "Chuck Norris Jokes"
      maxWidth = 400
      maxHeight = 200
      icons.add(view.logo)
    logger.info("*** Chuck Norris app started.")


  override def stopApp(): Unit =
    executors.shutdown()
    logger.info("*** Chuck Norris app stopped.")