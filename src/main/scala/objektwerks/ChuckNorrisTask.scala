package objektwerks

import java.net.http.HttpClient
import java.util.concurrent.Executor
import javafx.{concurrent => jfxc}

import scalafx.concurrent.Task

import ujson.*

class ChuckNorrisTask(executor: Executor) extends Task( new jfxc.Task[String]:
  private val client = HttpClient
                         .newBuilder()
                         .executor(executor)
                         .build()
  override def call(): String = getJoke()

  def getJoke(): String =
    /*
    val client = Http()
    client.singleRequest( HttpRequest(uri = "https://api.chucknorris.io/jokes/random") ).flatMap { response =>
      Unmarshal(response)
        .to[String]
        .map { json =>
          s"<p>${parseJson(json)}</p>"
        }
        .recover { case error =>
          s"<p>${error.getMessage}</p>"
        }
    }
    */
    "TODO!"

  def parseJson(json: String): String =
    val data = ujson.read(json)
    data("value").str
)