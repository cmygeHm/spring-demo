## Spring demo project

### Description

Service for deferred resolving requests.
We have database with `Persons` (name, birthday). Client can request all persons, who born in required month. The current month by default.
Client will take `taskId` after request. After that, client can check this `taskId` and will take two possible results: `resolved` or `in process`.
Example requests in rest.http file.

### Todo
* startup script
* tests