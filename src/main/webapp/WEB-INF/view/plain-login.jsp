<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.failed{
			color:red;
		}
	</style>

<meta charset="ISO-8859-1">
<title>Plain Login Page</title>
</head>
<body>
<h1>My Custom Login Page</h1>
	<c:if test="${param.error!=null }">
		<div class="failed">You have entered a wrong username/ password.</div>
	</c:if>
	<form:form action="${pageContext.request.contextPath}/authenticateUser" 
		method="POST">
		<p> 
			User name: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<input type="submit" value="Login" />
	</form:form>
</body>
</html>