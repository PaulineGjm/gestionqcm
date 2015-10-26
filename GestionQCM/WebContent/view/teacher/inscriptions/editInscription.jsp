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
<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	<h3>Liste des tests existant : </h3>
    	
   		<form method="post" class="form-horizontal clearfix" role="form" 
    			action="${pageContext.servletContext.contextPath}/teacher/inscriptions/edit">
			<datatables:table data="${editInscriptionGUI.inscriptionsTest}" htmlTableId="inscriptionsTestTable" 
				dataObjectId="inscriptionTest" autoWidth="true">
			 	<datatables:column title=" " headerCssClass="col-sm-1 text-center">
			 		<input type="radio" name="${inscriptionTestSelected}" value="{testId:${inscriptionTest.test.testId},testStartDate:${inscriptionTest.testStartDate}}" />
			 	</datatables:column>	
			   	<datatables:column title="Nom du Test" headerCssClass="col-sm-6 text-center">
			      <c:out value="${inscriptionTest.test.name}" />
			   </datatables:column>
			   <datatables:column title="Date de début" headerCssClass="col-sm-5 text-center">
			      <fmt:formatDate pattern="dd/MM/yyyy" value="${inscriptionTest.testStartDate}" />
			   </datatables:column>
			</datatables:table>
			
			<div class="pull-right">
				<input type="submit" value="Modifier" class="btn btn-defaut"/>
				<input type="submit" value="Programmer nouveau test" class="btn btn-defaut"
					onclick='this.form.action="${pageContext.servletContext.contextPath}/teacher/inscriptions/add";'/>
				<input type="submit" value="Supprimer" class="btn btn-defaut"
					onclick='this.form.action="${pageContext.servletContext.contextPath}/teacher/inscriptions/delete";'/>
			</div>
		</form>
		<c:if test="${editInscriptionGUI.startDateSelected != null && !editInscriptionGUI.startDateSelected.isEmpty()}">
	    	<form method="post" class="form-horizontal" role="form" 
	    		action="${pageContext.servletContext.contextPath}/teacher/inscriptions/">
				
				<div class="form-group">
		    		<label for="startDateSelected" class="control-label col-sm-3">Date de début :</label>
		    		<div class="col-sm-3">
		    			<input type="date" id="startDateSelected" name="${startDateSelected}" 
		    				value="${editInscriptionGUI.startDateSelected}" class="form-control">
					</div>
					<label for="startHourSelected" class="control-label col-sm-3">Heure de début :</label>
		    		<div class="col-sm-3">
		    			<input type="time" id="startHourSelected" name="startHourSelected" 
		    				value="${editInscriptionGUI.startHourSelected}" class="form-control">
					</div>
				</div>
				
				<div class="form-group">
		    		<label for="testSelected" class="control-label col-sm-3">List des tests :</label>
		    		<div class="col-sm-9">
				    	<select id="tests" name="${FormFields.testSelected}" class="form-control">
						   <c:forEach items="${editInscriptionGUI.tests}" var="test">
						       <option value="${test.testId}" ${editInscriptionGUI.testSelected.testId eq test.testId ? 'selected' : ''}>${test.name}</option>
						   </c:forEach>
						</select>
					</div>
				</div>
				
				<datatables:table data="${editInscriptionGUI.subscribedInscriptionsTest}" htmlTableId="inscriptionsTestTable" 
					dataObjectId="inscriptionTest" autoWidth="true"></datatables:table>
				<div class="form-group">
					<div class="col-sm-offset-3">
		    			<input type="submit" value="Valider" class="btn btn-defaut" />
		    		</div>
				</div>
			</form>
		</c:if>
    </jsp:body>
</t:genericpage>