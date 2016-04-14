<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Casa do Código</title>
		
		<link rel="stylesheet" href="/casadocodigo/resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="/casadocodigo/resources/css/bootstrap-theme.min.css">
		
		<style type="text/css">			
			body {
				padding: 60px 0px;
			}
		</style>
	</head>
	
	<body>
		<div class="container">
		
			<h1>Login</h1>
			
			<form:form servletRelativeAction="/login" method="POST">
				<div class="form-group">
					<label> Email </label>
					<input name="username" type="text" class="form-control"/>
				</div>
				
				<div class="form-group">
					<label> Senha </label>
					<input name="password" type="password" class="form-control"/>
				</div>
				
				<br/><br/>
				
				<button type="submit" class="btn btn-primary"> Acessar </button>
			</form:form>
		</div>				
	</body>
</html>