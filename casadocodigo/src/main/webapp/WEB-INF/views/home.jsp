<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<c:url value="/" var="contextPath" />
		<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
		<link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
		<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
		
		<title>Livros de Java, SOA, Android, iPhone, Ruby on Rails e muito mais - Casa do Código</title>
		
		<link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
		<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
		<link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
		<link href="${contextPath}resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
	     <link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"  />
		<link rel="canonical" href="http://www.casadocodigo.com.br/" />	
		<link href="${contextPath}resources/css/book-collection.css" rel="stylesheet" type="text/css" media="all" />
		
		<link rel="stylesheet" href="resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
	</head>
	
	<body>
	
	  	<header id="layout-header">
			<div class="clearfix container">
				<a href="/" id="logo"></a>
				<div id="header-content">
					<nav id="main-nav">						
						<ul class="clearfix">
							<li><a href="/casadocodigo/produtos">Lista de Produtos</a></li>
							<li><a href="/casadocodigo/produtos/novo">Cadastro de Produtos</a></li>
							<li><a href="/cart" rel="nofollow">Carrinho</a></li>	
							<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre Nós</a></li>	
						</ul>
					</nav>
				</div>
			</div>
		</header>
		
		<section id="index-section" class="container middle">
			<h1 class="cdc-call">Últimos dias com os preços promocionais. Aproveite!</h1>
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