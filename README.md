ScalaFX Http App
----------------
>ScalaFx Http app that queries and displays Chuck Norris jokes using JDK http client, virtual threads, ujson and Scala 3.

Launch Json
-----------
>To enable preview features and add modules, create a ./.vscode/launch.json file as follows:
```
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "scala",
      "request": "launch",
      "name": "Chuck Norris App",
      "mainClass": "objektwerks.ChuckNorrisApp",
      "args": [],
      "jvmOptions": ["--enable-preview", "--add-modules=jdk.incubator.concurrent"],
      "env": {}
    }
  ]
}
```
>Then select menu **Run** > **Run Without Debugging**

Run
---
1. sbt clean run

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

License
-------
>Copyright (c) [2021 - 2023] [Objektwerks]

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    * http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
