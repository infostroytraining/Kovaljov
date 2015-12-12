<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="mytag" uri="/WEB-INF/kaptcha.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="app/register" method ="Post">
	<input type = "email" name ="email" placeholder="Enter email"/><br/>
	<input type = "text" name ="firstName" placeholder="First name"/><br/>
	<input type = "text" name ="surname" placeholder="First name"/><br/>
	<input type = "password" name ="password" placeholder="Enter password"/><br/>
	<input type = "password" name ="passwordConfirm" placeholder="Confirm password"/><br/>
	<img src="kaptcha.jpg" /></br> <input type="text" name="kaptcha" value="" placeholder="enterKaptcha"/></br>
	<input type = "submit" value ="Register"/>
</form>
</body>
</html>