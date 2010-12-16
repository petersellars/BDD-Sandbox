<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
<h1 id="welcome">
  Welcome <sec:authentication property="principal.username" htmlEscape="true"/> 
  to the BDD-Sandbox
</h1>
<div id="footer">
	<p>Logged in as
		<sec:authorize ifAllGranted="ROLE_ADMINISTRATOR" >
		an administrator.
		</sec:authorize>
	</p>
</div>
</body>
</html>
