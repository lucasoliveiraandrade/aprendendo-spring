<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
	<head>
	  	<meta charset="utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
		
		<title>Casa do Código</title>
	</head>
	
	<body>
	  	<header id="layout-header">
			<div class="clearfix container">
				<a href="/" id="logo"></a>
				<div id="header-content">
					<nav id="main-nav">
						<ul class="clearfix">
							<li><a href="/casadocodigo/carrinho" rel="nofollow">Carrinho (${carrinhoCompras.quantidadeItensNoCarrinho} )</a></li>
							<li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre Nós</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		
		<section class="container middle">
		    <h2 id="cart-title">Seu carrinho de compras</h2>
		    <table id="cart-table">
			      <colgroup>
				        <col class="item-col"/>
				        <col class="item-price-col"/>
				        <col class="item-quantity-col"/>
				        <col class="line-price-col"/>
				        <col class="delete-col"/>
			      </colgroup>
			      <thead>
			        <tr>
				          <th class="cart-img-col"></th>
				          <th width="65%">Item</th>
				          <th width="10%">Preço</th>
				          <th width="10%">Quantidade</th>
				          <th width="10%">Total</th>
				          <th width="5%"></th>
			        </tr>
			      </thead>
			      <tbody>
				      	<c:forEach items="${carrinhoCompras.itensCarrinho}" var="itemCarrinho">
						   <tr>
						          <td class="cart-img-col"><img src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145" width="71px" height="100px"/></td>
						          <td class="item-title"> ${itemCarrinho.produto.titulo} </td>
						          <td class="numeric-cell">${itemCarrinho.tipoPreco}</td>
						          <td class="quantity-input-cell">
						          	<input type="number" min="0" readonly="readonly" id="quantidade" name="quantidade" value="${carrinhoCompras.getQuantidadeItensNoCarrinho(itemCarrinho)}"/>
						          </td>
						          <td class="numeric-cell">${carrinhoCompras.getTotal(itemCarrinho)}</td>
						          <td class="remove-item">
							          <form action="carrinho/remover/${itemCarrinho.produto.id}/${itemCarrinho.tipoPreco}" method="post">
						          		<input type="image" src="${contextPath}resources/imagens/excluir.png" alt="Excluir" title="Excluir" />
							          </form>
						          </td>
						   </tr>
				      	</c:forEach>
			      </tbody>
			      <tfoot>
				        <tr>
				        	<form action="pagamento/finalizar" method="post">
				          	<td colspan="3"><input type="submit" class="checkout" name="checkout" value="Finalizar compra" /></td>
				          </form>
				          
				          <td class="numeric-cell">${carrinhoCompras.getTotal()}</td>
				          <td></td>
				        </tr>
			      </tfoot>
		    </table>
		</section>			
	</body>
</html>