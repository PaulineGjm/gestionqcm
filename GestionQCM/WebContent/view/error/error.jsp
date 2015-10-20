<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erreur</title>
</head>
<body>

<div id="page">

	<div id="entete">
		<h1>Erreur</h1>
	</div>

	<%-- <%@ include file="/menu.jspf" %> --%>

	<div id="contenu">
	
	<div class="erreur">
		<h2>Erreur</h2>
		<jsp:useBean id="error" class="java.lang.Exception" type="java.lang.Exception" scope="request" />
		<p>Une erreur s'est produite : <jsp:getProperty name="error" property="message" /></p>
	</div>
 	</div>

	<%-- <%@ include file="/footer.jspf" %> --%>
	
</div>

</body>
</html>