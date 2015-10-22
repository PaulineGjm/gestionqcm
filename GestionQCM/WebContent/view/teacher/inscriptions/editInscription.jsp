<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	${EditInscriptionGUI}
    	<select name="tests">
		   <c:forEach items="${EditInscriptionGUI.tests}" var="test">
		       <option value="${test.testId}" ${param.test eq test.testId ? 'selected' : ''}>${test.name}</option>
		   </c:forEach>
		</select>
    </jsp:body>
</t:genericpage>