## Spring demo project

### Description

Service for deferred resolving requests.
We have database with `Persons` (name, birthday). Client can request all persons, who born in required month. The current month by default.
Client will take `taskId` after request. After that, client can check this `taskId` and will take two possible results: `resolved` or `in process`.


### How to use it
First, you need install mvn. Sorry for none dockerized project.
Start project via ./start.sh in the root of project.
After that, you can try http-requests written in ./rest.http file. File written in IntelliJIDEA format.

### Todo
* tests