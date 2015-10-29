<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="fr.gestionqcm.view.beans.EditInscriptionGUI" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="datatables" uri="http://github.com/tduchateau/DataTables-taglib" %>
<%@ page import="fr.gestionqcm.view.beans.EditInscriptionGUI.FormFields" %>

<c:set var="inscriptionTestSelected">
    <%=FormFields.inscriptionTestSelected %>
</c:set>
<c:set var="startDateSelected">
    <%=FormFields.startDateSelected %>
</c:set>
<c:set var="startHourSelected">
    <%=FormFields.startHourSelected %>
</c:set>
<c:set var="usersSelected">
    <%=FormFields.usersSelected %>
</c:set>
<c:set var="users">
    <%=FormFields.users %>
</c:set>
<c:set var="testSelected">
    <%=FormFields.testSelected %>
</c:set>


<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	<link href="${pageContext.servletContext.contextPath}/resources/datatable/media/css/jquery.dataTables.css" rel="stylesheet">
		<script src="${pageContext.servletContext.contextPath}/resources/datatable/media/js/jquery.dataTables.min.js"></script>
		<link href="${pageContext.servletContext.contextPath}/resources/datatable/media/css/demo_table.css" rel="stylesheet">
		<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/teacher/inscriptions/editInscription.js"></script>
		
    	<h3>Liste des tests existant : </h3>
    	<hr />
    	
   		<form method="post" class="form-horizontal" role="form" 
    			action="${pageContext.servletContext.contextPath}/teacher/inscriptions/edit">
			<div class="clearfix">
				<datatables:table data="${editInscriptionGUI.inscriptionsTest}" htmlTableId="inscriptionsTestTable" 
					dataObjectId="inscriptionTest" autoWidth="true" dom="'t'">
					<datatables:column title=" " headerCssClass="col-sm-1 text-center">
						<input type="radio" name="${inscriptionTestSelected}" value="{testId:${inscriptionTest.test.testId},testStartDate:'${inscriptionTest.testStartDate}'}" />
					</datatables:column>	
					<datatables:column title="Nom du Test" headerCssClass="col-sm-6 text-center">
						<c:out value="${inscriptionTest.test.name}" />
					</datatables:column>
					<datatables:column title="Date de début" headerCssClass="col-sm-5 text-center">
						<span>Le</span>
						<strong>
							<fmt:formatDate pattern="dd/MM/yyyy" value="${inscriptionTest.testStartDate}" />
						</strong>
						<span>à</span>
						<strong>
							<fmt:formatDate pattern="HH:mm" value="${inscriptionTest.testStartDate}" />
						</strong>
					</datatables:column>
				</datatables:table>
			</div>
			<br />
			<div class="text-center">
				<input type="submit" value="Modifier" class="btn btn-defaut"/>
				<input type="submit" value="Programmer nouveau test" class="btn btn-defaut"
					onclick='this.form.action="${pageContext.servletContext.contextPath}/teacher/inscriptions/add";'/>
				<input type="submit" value="Supprimer" class="btn btn-defaut"
					onclick='this.form.action="${pageContext.servletContext.contextPath}/teacher/inscriptions/delete";'/>
			</div>
		</form>
		<c:if test="${editInscriptionGUI.startDateSelected != null && !editInscriptionGUI.startDateSelected.isEmpty()}">
	    	<h3>Détails du test : </h3>
	    	<hr />
	    	<form id="editInscriptionForm" method="post" class="form-horizontal" role="form" 
	    		action="${pageContext.servletContext.contextPath}/teacher/inscriptions/save">
				<input type="hidden" name="${inscriptionTestSelected}" value="{testId:${editInscriptionGUI.testSelected.testId}, testStartDate:'${editInscriptionGUI.formatDateSelected}'}" />
				<div class="form-group">
		    		<label for="startDateSelected" class="control-label col-sm-3">Date de début :</label>
		    		<div class="col-sm-3">
		    			<input type="date" id="startDateSelected" name="${startDateSelected}" 
		    				value="${editInscriptionGUI.startDateSelected}" class="form-control">
					</div>
					<label for="startHourSelected" class="control-label col-sm-3">Heure de début :</label>
		    		<div class="col-sm-3">
		    			<input type="time" id="startHourSelected" name="${startHourSelected}" 
		    				value="${editInscriptionGUI.startHourSelected}" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
		    		<label for="testSelected" class="control-label col-sm-3">List des tests :</label>
		    		<div class="col-sm-9">
				    	<select id="tests" name="${testSelected}" class="form-control">
						   <c:forEach items="${editInscriptionGUI.tests}" var="test">
						       <option value="${test.testId}" ${editInscriptionGUI.testSelected.testId eq test.testId ? 'selected' : ''}>${test.name}</option>
						   </c:forEach>
						</select>
					</div>
				</div>
				<h5>Liste des inscrits :</h5>
				<div class="clearfix">
					<datatables:table data="${editInscriptionGUI.subscribedInscriptionsTest}" htmlTableId="subscribedInscriptionsTestTable" 
						dataObjectId="inscriptionTest" autoWidth="true" dom="'t'">
						<datatables:column title=" " headerCssClass="col-sm-1 text-center">
							<input type="hidden" name="${users}" value="${inscriptionTest.user.id}" />
							<input type="checkbox" value="${inscriptionTest.user.id}" name="${usersSelected}"/>
						</datatables:column>
						<datatables:column title="nom" headerCssClass="col-sm-6 text-center">
							${inscriptionTest.user.lastName}
						</datatables:column>
						<datatables:column title="prenom" headerCssClass="col-sm-5 text-center">
							${inscriptionTest.user.firstName}
						</datatables:column>
					</datatables:table>
				</div>
				<br />
				<div class="text-right">
					<%@ include file="/view/teacher/inscriptions/stagiaireSearch.jspf" %>	
					<button type="button" onclick="deleteStagiaires();" class="btn btn-defaut">Supprimer stagiaire(s)</button>
				</div>
				<div class="text-center">
					<input type="submit" value="Sauvegarder Modification" class="btn btn-defaut" />
				</div>
			</form>
		</c:if>
    </jsp:body>
</t:genericpage>