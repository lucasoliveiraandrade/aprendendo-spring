<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>	<!-- taglib de segurança do spring -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><fmt:message key="pagina.titulo"/></title>
		
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
					<a class="navbar-brand" href="/casadocodigo/"><fmt:message key="cabecalho.logotipo"/></a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/casadocodigo/produtos"><fmt:message key="cabecalho.listaDeProdutos"/></a></li>
						<li><a href="/casadocodigo/produtos/novo"><fmt:message key="cabecalho.cadastroDeProdutos"/></a></li>					
					</ul>
					<ul class="nav navbar-nav navbar-right">		<!-- exibindo o nome do usuario logado -->
						<li>
							<a href="#">
								<security:authentication property="principal" var="usuario"/> 	<!-- principal é a referencia default do spring para o usuario logado -->
								${usuario.username}
							</a>
						</li>
						<li>
							<a href="/casadocodigo/logout"><fmt:message key="cabecalho.sair"/></a>
						</li>
						<li>
							<a href="/casadocodigo/?locale=pt_BR" rel="nofollow">
								<fmt:message key="cabecalho.idioma.portugues"></fmt:message>
							</a>
						</li>
						<li>
							<a href="/casadocodigo/?locale=en_US" rel="nofollow">
								<fmt:message key="cabecalho.idioma.ingles"></fmt:message>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</nav>	
	
		<div class="container">
			<div><h3>${mensagem}</h3></div>
			
			<h1><fmt:message key="produtos.listar.tituloDaPagina"/></h1>
			
			<table class="table table-bordered table-striped table-hover">
				<tr>
					<th>	<fmt:message key="produtos.listar.tabela.titulo"/> </th>
					<th> <fmt:message key="produtos.listar.tabela.descricao"/> </th>
					<th> <fmt:message key="produtos.listar.tabela.numeroDePaginas"/> </th>
					<th> <fmt:message key="produtos.listar.tabela.precos"/> </th> 
					<th> <fmt:message key="produtos.listar.tabela.dataPublicacao"/></th>
				</tr>
				
				<c:forEach items="${produtos}" var="produto">
					<tr>
						<td><a href="/casadocodigo/produtos/detalhe/${produto.id}"> ${produto.titulo} </a></td>
						<td> ${produto.descricao} </td>
						<td> ${produto.numeroPaginas} </td>
						<td> ${produto.precos } </td>
						<td> ${produto.dataPublicacao.getTimeInMillis()}> </td>
					</tr>
				</c:forEach>			
			</table>
		</div>		
	</body>
</html>