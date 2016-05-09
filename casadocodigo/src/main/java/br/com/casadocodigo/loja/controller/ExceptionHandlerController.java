package br.com.casadocodigo.loja.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice		// @ControllerAdvice indica que essa controller é específica para tratamento das exceptions lançadas por todo o sistema
public class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)										// Toda Exception lançada em todo o sistema cai aqui nesse metodo q direciona para a pagina de erro.  
	public ModelAndView handlerDefaultException(Exception exception){		// A menos que na controller onde a Exception foi lançada ja tenha um metodo anotado com @ExceptionHandler(Exception.class) 
		exception.printStackTrace();
		
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}	 
}