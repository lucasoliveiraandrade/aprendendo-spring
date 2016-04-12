<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Livros sobre Java, Android, iPhone, Ruby e muito mais - Casa do Código</title>
		
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
					<a class="navbar-brand" href="/casadocodigo/"> Casa do Código</a>
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="/casadocodigo/produtos">Lista de Produtos</a></li>
						<li><a href="/casadocodigo/produtos/novo">Cadastro de Produtos</a></li>					
					</ul>
				</div>
			</div>
		</nav>
		
		<div class="container">
		
			<h1>Cadastro de Produto</h1>
			<!-- 
				action: URL na qual o form será submetido
				method: metodo html usado para submeter o form
				commandName: objeto java que representa o form
				enctype: informa o spring que na requisição tambem será enviado um arquivo (campo sumário) 
			-->
			<form:form action="/casadocodigo/produtos" method="POST" commandName="produto" enctype="multipart/form-data">
				<div class="form-group">
					<label> Titulo </label>
					<form:input path="titulo" cssClass="form-control"/>
					<form:errors path="titulo" />
				</div>
				
				<div class="form-group">
					<label> Descrição </label>
					<form:textarea path="descricao" cssClass="form-control"/>
					<form:errors path="descricao"/>
				</div>
				
				<div class="form-group">
					<label>Número paginas </label>
					<form:input path="numeroPaginas" cssClass="form-control"/>
					<form:errors path="numeroPaginas"/> 
				</div>
				
				<div class="form-group">
					<label> Data publicação </label>
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
					<label>Sumário</label>
					<input type="file" name="sumario" class="form-control"> 
				</div>
				
				<br/><br/>
				
				<button type="submit" class="btn btn-primary"> Cadastrar Novo Livro </button>
			</form:form>
		</div>				
	</body>
</html>