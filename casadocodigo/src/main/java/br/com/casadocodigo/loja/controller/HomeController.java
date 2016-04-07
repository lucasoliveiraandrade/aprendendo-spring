package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;
											// Informando o spring que ele deve colocar o resultado desse metodo em cache para evitar chamadas ao banco.
	@Cacheable(value="produtosHome")		// Ele trabalha na forma de chave-valor (map). O atributo Value é o nome do map, pode ser qq nome. 
	@RequestMapping("/")					// Ver @CacheEvict no criar produto do ProdutosController
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtoDAO.listar());
		return modelAndView;
	}
}