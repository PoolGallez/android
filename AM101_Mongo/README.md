# AM101_Mongo

Con questa app vedremo un **CRUD** su MongoDB.

## MongoDB

Per l'installazione: [qui](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/). Per un introduzione a MongoDB: [qui](https://docs.mongodb.com/manual/introduction/). Per far partire il server
```
sudo systemctl start mongodb
```
E quindi
```
genji@thinkpad:~$ sudo systemctl status mongodb
● mongodb.service - High-performance, schema-free document-oriented database
   Loaded: loaded (/etc/systemd/system/mongodb.service; disabled; vendor preset:
   Active: active (running) since Wed 2018-03-07 10:57:49 CET; 4min 33s ago
 Main PID: 20964 (mongod)
   CGroup: /system.slice/mongodb.service
           └─20964 /usr/bin/mongod --quiet --config /etc/mongod.conf

Mar 07 10:57:49 th
```
per fermarlo
```
sudo systemctl stop mongodb
```
Per entrare nella shell di mongo (ignoreremo i warning)
```
mongo
```
dopo il comando
```
use mytest
```
procediamo col tutorial e facciamo un **inserimento multiplo** [qui](https://docs.mongodb.com/manual/tutorial/getting-started/).
```
 db.inventory.find( {} )
{ "_id" : ObjectId("5a9fbd6e5c89f22ed372c21e"), "item" : "journal", "qty" : 25, "status" : "A", "size" : { "h" : 14, "w" : 21, "uom" : "cm" }, "tags" : [ "blank", "red" ] }
{ "_id" : ObjectId("5a9fbd6e5c89f22ed372c21f"), "item" : "notebook", "qty" : 50, "status" : "A", "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "tags" : [ "red", "blank" ] }
{ "_id" : ObjectId("5a9fbd6e5c89f22ed372c220"), "item" : "paper", "qty" : 100, "status" : "D", "size" : { "h" : 8.5, "w" : 11, "uom" : "in" }, "tags" : [ "red", "blank", "plain" ] }
{ "_id" : ObjectId("5a9fbd6e5c89f22ed372c221"), "item" : "planner", "qty" : 75, "status" : "D", "size" : { "h" : 22.85, "w" : 30, "uom" : "cm" }, "tags" : [ "blank", "red" ] }
{ "_id" : ObjectId("5a9fbd6e5c89f22ed372c222"), "item" : "postcard", "qty" : 45, "status" : "A", "size" : { "h" : 10, "w" : 15.25, "uom" : "cm" }, "tags" : [ "blue" ] }
```
La **chiave** viene inserita di default.

## RestHeart

E' una gile e semplice **REST** e **WEB** server, lo scarichiamo, lolanciamo e proviamo a interagire col nostro DB appena creato, vedi [qui](http://restheart.org/quick-start.html).
```
curl http://localhost:8080/mytest/inventory
{"_embedded":[{"_id":{"$oid":"5a9fbd6e5c89f22ed372c222"},"item":"postcard","qty":45.0,"status":"A","size":{"h":10.0,"w":15.25,"uom":"cm"},"tags":["blue"]},{"_id":{"$oid":"5a9fbd6e5c89f22ed372c221"},"item":"planner","qty":75.0,"status":"D","size":{"h":22.85,"w":30.0,"uom":"cm"},"tags":["blank","red"]},{"_id":{"$oid":"5a9fbd6e5c89f22ed372c220"},"item":"paper","qty":100.0,"status":"D","size":{"h":8.5,"w":11.0,"uom":"in"},"tags":["red","blank","plain"]},{"_id":{"$oid":"5a9fbd6e5c89f22ed372c21f"},"item":"notebook","qty":50.0,"status":"A","size":{"h":8.5,"w":11.0,"uom":"in"},"tags":["red","blank"]},{"_id":{"$oid":"5a9fbd6e5c89f22ed372c21e"},"item":"journal","qty":25.0,"status":"A","size":{"h":14.0,"w":21.0,"uom":"cm"},"tags":["blank","red"]}],"_id":"inventory","_returned":5}genji@thinkpad:~$
```
Via browser la cosa è più gradevole.

## Model

Costruiamo il nostro model col tool già sperimentato (questa volta sarà più facile): apportando le modifiche come in sorgente, ed è fatta!
