# demo-for-docler
Demo for Docler company

REST API for backend:

for getting tasks    -> get     /task/{date}
for adding task      -> put     /task/{date} + body {"name": "put your task name here"}
for task completion  -> delete  /task/{date} + body {"name": "put your task name here"}

Backend written in event driven, jpa and h2 in memory database, docker prepared for runtime and additiona docker compose 
for run infractucture locally for example.
