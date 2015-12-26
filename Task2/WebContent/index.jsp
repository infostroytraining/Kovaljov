<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="mytag" uri="/WEB-INF/kaptcha.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../Task2/css/bootstrap.min.css">
<link rel="stylesheet" href="../Task2/css/sweetalert.css">
<title>Insert title here</title>
</head>
<body>
	<!-- <form action="app/register" method ="Post">
	<input type = "email" name ="email" placeholder="Enter email"/><br/>
	<input type = "text" name ="firstName" placeholder="First name"/><br/>
	<input type = "text" name ="surname" placeholder="First name"/><br/>
	<input type = "password" name ="password" placeholder="Enter password"/><br/>
	<input type = "password" name ="passwordConfirm" placeholder="Confirm password"/><br/>
	<img src="kaptcha.jpg" /></br> <input type="text" name="kaptcha" value="" placeholder="enterKaptcha"/></br>
	<input type = "submit" value ="Register"/>
</form>
<c:forEach items="${sessionScope.errors}" var="element">    
    <c:out value="${element}"/>
</c:forEach>
<a href ="login.jsp">Login</a>-->
	<div class="container" style="margin-top: 30px">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Sign up </strong>
					</h3>
				</div>

				<div class="panel-body">
					<form role="form" action="app/register" onSubmit="return checkform()">
						<c:forEach items="${sessionScope.errors}" var="element">
							<div class="alert alert-danger">
								<div>
									<c:out value="${element}" />
								</div>
							</div>
						</c:forEach>
						<div class="row">
							<div class="col-xs-12 col-sm-4 col-md-12">
								<div class="form-group">
									<input type="text" name="firstName" id="first_name"
										class="form-control" placeholder="First Name"
										title="Допускаются только символы латинского алфавита, кириллица, а также пробельные символы"
										maxlength="128" required
										pattern="^([а-яА-Яa-zA-Z\s]*){1,128}$" tabindex="1">
								</div>
							</div>
							<div class="col-xs-12 col-sm-4 col-md-12">
								<div class="form-group">
									<input type="text" name="surname" id="last_name"
										class="form-control " placeholder="Last Name" tabindex="2"
										title="Допускаются только символы латинского алфавита, кириллица, а также пробельные символы"
										maxlength="128" required
										pattern="^([а-яА-Яa-zA-Z\s]*){1,128}$">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-4 col-md-12">
								<div class="form-group">
									<input type="email" name="email" id="email"
										class="form-control " placeholder="Email Address" tabindex="4"
										required>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-12">
								<div class="form-group">
									<input type="password" name="password" id="password"
										class="form-control " placeholder="Password" tabindex="5"
										title="пароль должен содержать 8 символов, 
										из них хотя бы одна маленькая буква, 
										хотя бы одна большая буква, хотя бы одна цифра"
										maxlength="128" required
										pattern="(?=.*\d)(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{8,128}">
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-12	">
								<div class="form-group">
									<input type="password" name="passwordConfirm"
										id="password_confirmation" class="form-control "
										placeholder="Confirm Password" tabindex="6"
										title="Пароли должны совпадать" maxlength="128" required
										pattern="(?=.*\d)(?=.*[a-zа-я])(?=.*[A-ZА-Я]).{8,128}">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-md-12">
								<div class="form-group">
									<img src="kaptcha.jpg" />
								</div>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-12">
								<div class="form-group">
									<input type="text" name="kaptcha" value="" class="form-control"
										placeholder="Enter Kaptcha" required/>
								</div>
							</div>


						</div>
						<button id="submit-button" class="btn btn-success">Sign in</button>

						<hr style="margin-top: 10px; margin-bottom: 10px;">
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="../Task2/js/jquery-2.1.4.min.js"></script>
	<script src="../Task2/js/bootstrap.min.js"></script>
	<script src="../Task2/js/sweetalert.min.js"></script>
	<script src="../Task2/js/app.js"></script>
</body>
</html>