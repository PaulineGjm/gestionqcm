<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	<%-- <jsp:useBean id="inscription" class="fr.gestionqcm.model.bo.InscriptionTest" />
		<jsp:getProperty name="inscription" property="inscriptionId" /> --%>
		<div id="page">
			<div id="entete">
				<h1>Liste des tests</h1>
			</div>
			<div id="contenu">
		
			<%@ include file="/view/teacher/tests/listTests.jspf" %>	
		
		    </div>
		</div>
    </jsp:body>
</t:genericpage>