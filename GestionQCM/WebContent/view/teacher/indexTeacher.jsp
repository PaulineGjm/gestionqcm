<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Accueil formateur">
    <jsp:body>
		<div id="page">
			<div id="contenu">
				<jsp:useBean id="animateurConnecte" class="fr.gestionqcm.view.beans.AnimateurGUI" scope="session" />
				<p>
				Bonjour
				<jsp:getProperty property="firstName" name="animateurConnecte"/>
				<jsp:getProperty property="lastName" name="animateurConnecte"/>	
				</p>
			</div>
		</div>
    </jsp:body>
</t:genericpage>