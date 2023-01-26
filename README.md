ScalaFX Akka-Http App
---------------------
>ScalaFx Akka-Http app that queries and displays Chuck Norris jokes.

Run
---
1. sbt clean compile run

Design
------
>The app uses the Chuck Norris ( https://api.chucknorris.io/jokes/random ) rest service. Of course, the app does
not call this service, Chuck does. The main web site can be found at: http://www.icndb.com/the-jokes-2/

* http://api.icndb.com/jokes/random
```
{ "type": "success",
  "value": { "id": 111,
             "joke": "Chuck Norris has a deep and abiding respect for human life... unless it gets
                      in his way.",
             "categories": []
           }
 }
```
* http://api.icndb.com/jokes/random/3
```
{
  "type": "success",
  "value": [
    {
      "id": 96,
      "joke": "Nobody doesn't like Sara Lee. Except Chuck Norris.",
      "categories": []
    },
    {
      "id": 241,
      "joke": "Chuck Norris puts his pants on one leg at a time, just like the rest of us. The only
               difference is, then he kicks ass.",
      "categories": []
    },
    {
      "id": 373,
      "joke": "Sweating bullets is literally what happens when Chuck Norris gets too hot.",
      "categories": []
    }
  ]
}                                
```