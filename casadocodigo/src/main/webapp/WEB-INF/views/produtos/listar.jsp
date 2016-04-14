<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>	<!-- taglib de segurança do spring -->

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Casa do Código</title>
		
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
		
		<style type="text/css">			
			body {
				padding: 60px 0px;
			}
		</style>
	</head>
	
	<body>
	
		<!-- barra de navegação -->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>				
					<a class="navbar-brand" href="/casadocodigo/"> Casa do Código</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/casadocodigo/produtos">Lista de Produtos</a></li>
						<li><a href="/casadocodigo/produtos/novo">Cadastro de Produtos</a></li>					
					</ul>
					<ul class="nav navbar-nav navbar-right">		<!-- exibindo o nome do usuario logado -->
						<li>
							<a href="#">
								<security:authentication property="principal" var="usuario"/> 	<!-- principal é a referencia default do spring para o usuario logado -->
								${usuario.username}
							</a>
						</li>
						<li>
							<a href="/casadocodigo/logout">Logout</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>	
	
		<div class="container">
			<div><h3>${mensagem}</h3></div>
			
			<h1>Listagem de Produtos</h1>
			
			<table class="table table-bordered table-striped table-hover">
				<tr>
					<th>	Título </th>
					<th> Descrição </th>
					<th> Número de Páginas </th> 
					<th> Data Publicação</th>
				</tr>
				
				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td><a href="/casadocodigo/produtos/detalhe/${produto.id}"> ${produto.titulo} </a></td>
						<td> ${produto.descricao} </td>
						<td> ${produto.numeroPaginas} </td>
						<td> ${produto.dataPublicacao.getTimeInMillis()}> </td>
					</tr>
				</c:forEach>			
			</table>
		</div>		
	</body>
</html>