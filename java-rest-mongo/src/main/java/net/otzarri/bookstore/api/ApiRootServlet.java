package net.otzarri.bookstore.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api")
public class ApiRootServlet extends HttpServlet {
    String html =
        "<html>"                                                      +
        "    <body>"                                                  +
        "        <h1>Book Store API</h1>"                             +
        "        <p>"                                                 +
        "            <ul>"                                            +
        "                <li><a href=\"./\">..</a></li>"              +
        "                <li><a href=\"./api/books\">/books</a></li>" +
        "            </ul>"                                           +
        "        </p>"                                                +
        "    </body>"                                                 +
        "</html>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        response.setContentType("text/html; charset=utf-8");
        out.println(html);
    }
}