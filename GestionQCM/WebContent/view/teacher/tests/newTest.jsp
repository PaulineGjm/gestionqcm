<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Nouveau test">
    <jsp:body>
		<div id="page">
			<div id="contenu">
		
			<%@ include file="/view/teacher/tests/newOrUpdateTest.jspf" %>	
		
		    </div>
		</div>
    </jsp:body>
</t:genericpage>