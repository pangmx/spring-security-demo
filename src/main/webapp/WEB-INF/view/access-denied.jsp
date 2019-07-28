<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>Access Denied</title>
</head>
<body>
	<h2>Access Denied Page</h2>
	<p>
		You do not have the right to access this page.
	</p>
	<a href="${pageContext.request.contextPath}/">Back to Home</a>
</body>
</html>