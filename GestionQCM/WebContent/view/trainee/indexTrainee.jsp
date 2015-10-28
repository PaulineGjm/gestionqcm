<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Accueil stagiaire">
    <jsp:body>
    	<link href="${pageContext.servletContext.contextPath}/resources/datatable/media/css/jquery.dataTables.css" rel="stylesheet">
		<script src="${pageContext.servletContext.contextPath}/resources/datatable/media/js/jquery.dataTables.min.js"></script>
		<div id="page">
			<div id="contenu">
		
			<%@ include file="/view/trainee/indexTrainee.jspf" %>	
		
		    </div>
		</div>
    </jsp:body>
</t:genericpage>