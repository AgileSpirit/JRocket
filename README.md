JavaWebStack
============

Java project boostrapper for common &amp; standard enterprise technical stack : 
- Spring
- Spring JavaConfig
- Spring profiles
- Spring Scheduler
- Spring MVC
- Spring Data - JPA
- JPA
- Hibernate
- C3P0
- HSQLDB
- H2
- SLF4J
- Logback
- Guava
- JodaTime
- AssertJ
- DBUnit
- Mockito
- JavaMail
- Velocity
- AOP / ApectJ
- Monitoring (AppStatus, Metrics, custom Servlet filtering)
- Front-end client (AngularJS)
- and some Maven plugins for quality code (PMD, Cobertura, Checkstyle)

Getting started
===============

1) Retrieve the sources  
```
$ git clone https://github.com/AgileSpirit/JavaWebStack.git
```
2) Build the application  
```
$ mvn clean install
```
This may take a few minutes because default mode sources and javadocs of each dependencies are downloaded by default (see downloadJavadocs and downloadSources properties in pom.xml).  

3) Deploy and run the application in a Java Server (ex: Tomcat, JBoss, Jetty, etc.)  

Eclipse IDE integration
=======================

1) Generate the Eclipse project thanks to the Maven Eclipse plugin  
```
$ mvn eclipse:eclipse
```
2) Import the project into your Eclipse workspace  
3) Declare a new Tomcat web server instance and add the application artifact  
4) Launch the Tomcat instance  

If all is OK :
- there is no ERROR in the log console, 
- nor in the */target/logs/JavaWebStack.log* file

Functional domain
=================
The business domain covered by the web application concerns the bookmark management. This is actually a pretext to implement an enterprise Java Web application based on modern concepts and tools in a way of state of the art, such as DDD layers decoupling, REST API, testing, mailing, batching, etc.

Software factory and Java dependencies
======================================

We use Maven to manage the building activities (configuration, dependency management, compiling, tests execution, etc.)

The whole Maven configuration is contained in the pom.xml file.

Dependencies
------------

- TODO

Plugins
-------
- maven-compiler-plugin : plugin used to configure JDK version
- maven-eclipse-plugin : Maven Eclipse integration & especially Web Tool Platform (WTP) support
- maven-war-plugin : Plugin used because we do not need no more web.xml (cf. WebAppInitializer)
- maven-site-plugin : Reporting quality plugins (such as PMD, Cobertura, Checkstyle) aggregator


Coding tips
===========

Live testing REST calls
-----------------------

In a REST console (chrome-extension://hgmloofddffdnphfgcellkdfbfbjeloo/RestClient.html) :

*GET*
URL: http://localhost:8080/JavaWebStack/service/bookmarks

*POST*
URL: http://localhost:8080/JavaWebStack/service/bookmarks
RAW (Payload) :
{
  "url" : "http://test.com",
  "title" : "Test",
  "description" : "This is a test bookmark"
}

*PUT*
URL: http://localhost:8080/JavaWebStack/service/bookmarks/10

