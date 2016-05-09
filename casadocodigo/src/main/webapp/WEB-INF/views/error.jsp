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
	
		<section id="index-section" class="container middle">
			<h1>${exception.message}</h1>
		</section>		
	</body>
</html>
