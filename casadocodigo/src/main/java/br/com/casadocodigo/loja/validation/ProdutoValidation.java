package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object produtoObj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required.produto.titulo");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required.produto.descricao");
		
		Produto produto = (Produto) produtoObj;
		if(produto.getNumeroPaginas() <= 0){
			errors.rejectValue("numeroPaginas", "field.required.produto.numeroPaginas");
		}
	}
}