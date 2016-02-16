<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%>
<html>
	<head>
		<style type="text/css">
		body{
			background-image: url("images/inventory2.jpg");
			 -webkit-background-size: cover;
			  -moz-background-size: cover;
			  -o-background-size: cover;
			  background-size: cover;
		}
		</style>
		<link href="ressources/assets/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet"/>
	</head>
	<body onload='document.loginForm.username.focus();'>
		<!--login modal-->
		<div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
		  <div class="modal-dialog">
		  <div class="modal-content">
		      <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		          <h1 class="text-center">Login</h1>
		      </div>
		      <div class="modal-body">
		      		<c:if test="${not empty error}">
						<div class="error">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div class="msg">${msg}</div>
					</c:if>
		          <form class="form col-md-12 center-block" name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
		            <div class="form-group">
		              <input type="text" name="username" class="form-control input-lg" placeholder="Username">
		            </div>
		            <div class="form-group">
		              <input type="password" name="password" class="form-control input-lg" placeholder="Password">
		            </div>
		            <div class="form-group">
		              <button class="btn btn-primary btn-lg btn-block" name="submit">Sign In</button>
		              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		            </div>
		          </form>
		      </div>
		      <div class="modal-footer">
		          <div class="col-md-12">
		          <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
				  </div>	
		      </div>
		  </div>
		  </div>
		</div>
		<!-- jQuery -->
	    <script src="ressources/assets/bower_components/jquery/dist/jquery.min.js"></script>
	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="ressources/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	</body>
</html>