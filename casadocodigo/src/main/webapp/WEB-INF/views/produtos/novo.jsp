<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Livros sobre Java, Android, iPhone, Ruby e muito mais - Casa do Código</title>
	</head>
	<body>
		
		<!-- 
			action: URL na qual o form será submetido
			method: metodo html usado para submeter o form
			commandName: objeto java que representa o form
			enctype: informa o spring que na requisição tambem será enviado um arquivo (campo sumário) 
		-->
		<form:form action="/casadocodigo/produtos" method="POST" commandName="produto" enctype="multipart/form-data">
			<div>
				<label> Titulo </label>
				<form:input path="titulo"/>
				<form:errors path="titulo" />
			</div>
			
			<div>
				<label> Descrição </label>
				<form:textarea path="descricao" rows="2"/>
				<form:errors path="descricao"/>
			</div>
			
			<div>
				<label>Número paginas </label>
				<form:input path="numeroPaginas"/>
				<form:errors path="numeroPaginas"/> 
			</div>
			
			<div>
				<label> Data publicação </label>
				<form:input path="dataPublicacao" />
				<form:errors path="dataPublicacao"/>
			</div>			
			
			<c:forEach items="${tipos}" var="tipo" varStatus="status">
				<div>
					<label>${tipo}</label>
					<form:input path="precos[${status.index}].valor" />
					<form:hidden path="precos[${status.index}].tipo" value="${tipo}" />
				</div>		
			</c:forEach>
			
			<div>
				<label>Sumário</label>
				<input type="file" name="sumario"> 
			</div>
			
			<br/><br/>
			
			<button type="submit"> Cadastrar Novo Livro </button>
		</form:form>
				
	</body>
</html>