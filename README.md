<<<<<<< HEAD
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

<table>
  <thead>
    <tr><td>Dependency</td><td>Version</td><td>Description</td></tr>  
  </thead>
  <tbody>
    <tr><td>junit</td><td>4.11</td><td></td></tr>
    <tr><td>javax.inject</td><td>1</td><td></td></tr>
    <tr><td>javax.servlet-api</td><td>3.1.0</td><td></td></tr>
    <tr><td>spring-core</td><td>3.2.4.RELEASE</td><td>Java beans container &amp; web support</td></tr>
    <tr><td>spring-context</td><td>3.2.4.RELEASE</td><td>Java beans container &amp; web support</td></tr>
    <tr><td>spring-beans</td><td>3.2.4.RELEASE</td><td>Java beans container &amp; web support</td></tr>
    <tr><td>spring-web</td><td>3.2.4.RELEASE</td><td>Java beans container &amp; web support</td></tr>
    <tr><td>spring-context-support</td><td>3.2.4.RELEASE</td><td>Java beans container &amp; web support</td></tr>
    <tr><td>spring-data-jpa</td><td>1.3.4.RELEASE</td><td>repository services provider/generator</td></tr>
    <tr><td>spring-jdbc</td><td>3.2.4.RELEASE</td><td>transaction management &amp; ORM support</td></tr>
    <tr><td>spring-tx</td><td>3.2.4.RELEASE</td><td>transaction management &amp; ORM support</td></tr>
    <tr><td>spring-orm</td><td>3.2.4.RELEASE</td><td>transaction management &amp; ORM support</td></tr>
    <tr><td>hibernate-entitymanager</td><td>4.2.4.Final</td><td>bundle of all necessary Hibernate modules (Core, Annotations, EntityManager) for JPA support</td></tr>
    <tr><td>hibernate-jpa-2.0-api</td><td>1.0.1.Final</td><td>Hibernate support for JPA</td></tr>
    <tr><td>hibernate-c3p0</td><td>4.2.4.Final</td><td>JDBC connection pooling & Hibernate support</td></tr>
    <tr><td>spring-webmvc</td><td>3.2.4.RELEASE</td><td></td></tr>
    <tr><td>jackson-core</td><td>2.2.2</td><td>used by Spring to convert Java POJOs to JSON strings</td></tr>
    <tr><td>jackson-databind</td><td>2.2.2</td><td>Just the annotations; use this dependency if you want to attach annotations to classes without connecting them to the code</td></tr>
    <tr><td>hsqldb</td><td>2.3.0</td><td>HSQLDB driver</td></tr>
    <tr><td>logback-classic</td><td>1.0.13</td><td>Java logger, substitute of Log4J (embeds SLF4J abstract wrapper)</td></tr>
    <tr><td>joda-time</td><td>2.3</td><td>enhanced Date types</td></tr>
    <tr><td>usertype.core</td><td>3.0.0.GA</td><td>joda-time DateTime support for Hibernate</td></tr>
    <tr><td>guava</td><td>12.0</td><td>useful Java tools &amp; utils</td></tr>
    <tr><td>commons-beanutils</td><td>20030211.134440</td><td>used for improved Java Reflection abilities</td></tr>
    <tr><td>aspectjrt</td><td>1.7.3</td><td>AOP support through Spring</td></tr>
    <tr><td>aspectjtools</td><td>1.7.3</td><td>AOP support through Spring</td></tr>
    <tr><td>metrics-core</td><td>3.0.1</td><td></td></tr>
    <tr><td>metrics-servlets</td><td>3.0.1</td><td></td></tr>
    <tr><td>metrics-spring</td><td>3.0.0-RC2</td><td></td></tr>
    <tr><td>httpclient</td><td>4.2.6</td><td></td></tr>
    <tr><td>fluent-hc</td><td>4.2.6</td><td></td></tr>
    <tr><td>spring-test</td><td>3.2.4.RELEASE</td><td></td></tr>
    <tr><td>assertj-core</td><td>1.3.0</td><td></td></tr>
    <tr><td>dbunit</td><td>2.4.9</td><td>database testing</td></tr>
    <tr><td>spring-test-dbunit</td><td>1.0.1</td><td>Spring support for DBUnit</td></tr>
    <tr><td>h2</td><td>1.3.173</td><td>lightweight in-memory database for testing</td></tr>
    <tr><td>mockito-all</td><td>1.9.5</td><td>mocking framework for testing</td></tr>
    <tr><td>mail</td><td>1.4.7</td><td></td></tr>
    <tr><td>velocity</td><td>1.7</td><td></td></tr>
  </tbody>
</table>

Plugins
-------
- maven-compiler-plugin : plugin used to configure JDK version
- maven-eclipse-plugin : Maven Eclipse integration & especially Web Tool Platform (WTP) support
- maven-war-plugin : Plugin used because we do not need no more web.xml (cf. WebAppInitializer)
- maven-site-plugin : Reporting quality plugins (such as PMD, Cobertura, Checkstyle) aggregator
=======
# Welcome to Tomcat7 on CloudBees

This is a "ClickStart" that gets you going with a Maven - Tomcat 7 "seed" project starting point. You can launch it here:

<a href="https://grandcentral.cloudbees.com/?CB_clickstart=https://raw.github.com/CloudBees-community/tomcat7-maven-clickstart/master/clickstart.json"><img src="https://d3ko533tu1ozfq.cloudfront.net/clickstart/deployInstantly.png"/></a>

This will setup a continuous deployment pipeline - a CloudBees Git repository, a Jenkins build compiling and running the test suite (on each commit).
Should the build succeed, this seed app is deployed on a Tomcat 7 container.

# CloudBees Tomcat 7 container

Tomcat 7 container is available on CloudBees thanks to the [tomcat7-clickstack](https://github.com/CloudBees-community/tomcat7-clickstack). Documentation is available [here](https://developer.cloudbees.com/bin/view/RUN/Tomcat7).

# How to deploy a web application on a Tomcat7 ClickStack

You can deploy your web application on the tomcat7 clickstack using the [CloudBees SDK](https://developer.cloudbees.com/bin/view/RUN/BeesSDK) "`app:deploy`" command.

```
bees app:deploy -a myapp -t tomcat7 ./target/tomcat7-maven-clickstart-1.0-SNAPSHOT.war
```

* "`-a myapp`": name of the CloudBees account and of the application. The application will be accessible on the URL http://tomcat7-maven-clickstart.cyrille-leclerc.cloudbees.net/
* "`-t tomcat7`": identifier of the tomcat7 clickstack
* "`./target/tomcat7-maven-clickstart-1.0-SNAPSHOT.war`": path to the war file.
You only need to set the "`-R`", "`-t`" and "`-D`" settings once - they will be remembered for subsequent deploys.

# How to bind a CloudBees MySql database to an application on a Tomcat7 ClickStack

## Create database if needed
```
db:create --username my-username --password alpha-beta mydb
```

## Bind application to database

```
bees app:bind -a  myapp -db mydb -as mydb
```
* "`-a  myapp`": the name of your application
* "`-db mydb`": the name of your CloudBees MySQL Database
* "`-as mydb`": the name of the binding which is used to identify the binding and to compose the name of the environment variables used to describe this binding (always prefer '_' to '-' for bindings because '-' is not supported in linux environment variable names).

This binding will create

* A JNDI DataSource with name "`java:comp/env/jdbc/mydb`" (also available at "`jdbc/mydb`")
* The following System Properties
  * `DATABASE_URL_MYDB`: url of the database starting with "mysql:" (e.g. "mysql://ec2-1.2.3.4.compute-1.amazonaws.com:3306/tomcat7-maven-clickstart-db"). **Please note** that this URL is **not** prefixed by "jdbc:".
  * `DATABASE_USERNAME_MYDB`: login of the database
  * `DATABASE_PASSWORD_MYDB`: password of the database

Details on bindings are available in [Binding services (resources) to applications](https://developer.cloudbees.com/bin/view/RUN/Resource+Management).

### Use the DataSource in you application

#### Plain Java

You can now use your "`java:comp/env/jdbc/mydb`" JNDI DataSource in your application.
Please note that "`jdbc/mydb`" is also available.

Java code sample:

```java
Context ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
Connection conn = ds.getConnection();
ResultSet rst = stmt.executeQuery("select 1");
while (rst.next()) {
    out.print("resultset result: " + rst.getString(1));
}
rst.close();
stmt.close();
conn.close();
```

#### Java Standard Tag Library / JSTL

JSP / JSTL code sample:

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sql:query var="rs" dataSource="jdbc/mydb">
    select 1 as col1
</sql:query>

<h1>Datasource JSTL Demo</h1>

<c:forEach var="row" items="${rs.rows}">
Row: ${row.col1}<br/>
</c:forEach>
```


 




>>>>>>> bc4f2a870a8ba2c4c566d061215b0bfcdc3d8845
