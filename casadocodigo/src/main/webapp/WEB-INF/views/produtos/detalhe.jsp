<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
	<head>
	 	<meta charset="utf-8"/>
	  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		
		<title> ${produto.titulo} - Casa do Código</title>
	</head>
	
	<body class="produto">	
	  	<header id="layout-header">
		  	<div class="clearfix container">
				<div id="header-content">
					<nav id="main-nav">						
						<ul class="clearfix">
							<li><a href="/carrinho" rel="nofollow">Carrinho (${carrinhoCompras.quantidadeItensNoCarrinho})</a></li>	
						</ul>
					</nav>
				</div>
			</div>
		</header>
		
		<article id="${produto.id}">
			 <header id="product-highlight" class="clearfix">
				<div id="product-overview" class="container">
				      <img width="180px" height="295px" src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145" class="product-featured-image" />
				      <h1 class="product-title">${produto.titulo}</h1>				      
				    <p class="book-description"> ${produto.descricao}</p>
				</div>
			</header>			
			  
			<section>  
				<form:form servletRelativeAction="/casadocodigo/carrinho/add" method="post" cssClass="container">  <!-- form:form servletRelativeAction evita erro de 'invalid csrf token'  -->
				    <ul id="variants" class="clearfix">
					    <input type="hidden" value="${produto.id}" name="produtoId">
					    <c:forEach items="${produto.precos}" var="preco">
					    	  <li class="buy-option">
					            <input type="radio" name="tipoPreco" class="variant-radio" checked="checked" id="tipo" value="${preco.tipo}" /> 
					            
					            <label class="variant-label"> ${preco.tipo} </label>
					            <p class="variant-price"> ${preco.valor}</p>
					        </li>           
					    </c:forEach>
				    </ul>
				    <button type="submit"> Compre Agora ${produto.titulo}!</button>
				</form:form>			  
			</section>
			  
			<div class="container">				  
				  <section class="data product-detail">
					    <h3 class="section-title">Dados do livro:</h3>
					    <p>Número de páginas: <span>${produto.numeroPaginas}</span></p>
					    <p>Data de publicação: <fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataPublicacao.time}"/> </p>
				  </section>
			</div>		
		</article>	
	</body>
</html>