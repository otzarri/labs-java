<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Organization census</title>
</head>
<body>
	<h1>Organization census</h1>
	<p>
		This is a sample implementation of XML marshalling and unmarshalling using JAXB.<br />
		Emulates a census of organizations that manages data using XML format.<br />
		It's developed as a practical environment for learning how to implement JAXB.<br />
		The pages and the source code are well-commented to understand JAXB easily.
	</p>
	<p>
		There are two actions available:
		<ul>
			<li><a href="./marshal.jsp">Marshal</a></li>
			<li><a href="./unmarshal.jsp">Unmarshal</a><br /></li>
		</ul>
	<p>
		JAXB uses Java classes for mapping XML nodes. The <a href="organizations.xml">XML code</a> used in this implementation<br />
		is mapped using Address, Organization and Census classes on net.otzarri.orgcensus package.<br />
		Note that each subset (node level) is mapped to a different object and child subsets are assigned<br />
		to their parents using object variables with the subset object type.<br />
	</p>
	<p>The diagram below explains the XML mapping with JAXB used in this implementation:</p>
	<img src="img/xml-mapping-with-jaxb.svg" />
</body>
</html>