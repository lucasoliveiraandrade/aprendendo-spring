package br.com.casadocodigo.loja.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controller.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;

@EnableCaching		// ativando o gerenciamento de cache do spring
@EnableWebMvc		
@ComponentScan(basePackageClasses={ HomeController.class, ProdutoDAO.class, FileSaver.class, Produto.class })  	// informando o Spring os pacotes de classes da aplicação. Ele pega os pacotes de cada classe, basePackageClasses. 
public class AppWebConfiguration {
	
	// informando o spring onde encontrar as views jsp
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setExposedContextBeanNames("carrinhoCompras"); //deixando o objeto disponível na JSP
		return resolver;
	}
	
	// informando o spring onde encontrar o arquivo messages.properties
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);		
		return messageSource;
	}
	
	// informando o spring o formato dos campos tipo Calendar
	@Bean
	public FormattingConversionService mvcConversionService(){
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		
		DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
		dateFormatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		dateFormatterRegistrar.registerFormatters(conversionService);
		
		return conversionService;
	}
	
	// configuração para upload de arquivo na tela 
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();		
	}
	
	//configuração para uso do Jackson para conversão de objetos para Json
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	// configuração para uso de cache usando o framework Guava do Google
	@Bean
	public CacheManager cacheManager(){
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
		
		GuavaCacheManager guavaCacheManager = new GuavaCacheManager();
		guavaCacheManager.setCacheBuilder(cacheBuilder);
		return guavaCacheManager;		
	}
}
