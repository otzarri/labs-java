package net.otzarri.orgcensus;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;

/**
 *	This Servlet receives a form from unmarshal.jsp containig a textarea
 *  with XML code that stores information about two organizations. When
 *  unmarshalled, this code is mapped to a object list of Organization type.
 */
@WebServlet("/Unmarshal")
public class Unmarshal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Unmarshal() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**  XML code submitted from unmarshal.jsp */
		String xml = request.getParameter("xml");
		
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
		
	    out.println("<h1>Unmarshal</h1><a href=\"index.jsp\">Index</a><p>This Servlet receives from unmarshal.jsp a form containig a textarea<br />with XML code that stores information about two organizations. When<br />unmarshalled, this code is mapped to a object list of Organization type.</p><hr />");
	    
	    /**  Creating a list of Organization type objects from Census object */
		Census census = JAXB.unmarshal(new StringReader(xml), Census.class);
		List<Organization> organizations = census.getOrganizations();
		
		/**  Iterating the list to print the values */
		for (Organization organization : organizations) {
			out.println(
				"Name: " + organization.getName() + "<br />" +
				"E-mail: " + organization.getEmail() + "<br />" +
				"Street: " + organization.getAddress().getStreet() + "<br />" +
				"Country: " + organization.getAddress().getCountry() + "<br />" +
				"City: " + organization.getAddress().getCity() + "<br /><br />"
			);
		}
	}
}