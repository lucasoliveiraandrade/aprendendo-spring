<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><fmt:message key="pagina.titulo"/></title>
		
		<link rel="stylesheet" href="/casadocodigo/resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="/casadocodigo/resources/css/bootstrap-theme.min.css">
		
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
		
			<h1><fmt:message key="produtos.novo.tituloDaPagina"/></h1>
			<!-- 
				action: URL na qual o form será submetido
				method: metodo html usado para submeter o form
				commandName: objeto java que representa o form
				enctype: informa o spring que na requisição tambem será enviado um arquivo (campo sumário) 
			-->
			<form:form action="/casadocodigo/produtos" method="POST" commandName="produto" enctype="multipart/form-data">
				<div class="form-group">
					<label> <fmt:message key="produtos.novo.titulo"/> </label>
					<form:input path="titulo" cssClass="form-control"/>
					<form:errors path="titulo" />
				</div>
				
				<div class="form-group">
					<label><fmt:message key="produtos.novo.descricao"/> </label>
					<form:textarea path="descricao" cssClass="form-control"/>
					<form:errors path="descricao"/>
				</div>
				
				<div class="form-group">
					<label><fmt:message key="produtos.novo.numeroPaginas"/></label>
					<form:input path="numeroPaginas" cssClass="form-control"/>
					<form:errors path="numeroPaginas"/> 
				</div>
				
				<div class="form-group">
					<label><fmt:message key="produtos.novo.dataPublicacao"/></label>
					<form:input path="dataPublicacao" cssClass="form-control" />
					<form:errors path="dataPublicacao"/>
				</div>			
				
				<c:forEach items="${tipos}" var="tipo" varStatus="status">
					<div class="form-group">
						<label>${tipo}</label>
						<form:input path="precos[${status.index}].valor" cssClass="form-control" />
						<form:hidden path="precos[${status.index}].tipo" value="${tipo}" />
					</div>		
				</c:forEach>
				
				<div class="form-group">
					<label><fmt:message key="produtos.novo.sumario"/></label>
					<input type="file" name="sumario" class="form-control"> 
				</div>
				
				<br/><br/>
				
				<button type="submit" class="btn btn-primary"><fmt:message key="produtos.novo.botao.cadastrar"/></button>
			</form:form>
		</div>				
	</body>
</html>