<%@ page import="javax.naming.Context" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.naming.InitialContext" %>
<html>
<body>
<h1>DataSource demo</h1>

<h2>DataSource declaration</h2>

<p/>

<h2>Sample of code</h2>
<table border="1">
    <tr>
        <td>
<pre>
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
<code>
</code>
</pre>
        </td>
    </tr>
</table>
<p>
    <%
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydb");
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rst = stmt.executeQuery("select 1");
        while (rst.next()) {
            out.print("resultset result: " + rst.getString(1));
        }
        rst.close();
        stmt.close();
        conn.close();
    %>
</p>
<strong>Success! Your DataSource works!</strong>
</body>
</html>
