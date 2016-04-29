package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.config.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.enums.TipoPreco;
import br.com.casadocodigo.loja.models.Produto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class })	// classes necessarias para a execução desses testes
@ActiveProfiles("test") 	// ativando o profile de test definido na classe DataSourceConfigurationTest.java pro spring mudar o banco 
public class ProdutoDAOTest {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Test
	@Transactional
	public void deveSomarPrecosPorTipo()	{
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, new BigDecimal(10)).mais(3).buildAll();
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, new BigDecimal(10)).mais(3).buildAll();
		
		livrosImpressos.stream().forEach(produtoDao::gravar);		//jdk8
		livrosEbook.stream().forEach(produtoDao::gravar);
		
		BigDecimal valorSoma = produtoDao.somaPrecosPorTipo(TipoPreco.IMPRESSO);
		
		Assert.assertEquals(valorSoma, new BigDecimal(40).setScale(2));
	}
}