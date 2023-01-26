package objektwerks

import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS
import java.util.concurrent.Executor

import javafx.{concurrent => jfxc}

import scalafx.concurrent.Task

import scala.util.Try

import ujson.*

class ChuckNorrisTask(executor: Executor, uri: URI) extends Task( new jfxc.Task[String]:
  private val client = HttpClient
                         .newBuilder
                         .executor(executor)
                         .build
  override def call(): String = getJoke()

  def getJoke(): String =
    Try {
      val httpRequest = HttpRequest
        .newBuilder
        .uri(uri)
        .timeout(Duration.of(30, SECONDS))
        .version(HttpClient.Version.HTTP_2)
        .GET()
        .build
      
      val json = client
        .send( httpRequest, BodyHandlers.ofString )
        .body
      
      s"<p>${parseJson(json)}</p>"
    }.recover { case error: Exception => s"<p>${error.getMessage}</p>" }
     .get

  def parseJson(json: String): String =
    val data = ujson.read(json)
    data("value").str
)