package br.com.casadocodigo.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping("/")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtoDAO.listar());
		return modelAndView;
	}
}