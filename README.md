## Spring demo project

### Description

This demo-service shows working with deferred requests resolving.
There is a database with `Persons` (name, birthday). Client can request all persons who was born in required month. The current month by default.
Client take `taskId` after request. After that, client can check this `taskId` and will take two possible results: `resolved` or `in process`.


### How to use it
First, you need to install `mvn`. Sorry for none dockerized project.
Start service via `./start.sh` in the root of the project.
After that, you can try http-requests written in `./rest.http` file. File is written in IntelliJIDEA format.

### Todo
* tests
* docker