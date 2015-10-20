<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>S'authentifier</title>
	<link media="all" rel="stylesheet" href="<%=request.getContextPath()%>/theme/style.css" type="text/css"/>
</head>
<body>
	<div class="pageLogin">
		<div id="entete">
		</div>
		<div id="contenu">
			<div id="labelTitre">
				<p>Compte utilisateur</p>
			</div>
			<form class="connexion" action="" method="post">
				<div id="blocIdentifiant">
				<input class="champtexte" placeholder="Authentifiant" type="text" id="identifiant" name="identifiant"/><br/>
				</div>
				<div id="blocPassword">
				<input class="champtexte" placeholder="Mot de passe" type="text"  id="motdepasse" name="motdepasse"/>
				</div>
					<input type="submit" class="buttonBlue" id="seconnecter" value="Valider" />
			</form>
		</div>
		
		
	</div>
</body>
</html>