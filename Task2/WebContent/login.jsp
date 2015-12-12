<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="app/login" method ="Post">
	<input type = "email" name ="email" placeholder="Enter email"/><br/>
	<input type = "password" name ="password" placeholder="Enter password"/><br/>
	<input type = "submit" value ="Login"/>
</form>
<c:forEach items="${errors}" var="element">    
    <c:out value="${element}"/>
</c:forEach>
</body>
</html>