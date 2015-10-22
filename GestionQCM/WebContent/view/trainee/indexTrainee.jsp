<%@page import="fr.gestionqcm.view.beans.TestDisponibleGUI"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
		
		<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<TestDisponibleGUI> listTestsDisponibles = (ArrayList<TestDisponibleGUI>)request.getSession().getAttribute("listTestsDisponibles");
	 	for(TestDisponibleGUI testDispo : listTestsDisponibles) {
	%>
		<form class="connexion" action="<%=request.getContextPath()%>/test/BeginTest" method="post">
			<div class="formationEntete">
			<% if(testDispo.getTimeRemaining() != testDispo.getTestDuration())
				{
			%>
					<label><%="TEST EN COURS /!\\  "%></label>
			<%
				}
			%>
				<label><%=testDispo.getName()+ " "%></label>
				<label><%="Durée du test : " + testDispo.getTestDuration()%></label>
				<label><%="Début du test : " + df.format(testDispo.getTestStartDate())%></label>
				<input type="hidden" name="idInscription" value="<%=testDispo.getInscriptionID()%>" />
			<% if(testDispo.getTimeRemaining() != testDispo.getTestDuration())
				{
			%>
				<label><%="Temps restant : " + testDispo.getTimeRemaining()%></label>
			<%
				}
			%>
				<input type="submit" value="GO" />
			</div>	
		</form>
		<%
			}
	 	%>
		
	</div>
</body>
</html>