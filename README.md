JRocket
=======

JRocket is a full-stack bootstrapping webapp, based on a Java / Spring for back-end and an AngularJS / Bootstrap for front-end.

Use cases
---------
It is useful if you want to quickly and easily :
- **start a new project** from scratch with common technical features (data persistence, logging, etc.) met in enterprise conditions
- **learn how to use** some common concepts / frameworks / libraries / tools, such as Spring, AngularJS, JPA, Mockito, etc.
- **make some load / perf testing** on a minimum enterprise project
- **test some stuff** like new framework version, new implementation, etc.

Technical stack
---------------
*TODO*

Getting started
===============

Since version 0.5.0, the application is divided in two parts :
- jrocket-api : the server side, exposing a RESTful API (in JSON) and managing busiess logic and data persistence
- jrocket-ui : the client side, with an AngularJS SPA-type application

1) Retrieve the sources
```
$ git clone https://github.com/AgileSpirit/JRocket.git
```
2) Build the application
```
$ mvn clean install
```
This may take a few minutes because default mode sources and javadocs of each dependencies are downloaded by default (see downloadJavadocs and downloadSources properties in pom.xml).

3) Run the API module
```
$ cd jrocket-api
$ mvn jetty:run
```
4) Run the UI module (you must have a working NodeJS / AngularJS environment, cf. above)
```
$ cd ../jrocket-ui
$ grunt serve
```

Configure a SPA workstation
===========================

1. Install [NodeJS](http://nodejs.org/download/ "NodeJS")
2. Install Grunt-cli
```
$ npm install -g grunt-cli
```
3. Install Yeoman
```
$ npm install -g yeoman
```
4. Retrieve Grunt Node modules
```
$ npm install
```
5. Retrieve Bower dependencies
```
$ bower dependencies
```

IDE integration
===============

Eclipse
-------

1) Generate the Eclipse project thanks to the Maven Eclipse plugin  
```
$ mvn eclipse:eclipse
```
2) Import the project into your Eclipse workspace  
3) Declare a new Tomcat web server instance and add the io.jrocket.application artifact
4) Launch the Tomcat instance  

If all is OK :
- there is no ERROR in the log console, 
- nor in the */target/logs/JRocket.log* file

IntelliJ IDEA
-------------
*TODO*

Coding tips
===========

Live testing REST calls
-----------------------

In a REST console (chrome-extension://hgmloofddffdnphfgcellkdfbfbjeloo/RestClient.html) :

**GET**  
URL: http://localhost:8080/JRocket/service/bookmarks

**POST**  
URL: http://localhost:8080/JRocket/service/bookmarks 
RAW (Payload) : 
```
{
  "url" : "http://test.com",
  "title" : "Test",
  "description" : "This is a test bookmark"
}
```

**PUT**  
URL: http://localhost:8080/JRocket/service/bookmarks/10
RAW (Payload) :
```
{
  "id" : "10", 
  "url" : "http://test.com",
  "title" : "Test",
  "description" : "This is a test bookmark",
  "creationDate" : "2014-02-04T02:25:33.803+01:00",
  "modificationDate" : "null"
}
```

Functionnal testing with FitNesse
---------------------------------
You can run FitNesse in edition mode and running functionnal tests with command :
```
mvn test -Pfitnesse
```
Then, go to http://localhost:8000

test jenkins