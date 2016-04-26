package br.com.casadocodigo.loja.builders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.models.Preco;
import br.com.casadocodigo.loja.models.Produto;

public class ProdutoBuilder {
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public ProdutoBuilder(Produto produto) {
		produtos.add(produto);
	}

	public static ProdutoBuilder newProduto(TipoPreco tipoPreco, BigDecimal valor){
		Produto produto = create("Book 1", tipoPreco, valor);
		return new ProdutoBuilder(produto);		
	}

	private static Produto create(String titulo, TipoPreco tipoPreco, BigDecimal valor) {
		Produto produto = new Produto();
		produto.setTitulo(titulo);
		produto.setDataPublicacao(Calendar.getInstance());
		produto.setNumeroPaginas(150);
		produto.setDescricao("great book about testing");
		
		Preco preco = new Preco();
		preco.setTipo(tipoPreco);
		preco.setValor(valor);
		
		produto.getPrecos().add(preco);	
		return produto;
	}
	
	public ProdutoBuilder mais(int quantidade){
		Produto base = produtos.get(0);
		Preco preco = base.getPrecos().get(0);
		
		for(int i = 0 ; i < quantidade ; i++){
			produtos.add(create("Book"+1, preco.getTipo(), preco.getValor()));
		}
		return this;
	}

	public List<Produto> buildAll() {
		return produtos;
	}
	
	public Produto buildOne() {
		return produtos.get(0);
	}
}