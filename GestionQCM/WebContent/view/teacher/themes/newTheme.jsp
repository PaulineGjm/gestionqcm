<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Nouveau theme">
    <jsp:body>
		<div id="page">
			<div id="contenu">
		
			<%@ include file="/view/teacher/themes/newOrUpdateTheme.jspf" %>	
		
		    </div>
		</div>
    </jsp:body>
</t:genericpage>