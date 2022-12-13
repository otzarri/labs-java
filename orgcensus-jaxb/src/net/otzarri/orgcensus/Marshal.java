package net.otzarri.orgcensus;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *	This Servlet receives from marshal.jsp a form containing information about
 *  two organizations. An object is created for earch onganization and an
 *  Address object is created and assigned as value to each Organization object.
 *  When marshalled, the objects are mapped to a XML file.
 */

@WebServlet("/Marshal")
public class Marshal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Marshal() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    /**  Creating new object for mapping <address> node and it's children node values for organization 1. */
		Address orgOneAddress = new Address();
		orgOneAddress.setStreet(request.getParameter("org1_street"));
		orgOneAddress.setCity(request.getParameter("org1_city"));
		orgOneAddress.setCountry(request.getParameter("org1_country"));
		
		/**
		 *	Creating new object for mapping <organization> node and it's children node values for organization 1.
		 *  Note that Address object created above is used in the as value of the setAddress
		 *  method. This is the way for including a child nodeset into it's parents using JAXB.
		 */
		Organization orgOne = new Organization();
		orgOne.setName(request.getParameter("org1_name"));
		orgOne.setEmail(request.getParameter("org1_email"));
		orgOne.setAddress(orgOneAddress);
		
		/** Creating new object for mapping <address> node and it's children node values for organization 2. */
		Address orgTwoAddress = new Address();
		orgTwoAddress.setStreet(request.getParameter("org2_street"));
		orgTwoAddress.setCity(request.getParameter("org2_city"));
		orgTwoAddress.setCountry(request.getParameter("org2_country"));
		
		/**
		 *	Creating new object for mapping <organization> node and it's children node values for organization 1.
		 *  Note that Address object created above is used in the as value of the setAddress
		 *  method. This is the way for including a child nodeset into it's parents using JAXB.
		 */
		Organization orgTwo = new Organization();
		orgTwo.setName(request.getParameter("org2_name"));
		orgTwo.setEmail(request.getParameter("org2_email"));
		orgTwo.setAddress(orgOneAddress);
		
		/**  Creating a list of Organization type objects */
		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(orgOne);
		organizations.add(orgTwo);
		
		/** 
		 * Creating new object for mapping <census> node and it's children node values.
		 * It's only parameter is a List of organization objects, so we assign the list
		 * created below to it's setOrganizations method.
		 * This is the way for including multiple child nodes into a parents using JAXB.
		 */
		Census census = new Census();
		census.setOrganizations(organizations);

		/**  Marshalling Census object to XML String */
		Marshaller jaxbMarshaller;
		try {
			jaxbMarshaller = JAXBContext.newInstance(Census.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter xml = new StringWriter();
			jaxbMarshaller.marshal(census, xml);
			out.println("<h1>Marshal</h1><a href=\"index.jsp\">Index</a><p>This Servlet receives from marshal.jsp a form containing information about<br />two organizations. An object is created for earch onganization and an<br />Address object is created and assigned as value to each Organization object.<br />When marshalled, the objects are mapped to the XML text below:</p><hr />");
			out.println("<textarea style=\"height: 340px;	width: 540px;\">" + xml.toString() + "</textarea>");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
