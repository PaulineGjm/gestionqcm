<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	<jsp:useBean id="inscription" class="fr.gestionqcm.model.bo.InscriptionTest" />
		<jsp:getProperty name="inscription" property="inscriptionId" />
    </jsp:body>
</t:genericpage>