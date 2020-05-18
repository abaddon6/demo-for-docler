# demo-for-docler
Demo for Docler company

REST API for backend:

for getting tasks    -> get     /task/{date} </br>
for adding task      -> put     /task/{date} + body {"name": "put your task name here"} </br>
for task completion  -> delete  /task/{date} + body {"name": "put your task name here"} </br>
</br>

Backend written in event driven, jpa and h2 in memory database, docker prepared for runtime and additional docker compose created
for run infractucture locally for example or with Docker Enterprise as runtime.
