JavaWebStack
============

Java project boostrapper for common &amp; standard technical stack : 
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
- and some Maven plugins for quality code (PMD, Cobertura, Checkstyle)

Get started
===========

1) Retrieve the sources  
```
$ git clone https://github.com/AgileSpirit/JavaWebStack.git
```
2) Configure the *application-localhost.properties* file in order to set your mailing properties  
```
mail.username=<username>@gmail.com
mail.password=<password>
mail.message.to=<receiver-adress@gmail.com>
```
3) Build the application  
```
$ mvn clean install
```
4) Run the application  

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
- nor in the */target/logs/JavaWebStack.log* file, 
- and you should have received an email entitled "JavaWebStack test"
