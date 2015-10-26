<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>
<%-- <%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %> --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
  	<title>${title}</title>
  	<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"/>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
  </head>
  <body>
 	<c:set value="${user}" var="user" scope="session"/>
  	<div class="container">
  		<c:if test="${user != null }">
  		<div class="navbar-form navbar-right">
  			<c:out value="${user.getFirstName() }"/> <c:out value="${user.getLastName() }"/>
		  	<a class="btn btn-default" href="${pageContext.servletContext.contextPath}/deconnexion" role="button">Déconnexion</a>
		</div>
		</c:if>
		
  		<div class="page-header">
  			<h1><a href="${pageContext.request.contextPath}/">Gestion QCM</a></h1>	
 		</div>
<!--  		<ol class="breadcrumb">
			  <li><a href="#">Home</a></li>
			  <li><a href="#">Library</a></li>
			  <li class="active">Data</li>
		</ol> -->
 		<div class="container">
 			<c:choose>
			  <c:when test="${user.isAnimateur()}">
			    <ul class="navbar-nav nav">
		  			<li role="presentation" class="${homeactive}"><a href="#">Home</a></li>
		  			<li role="presentation" class="${testsactive}"><a href="${pageContext.request.contextPath}/ListTests">Gestion des tests</a></li>
		 			<li role="presentation" class="${homeactive}"><a href="#">Messages</a></li>
				</ul>
 			  </c:when>
			  <c:when test="${user.isStagiaire()}">
			    <ul class="navbar-nav nav">
		  			<li role="presentation" class="${homeactive}"><a href="#">Home</a></li>
				</ul>
			  </c:when>
			</c:choose>
			
			<div class="col-sm-12">
		  		<div class="panel panel-default">
			  	 	<div class="panel-heading">
						<h2>${title}</h2>				
					</div>
				  	<div class="panel-body">
				    	<div id="body">
				      		<jsp:doBody/>
				    	</div>
				  	</div>
				</div>
		    </div>
	    </div>
	    
	    <div id="pagefooter" class="text-center">
	      <%-- <jsp:invoke fragment="footer"/> --%>
	      <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>
	    </div>
	</div>
  </body>
</html>