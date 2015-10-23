<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>
<%-- <%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %> --%>
<html>
  <head>
  	<title>${title}</title>
  	<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"/>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
  </head>
  <body>
  	<div class="container">
  		<form class="navbar-form navbar-right" role="search">
		  <div class="form-group">
		    <input type="text" class="form-control" placeholder="Search">
		  </div>
		  <button type="submit" class="btn btn-default">Submit</button>
		</form>
		
  		<div class="page-header">
  			<h1>Gestion QCM</h1>	
 		</div>
<!--  		<ol class="breadcrumb">
			  <li><a href="#">Home</a></li>
			  <li><a href="#">Library</a></li>
			  <li class="active">Data</li>
		</ol> -->
 		<div class="container">
	 		<ul class="nav nav-pills nav-stacked col-sm-3">
	  			<li role="presentation" class="${homeactive}"><a href="#">Home</a></li>
	  			<li role="presentation" class="${testsactive}"><a href="${pageContext.request.contextPath}/ListTests">Gestion des tests</a></li>
	 			<li role="presentation" class="${homeactive}"><a href="#">Messages</a></li>
			</ul>
			
			<div class="col-sm-9">
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