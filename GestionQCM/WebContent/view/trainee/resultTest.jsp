<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Résultat du test">
    <jsp:body>
		<div id="page">
			<div id="contenu">
		
			<%@ include file="/view/trainee/resultTest.jspf" %>	
		
		    </div>
		</div>
    </jsp:body>
</t:genericpage>