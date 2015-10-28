<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Gestion des tests">
    <jsp:body>
   		<link href="${pageContext.servletContext.contextPath}/resources/datatable/media/css/jquery.dataTables.css" rel="stylesheet">
		<script src="${pageContext.servletContext.contextPath}/resources/datatable/media/js/jquery.dataTables.min.js"></script>
		
		<div id="page">
			<div id="contenu">
		
			<%@ include file="/view/teacher/tests/listTests.jspf" %>	
		
		    </div>
		</div>
    </jsp:body>
</t:genericpage>