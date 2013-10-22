/*
 * Copyright 2010-2013, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package localdomain.localhost;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:cleclerc@cloudbees.com">Cyrille Le Clerc</a>
 */
@WebServlet(value = "/MyServlet")
public class MyServlet extends HttpServlet {
    protected final Logger logger = Logger.getLogger(getClass().getName());
    @Resource(name = "jdbc/mydb", lookup = "jdbc/mydb")
    private DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("Init");
        System.out.println(getClass().getName() + ".init");
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("<html>");
        writer.println("<head><title>MyServlet</title></head>");
        writer.println("<body><h1>MyServlet</h1>");

        writer.println("<h2>DataSource</h2>");
        Connection conn = null;
        try {
            writer.println("Datasource: " + dataSource + "<br/><br/>");
            conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery("select 1");
            while (rst.next()) {
                writer.println("Resultset result: " + rst.getString(1) + "<br/><br/>");
            }
            rst.close();
            stmt.close();
            conn.close();
            writer.println("SUCCESS to access the datasource");

        } catch (Exception e) {
            e.printStackTrace(writer);
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        writer.println("</body></html>");

    }
}
