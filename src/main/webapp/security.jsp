<%@ page import="java.security.Security" %>
<%@ page import="java.security.Provider" %>
<%@ page import="java.util.Map" %>
<%@ page import="javax.crypto.Cipher" %>
<HTML>
<HEAD>
    <TITLE>JVM Security</TITLE>
</HEAD>
<BODY>
<h1>JVM Security</h1>

    <%
        for (Provider provider : Security.getProviders()) {

    %>
<h2>Provider: <%=provider.getName() + " " + provider.getVersion()%>
</h2>

<h3>Services</h3>

<table border="1">
    <tr>
        <th>Type</th>
        <th>Algorithm</th>
        <th>Max Allowed Key Length</th>
    </tr>
    <%
        for (Provider.Service service : provider.getServices()) {
            out.print("<tr><td>" + service.getType() + "</td><td>" + service.getAlgorithm() + "</td>");
            out.print("<td>");
            String msg;
            try {
                msg = Cipher.getMaxAllowedKeyLength(service.getAlgorithm()) + "";
            } catch (Exception e) {
                msg = "N/A";
            }
            out.print(msg);
            out.print("</td>");
            out.println("</tr>");
        }
    %></table>


    <%
            }
%>

</table>