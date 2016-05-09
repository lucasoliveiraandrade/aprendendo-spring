package br.com.casadocodigo.loja.controller;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
		
	@InitBinder			// validator do spring
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.addValidators(new ProdutoValidation());		
	}
	
	@RequestMapping(value="/novo")
	public ModelAndView mostrarFormDeNovoCadastro(Produto produto){
		ModelAndView modelAndView = new ModelAndView(getPageUrl("novo"));
		modelAndView.addObject("tipos", TipoPreco.values());		
		return modelAndView;
	}
	
	@CacheEvict(value="produtosHome", allEntries=true) 		// remove o cache armazenado do "produtosHome" para que ele possa reconhecer o novo produto cadastrado. Ver HomeController.   
	@RequestMapping(method=RequestMethod.POST)
	public String criar(MultipartFile sumario, @Valid Produto produto, BindingResult bindingResult){		
		if(bindingResult.hasErrors()){
			return getPageUrl("novo");
		}
		
		if(!sumario.getOriginalFilename().isEmpty()){
			produto.setSumarioPath(fileSaver.salvarArquivo("arquivos-sumario", sumario));
		}
		
		produtoDao.gravar(produto);		
		return getPageUrl("listar");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar(){
		ModelAndView modelAndView = new ModelAndView(getPageUrl("listar"));
		modelAndView.addObject("produtos", produtoDao.listar());
		return modelAndView;		
	}
	
	@RequestMapping(value="/detalhe/{produtoId}")
	public ModelAndView mostrarDetalhes(@PathVariable("produtoId") Integer produtoId){
		ModelAndView modelAndView = new ModelAndView(getPageUrl("detalhe"));
		modelAndView.addObject("produto", produtoDao.find(produtoId));
		return modelAndView;
	}
	
	@ExceptionHandler(NoResultException.class) 	// @ExceptionHandler => toda NoResultException lançada nessa controller cai nesse metodo 
	public ModelAndView handleNoResultException(Exception noResultException){
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", noResultException);
		return modelAndView;
	}
	
	@ExceptionHandler(Exception.class)		// @ExceptionHandler => toda Exception lançada nessa controller cai nesse metodo
	public ModelAndView handlerException(){
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", new Exception("Erro específico do ProdutoController"));
		return modelAndView;
	}
	
	private String getPageUrl(String view){
		return "produtos/" + view;
	}
}
