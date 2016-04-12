<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		
		<title>Casa do Código</title>
		
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
	</head>
	
	<body>	
	  	<header id="layout-header">
			<div class="clearfix container">
				<div id="header-content">
					<nav id="main-nav">						
						<ul class="clearfix">
							<li><a href="/casadocodigo/produtos">Lista de Produtos</a></li>
							<li><a href="/casadocodigo/produtos/novo">Cadastro de Produtos</a></li>
							<li><a href="/cart" rel="nofollow">Carrinho</a></li>	
						</ul>
					</nav>
				</div>
			</div>
		</header>
		
		<section id="index-section" class="container middle">
			<h1 class="cdc-call">Produtos cadastrados</h1>
			<ul class="clearfix book-collection">
				
				<c:forEach items="${produtos}" var="produto">
					<li>
						<a href="/casadocodigo/produtos/detalhe/${produto.id}" class="block clearfix">
							<h2 class="product-title">${produto.titulo}</h2>
							<img width="143" height="202" src="https://cdn.shopify.com/s/files/1/0155/7645/products/java8-featured_large.png?v=1411490181" alt="Java 8 Prático" title="Java 8 Prático"/>
							<small class="buy-button">Compre</small>
						</a>
					</li>
				</c:forEach>				
			</ul>
		</section>	
	</body>
</html>