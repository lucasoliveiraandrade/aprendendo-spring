package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.config.DataSourceConfigurationTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ AppWebConfiguration.class, JPAConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class })		// classes necessarias para a execução desses testes
@ActiveProfiles("test") 	// ativando o profile de test definido na classe DataSourceConfigurationTest.java pro spring mudar o banco
@WebAppConfiguration	
public class ProdutosControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	private MockMvc mockMvc;		// usando o mock do proprio spring ao inves do mockito
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
									  .addFilter(springSecurityFilterChain)		//configurando filtro do spring security no objeto MockMvc para os testes com login na app
									  .build();
	}
	
	@Test
	public void deveRedirecionarParaHomeComParametroProdutos() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
													    .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void naoDevePermitirAcessoParaRoleUsuario() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/novo")
			   .with(SecurityMockMvcRequestPostProcessors.user("user@casadocodigo.com.br").password("123").roles("USUARIO")))
			   .andExpect(MockMvcResultMatchers.status().is(403));
	}
	
	@Test
	public void devePermitirAcessoParaRoleAdmin() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/novo")
			   .with(SecurityMockMvcRequestPostProcessors.user("user@casadocodigo.com.br").password("123").roles("ADMIN")))
			   .andExpect(MockMvcResultMatchers.status().is(200));
	}
}
