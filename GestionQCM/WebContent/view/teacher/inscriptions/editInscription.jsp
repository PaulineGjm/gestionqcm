<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>

<sql:setDataSource var="datasource" driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"
     url="jdbc:sqlserver://10.21.200.18:1433;databasename=GestionQCM"
     user="sa"  password="Pa$$w0rd"/>
     
<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	<h3>Liste des test à existant : </h3>
   		<form method="post" class="form-horizontal" role="form" 
    			action="${pageContext.servletContext.contextPath}/teacher/inscriptions/edit">
			<datatables:table data="${editInscriptionGUI.inscriptionsTest}" htmlTableId="inscriptionsTestTable" 
				dataObjectId="inscriptionTest" autoWidth="true">
			 	<datatables:column title=" " headerCssClass="col-sm-1 text-center">
			 		<input type="radio" name="inscriptionsTestSelected" value="{testId:${inscriptionTest.test.testId},testStartDate:${inscriptionTest.testStartDate}}" />
			 	</datatables:column>	
			   	<datatables:column title="Nom du Test" headerCssClass="col-sm-6 text-center">
			      <c:out value="${inscriptionTest.test.name}" />
			   </datatables:column>
			   <datatables:column title="Date de début" headerCssClass="col-sm-5 text-center">
			      <c:out value="${inscriptionTest.testStartDate}" />
			   </datatables:column>
			</datatables:table>
			<div>
				<input type="submit" value="Modifier" class="btn btn-defaut center-block"/>
			</div>
		</form>

    	<form method="post" class="form-horizontal" role="form" 
    		action="${pageContext.servletContext.contextPath}/teacher/inscriptions/edit">
			<div class="form-group">
	    		<label for="startDateSelected" class="control-label col-sm-3">Date de début :</label>
	    		<div class="col-sm-3">
	    			<input type="date" id="startDateSelected" name="startDateSelected" 
	    				value="${editInscriptionGUI.startDateSelected}" class="form-control">
				</div>
				<label for="startHourSelected" class="control-label col-sm-3">Heure de début :</label>
	    		<div class="col-sm-3">
	    			<input type="time" id="startHourSelected" name="startHourSelected" 
	    				value="${editInscriptionGUI.startDateSelected}" class="form-control">
				</div>
			</div>
			<div class="form-group">
	    		<label for="testSelected" class="control-label col-sm-3">List des tests :</label>
	    		<div class="col-sm-9">
			    	<select id="tests" name="testSelected" class="form-control">
					   <c:forEach items="${editInscriptionGUI.tests}" var="test">
					       <option value="${test.testId}" ${editInscriptionGUI.testSelected.testId eq test.testId ? 'selected' : ''}>${test.name}</option>
					   </c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-3">
	    			<input type="submit" value="Valider" class="btn btn-defaut" />
	    		</div>
			</div>
		</form>
    </jsp:body>
</t:genericpage>