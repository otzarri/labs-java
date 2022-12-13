<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Marshal</title>
</head>
<body>
	<h1>Marshal</h1>
	<a href="index.jsp">Index</a>
	<p>This page submits to Mashal servlet a form with information about two organizations.</p>
	<hr />
	<form action="./Marshal" method="post" >
		<h2>Organization 1</h2>
		<label>Name: </label><input type="text" name="org1_name" value="Free Software Foundation Europe e.V." /><br />
		<label>E-mail: </label><input type="text" name="org1_email" value="office@fsfeurope.org" /><br />
		<label>Street: </label><input type="text" name="org1_street" value="Schonhauser Allee 6/7. Stairway 2, 5. floor" /><br />
		<label>City: </label><input type="text" name="org1_city" value="Berlin" /><br />
		<label>Country: </label><input type="text" name="org1_country" value="Germany" /><br />
		
		<h2>Organization 2</h2>
		<label>Name: </label><input type="text" name="org2_name" value="Free Software Foundation" /><br />
		<label>E-mail: </label><input type="text" name="org2_email" value="info@fsf.org" /><br />
		<label>Street</label><input type="text" name="org2_street" value="51 Franklin Street, Fifth Floor" /><br />
		<label>City: </label><input type="text" name="org2_city" value="Boston" /><br />
		<label>Country: </label><input type="text" name="org2_country" value="USA" /><br />
		<input type="submit" value="Submit" /><input type="reset" value="Reset" />
	</form>
</body>
</html>