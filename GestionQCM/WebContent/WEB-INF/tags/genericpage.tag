<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
  <head>
  	<title>${title}</title>
  	<script src="${pageContext.request.contextPath}/resources/jquery/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
  </head>
  <body>
  	<div class="container">
	  <h2>${title}</h2>
	    <div id="pageheader">
	      <jsp:invoke fragment="header"/>
	    </div>
	    <div id="body">
	      <jsp:doBody/>
	    </div>
	    <div id="pagefooter">
	      <jsp:invoke fragment="footer"/>
	    </div>
	</div>
  </body>
</html>