package net.otzarri.bookstore.api;

import net.otzarri.bookstore.db.DocumentDAO;
import net.otzarri.bookstore.model.DocumentList;
import org.apache.log4j.Logger;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/api/books/*")
public class BooksServlet extends HttpServlet {
    private final static Logger logger = Logger.getLogger(DocumentDAO.class);
    private final static DocumentDAO documentDAO = new DocumentDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String isbn = pathToValue(request.getPathInfo());

        try {
            Document query = documentDAO.buildQuery(null, isbn);
            DocumentList result = documentDAO.find(query);

            response.setStatus(200);
            response.setContentType("application/json; charset=utf-8");
            out.println(result.toJson());
        } catch (NullPointerException e) {
            response.setStatus(404);
            response.setContentType("application/json; charset=utf-8");
            out.println("{}");
        } catch (Exception e) {
            response.setStatus(500);
            response.setContentType("application/json; charset=utf-8");
            out.println(new Document("code", "500")
                    .append("description","Internal Server Error")
                    .append("message","Error finding documents")
                    .toJson());
            logger.error(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            Document document = Document.parse(body);
            documentDAO.insert(document);

            response.setStatus(201);
            response.setContentType("application/json; charset=utf-8");
            response.setHeader("Location", this.getLocation(request, document));
            out.println(document.toJson());
        } catch (Exception e) {
            response.setStatus(500);
            response.setContentType("application/json; charset=utf-8");
            out.println(new Document("code", "500")
                    .append("description","Internal Server Error")
                    .append("message","Error inserting document")
                    .toJson());
            logger.error(e);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String isbn = pathToValue(request.getPathInfo());
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        try {
            Document query = documentDAO.buildQuery(null, isbn);
            Document document = Document.parse(body);
            documentDAO.update(query, document);
            document = documentDAO.find(document).getDocumentList().get(0);

            response.setStatus(200);
            response.setContentType("application/json; charset=utf-8");
            out.println(document.toJson());
        } catch (NullPointerException e) {
            response.setStatus(404);
            response.setContentType("application/json; charset=utf-8");
            out.println("{}");
        } catch (Exception e) {
            response.setStatus(500);
            response.setContentType("application/json; charset=utf-8");
            out.println(new Document("code", "500")
                    .append("description","Internal Server Error")
                    .append("message","Error updating document")
                    .toJson());
            logger.error(e);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String isbn = pathToValue(request.getPathInfo());

        try {
            Document query = documentDAO.buildQuery(null, isbn);
            documentDAO.delete(query);

            response.setStatus(200);
            response.setContentType("application/json; charset=utf-8");
            out.println("{}");
        } catch (NullPointerException e) {
            response.setStatus(404);
            response.setContentType("application/json; charset=utf-8");
            out.println("{}");
        } catch (Exception e) {
            response.setStatus(500);
            response.setContentType("application/json; charset=utf-8");
            out.println(new Document("code", "500")
                    .append("description","Internal Server Error")
                    .append("message","Error deleting document")
                    .toJson());
            logger.error(e);
        }
    }

    private String getLocation(HttpServletRequest request, Document document) {
        String scheme = request.getScheme();
        String address = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();

        String url = String.format("%s://%s:%d%s%s", scheme, address, port, contextPath, servletPath);
        String isbn = document.get("isbn").toString();
        String location = url + '/' + isbn;
        return location;
    }

    private String pathToValue(String pathInfo) {
        String id = null;
        if (pathInfo != null && !pathInfo.equals("/")) {
            id = pathInfo;
            if (id.startsWith("/")){ id = id.substring(1); }
            if (id.endsWith("/")) { id = id.substring(0, id.length()-1); }
        }
        return id;
    }
}