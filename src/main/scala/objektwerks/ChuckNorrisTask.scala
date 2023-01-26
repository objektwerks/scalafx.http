package objektwerks

import akka.actor.ActorSystem
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest

import javafx.{concurrent => jfxc}

import scalafx.concurrent.Task

import scala.concurrent.ExecutionContext
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

import ujson.*

class ChuckNorrisTask(using system: ActorSystem, dispatcher: ExecutionContext) extends Task( new jfxc.Task[String]:
  override def call(): String = Await.result( getJoke, 10 seconds )

  def getJoke: Future[String] =
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

  def parseJson(json: String): String =
    val data = ujson.read(json)
    data("value").str
)