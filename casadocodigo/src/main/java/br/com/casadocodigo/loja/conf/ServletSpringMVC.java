package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	// informando o spring as classes de configuração que serão carregadas junto com a aplicação no servidor
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfiguration.class, AppWebConfiguration.class, JPADevelopmentConfiguration.class, JPAProductionConfiguration.class };
	}
	
	// informando o spring quais são as classes de configuração da aplicação
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	// informando o spring a partir de qual url ele deve gerenciar as requisições 
	@Override
	protected String[] getServletMappings() { 
		return new String[] {"/"};
	}
	
	// definindo um Filtro do spring para enconding UTF-8 dos inputs das requisições
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		return new Filter[]{ encodingFilter };
	}
	
	// configuração para upload de arquivo na tela
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	// configurando o profile "dev" como sendo o padrão do servidor da aplicação
//	@Override 
//	public void onStartup(ServletContext servletContext) throws ServletException{
//		super.onStartup(servletContext);
//		servletContext.addListener(RequestContextListener.class);		// registrando um listener de contextos do spring
//		servletContext.setInitParameter("spring.profiles.active", "dev");		
//	}
}