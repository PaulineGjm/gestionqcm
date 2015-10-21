<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des tests</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/ressources/jquery/jquery-1.11.3.min.js"></script>
</head>
<body>

<div id="page">
	<div id="entete">
		<h1>Nouveau test</h1>
	</div>

	<%-- <%@ include file="/menu.jspf" %> --%>

	<div id="contenu">

	<%@ include file="/view/teacher/tests/newOrUpdateTest.jspf" %>	

    </div>

		
	<%-- <%@ include file="/footer.jspf" %> --%>

</div>

</body>
</html>