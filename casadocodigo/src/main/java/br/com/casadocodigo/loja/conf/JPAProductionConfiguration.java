package br.com.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("prod")
public class JPAProductionConfiguration {

	@Autowired								// o Heroku disponibiliza uma variável de ambiente para configurações
	private Environment environment;		// essa variavel de ambiente vai ser carregada no momento da inicialização da app neste atributo Environment
	
	@Bean
	public Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.ProstgreSQLDialect");	// produção (Heroku) usa banco Postgre
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
	
	@Bean
	private DataSource getDataSource() throws URISyntaxException {
																		    // DATABASE_URL: variável de ambiente disponibilizada pelo Heroku
		String property = environment.getProperty("DATABASE_URL");			// property vem no formato: usuario:senha@host:port/path
		URI dbUri = new URI(property);

		String urlDbConnection = "jdbc:postgre://"+ dbUri.getHost() +":"+ dbUri.getPort() + dbUri.getPath();
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();		
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(urlDbConnection);
		dataSource.setUsername(dbUri.getUserInfo().split(":")[0]);		// dbUri.getUserInfo() vem no formato usuario:senha
		dataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
		return dataSource;
	}
}