JavaBackbone
============

Java project boostrapper for common &amp; standard technical stack : 
- Spring
- JPA
- Hibernate
- Spring Data - JPA
- Logback
- Guava
- JodaTime
- etc.

Get started
===========

1) Retrieve the sources
```
$ git clone https://github.com/AgileSpirit/JavaBackbone.git
```
2) Build the application
```
$ mvn clean install
```
3) Run the application

Eclipse IDE integration
=======================

1) Generate the Eclipse project thanks to the Maven Eclipse plugin
```
$ mvn eclipse:eclipse -Dwtpversion=2.0 -DdownloadSources=true -DdownloadJavadocs=true
```
2) Import the project into your Eclipse workspace  
3) Declare a new Tomcat web server instance and add the application artifact  
4) Add the Spring profile Tomcat environment variable
```
spring.profiles.active = [localhost | integration | production]
```
