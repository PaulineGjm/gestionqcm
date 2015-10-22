<%@page import="fr.gestionqcm.view.beans.ReponseGUI"%>
<%@page import="fr.gestionqcm.view.beans.QuestionGUI"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Test en cours</title>
</head>
<body>
		Test C#
		
				<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		QuestionGUI question = (QuestionGUI)request.getSession().getAttribute("selectedQuestion");
		boolean isMultiple = (question.getListResponses().size() > 2);
		if(isMultiple)
		{
			%>
			<p>Question à choix multiple</p>
			<%
		}
		else
		{
			%>
			<p>Question simple</p>
			<%
		}
		
		%>
			<p>
			<%=question.getWording() %>
			</p>
			<form class="connexion" action="<%=request.getContextPath()%>/test/BeginTest" method="post">
		<%
		
	 		for(ReponseGUI responseGUI : question.getListResponses())
	 		{
	 			if(isMultiple)
	 			{
	 				%>
	 				<p>
	 				<input type="checkbox" value="<%=responseGUI.getIdResponse()%>"><%="   "+responseGUI.getWording()%>
	 				</p>
	 				<%
	 			}
	 			else
	 			{
	 				%>
	 				<p>
	 				<input type="radio" value="<%=responseGUI.getIdResponse()%>"><%="   "+responseGUI.getWording()%>
	 				</p>
	 				<%
	 			}
		%>
		</form>
		<%
			}
	 	%>
</body>
</html>