package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.daos.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UsuarioDAO usuarioDao;	

	@Override 		// definindo as permissões de acesso de cada URL. A order das definições influencia. 
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
					.antMatchers("/produtos/novo").hasRole("ADMIN")			// internamente ele ja espera que tenha ROLE_ como prefixo, ou seja, no banco tem que estar ROLE_ADMIN
					.antMatchers("/carrinho/**").permitAll()
					.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
					.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
					.antMatchers("/produtos/**").permitAll()
					.antMatchers("/resources/**").permitAll()		// liberando acesso aos arquivos do bootstrap
					.antMatchers("/").permitAll()
					.anyRequest().authenticated()
					.and().formLogin().loginPage("/login").permitAll() 		// informando o spring para usar a nossa pagina de login e não a default
					.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));		// informando o spring a URL que será usada para fazer o logout
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		authManagerBuilder.userDetailsService(usuarioDao).passwordEncoder(new BCryptPasswordEncoder());		// definindo o encriptador da senha como sendo o default do spring
	}
}