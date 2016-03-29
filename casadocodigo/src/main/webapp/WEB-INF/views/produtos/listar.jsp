<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Livros sobre Java, Android, iPhone, Ruby e muito mais - Casa do Código</title>
	</head>
	<body>
		<div>
			<h3>${mensagem}</h3>
		</div>
		
		<br/><br/>
	
		<h1>Listagem de Produtos</h1>
		
		<table>
			<tr>
				<td>	Título </td>
				<td> Descrição </td>
				<td> Número de Páginas </td> 
				<td> Data Publicação</td>
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
	</body>
</html>