package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.enums.TipoPreco;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)  		//esse proxyMode diz ao Spring para ele colocar todas as controllers 
public class CarrinhoCompras implements Serializable {									   		//que usam essa classe como scopo de request: @Scope(value=WebApplicationContext.SCOPE_REQUEST).
	private static final long serialVersionUID = 1L;									   		//dessa forma a gente não precisa se preocupar em anotar todas as controllers com essa anotação
	
	private Map<CarrinhoItem, Integer> itensCarrinho = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem carrinhoItem){
		itensCarrinho.put(carrinhoItem, getQuantidadeItensNoCarrinho(carrinhoItem) + 1);
	}

	public Integer getQuantidadeItensNoCarrinho(CarrinhoItem carrinhoItem) {
		if(!itensCarrinho.containsKey(carrinhoItem)){
			itensCarrinho.put(carrinhoItem, 0);
		}		
		return itensCarrinho.get(carrinhoItem);
	}
	
	public Integer getQuantidadeItensNoCarrinho(){
		return itensCarrinho.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

	public Collection<CarrinhoItem> getItensCarrinho() {
		return itensCarrinho.keySet();
	}	
	
	public BigDecimal getTotal(CarrinhoItem carrinhoItem){
		Integer quantidadeItensNoCarrinho = getQuantidadeItensNoCarrinho(carrinhoItem);
		return carrinhoItem.getTotal(quantidadeItensNoCarrinho == null ? 0 : quantidadeItensNoCarrinho);
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for(CarrinhoItem carrinhoItem : itensCarrinho.keySet()){
			total = total.add(getTotal(carrinhoItem));
		}
		return total;		
	}

	public void removeItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto(produtoId);
		itensCarrinho.remove(new CarrinhoItem(produto, tipoPreco));
	}
}