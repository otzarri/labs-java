<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unmarshall</title>
<style>
textarea {
	height: 340px;
	width: 540px;
}
</style>
</head>
<body>
	<h1>Unmarshall</h1>
	<a href="index.jsp">Index</a>
	<p>This page submits to Unmarshal servlet a form containig a textarea with XML code that stores information about two organizations.</p>
	<hr />
	<form action="./Unmarshal" method="post" >
		<textarea name="xml">
<%@ include file="organizations.xml" %>
		</textarea>
		<br /><br />
		<input type="hidden" name="action" value="query" />
		<input type="submit" value="Submit" />
		<input type="reset" value="Reset" />
	</form>
</body>
</html>