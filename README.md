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


 




