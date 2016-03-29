package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.CarrinhoItem;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinhoCompras;
	
	@RequestMapping(value="/add")
	public ModelAndView adicionarNovoProduto(Integer produtoId, TipoPreco tipoPreco){
		carrinhoCompras.add(getCarrinhoItem(produtoId, tipoPreco));
		return new ModelAndView("redirect:/carrinho");	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens(){
		return new ModelAndView("carrinho/itens");
	}
	
	@RequestMapping("/remover/{produtoId}/{tipoPreco}")
	public ModelAndView removerCarrinhoItem(@PathVariable("produtoId") Integer produtoId, @PathVariable("tipoPreco") TipoPreco tipoPreco){
		carrinhoCompras.removeItem(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho");
	}
	 
	private CarrinhoItem getCarrinhoItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(produtoId);
		return new CarrinhoItem(produto, tipoPreco);		
	}
}