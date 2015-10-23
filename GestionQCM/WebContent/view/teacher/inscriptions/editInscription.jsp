<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:genericpage title="Gestion des inscriptions">
    <jsp:body>
    	<form method="post" class="form-horizontal" role="form" 
    		action="${pageContext.servletContext.contextPath}/teacher/inscriptions/edit">
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
				<div class="col-sm-offset-3">
	    			<input type="submit" value="Valider"/>
	    		</div>
			</div>
		</form>
    </jsp:body>
</t:genericpage>