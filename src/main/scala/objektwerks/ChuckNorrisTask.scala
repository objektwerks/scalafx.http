package objektwerks

import java.net.URI
import java.net.http.{HttpClient, HttpRequest}
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS
import java.util.concurrent.Executor

import javafx.{concurrent => jfxc}

import scalafx.concurrent.Task

import scala.util.Try

import ujson.*

class ChuckNorrisTask(client: HttpClient, uri: URI) extends Task( new jfxc.Task[String]:
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

  def parseJson(json: String): String = ujson.read(json)("value").str
)