<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../Task2/css/bootstrap.min.css">
<link rel="stylesheet" href="../Task2/css/style.css">
</head>
<body>
<div class="container">
        <div class="card card-container">
            <!-- <img class="profile-img-card" src="//lh3.googleusercontent.com/-6V8xOA6M7BA/AAAAAAAAAAI/AAAAAAAAAAA/rzlHcD0KYwo/photo.jpg?sz=120" alt="" /> -->
            <img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <form action="app/login" class="form-signin">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="email" id="inputEmail" name ="email" class="form-control" placeholder="Email address" required autofocus>
                <input type="password" name ="password" id="inputPassword" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Sign in</button>
            </form><!-- /form -->
             <div class="form-group">
                            <p class="text-center m-t-xs text-sm">Do not have an account?</p> 
                            <a href="Task2/index.jsp" class="btn btn-default btn-block m-t-md ">Create an account</a>
                        </div>
        </div><!-- /card-container -->
    </div>
<c:forEach items="${sessionScope.errors}" var="element">    
    <c:out value="${element}"/>
</c:forEach>
<script src="../Task2/js/jquery-2.1.4.min.js"></script>
<script src="../Task2/js/bootstrap.min.js"></script>
<script src="../Task2/js/sweetalert.js"></script>
</body>
</html>