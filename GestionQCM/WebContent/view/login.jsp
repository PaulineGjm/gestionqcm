<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:genericpage title="Identification">
    <jsp:body>
		<div id="page">
			<div id="contenu">
				<div id="labelTitre">
					<p>Compte utilisateur</p>
				</div>
				<form class="connexion" action="${pageContext.servletContext.contextPath}/connexion" method="post">
					<div id="blocIdentifiant" class="form-group">
						<input class="champtexte form-control" placeholder="Authentifiant" type="text" id="identifiant" name="identifiant"/><br/>
					</div>
					<div id="blocPassword" class="form-group">
						<input class="champtexte form-control" placeholder="Mot de passe" type="text"  id="motdepasse" name="motdepasse"/>
					</div>
						<input type="submit" class="buttonBlue" id="seconnecter" value="Valider" />
				</form>
			</div>
		</div>
    </jsp:body>
</t:genericpage>