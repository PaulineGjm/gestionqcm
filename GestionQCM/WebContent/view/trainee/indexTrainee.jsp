<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Accueil stagiaire</title>
</head>
<body>
	<div id="contenu">
		<jsp:useBean id="stagiaireConnecte" class="fr.gestionqcm.view.beans.StagiaireGUI" scope="session" />
		<p>
		Bonjour
		<jsp:getProperty property="firstName" name="stagiaireConnecte"/>
		<jsp:getProperty property="lastName" name="stagiaireConnecte"/>	
		</p>
	</div>
</body>
</html>