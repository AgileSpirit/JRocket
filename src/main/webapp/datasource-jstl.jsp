<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/mydb">
    select 1 as col1
</sql:query>

<html>
<head>
    <title>Datasource JSTL Demo</title>
</head>
<body>

<h1>Datasource JSTL Demo</h1>

<h2>Results of "select 1 as col1"</h2>

<c:forEach var="row" items="${rs.rows}">
Row: ${row.col1}<br/>
</c:forEach>

Success
</body>
</html>