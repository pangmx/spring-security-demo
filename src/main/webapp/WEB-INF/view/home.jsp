<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>Home page</title>
</head>
<body>
	Spring security demo home page
	<hr>
		<p>
			User name: <security:authentication property="principal.username"/><br>
			Roles: <security:authentication property="principal.authorities"/>
		</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<!-- Only show this link if user has role MANAGER -->
		<a href="${pageContext.request.contextPath}/leaders">Leaders</a>
	</security:authorize>
	
	<br>
	
	<security:authorize access="hasRole('ADMIN')">
		<!-- Only show this link if user has role ADMIN -->
		<a href="${pageContext.request.contextPath}/systems">Systems</a>
	</security:authorize>
	<hr>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
	
</body>
</html>