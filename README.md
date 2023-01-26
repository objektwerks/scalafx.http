ScalaFX Http App
----------------
>ScalaFx Http app that queries and displays Chuck Norris jokes using JDK http client, virtual threads and Scala 3.

Run
---
1. sbt clean compile run

Design
------
>This app uses a Chuck Norris ( https://api.chucknorris.io/jokes/random ) rest service. Of course, this
>app does not call this service, Chuck does. The main web site can be found at: https://api.chucknorris.io/

Json
----
>Http target url: https://api.chucknorris.io/jokes/random
```
{
  "categories": [],
  "created_at": "2020-01-05 13:42:19.576875",
  "icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
  "id": "6c5XhyPeTr-yWR47A8jOng",
  "updated_at": "2020-01-05 13:42:19.576875",
  "url": "https://api.chucknorris.io/jokes/6c5XhyPeTr-yWR47A8jOng",
  "value": "Chuck Norris can power-shit a large ball bearing through your skull from half a mile away."
}
```