package br.agenda3.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.httpBasic().and().csrf().disable().authorizeRequests().antMatchers(
	 * "/private/**").permitAll().anyRequest().authenticated()
	 * .and().formLogin().loginProcessingUrl("/usuarioLogon")
	 * .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").
	 * invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll()
	 * .and().exceptionHandling().accessDeniedPage("/");
	 * 
	 * http.csrf().disable();
	 * 
	 * http.sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	 * 
	 * http.sessionManagement().maximumSessions(1).expiredUrl("/semSessao").and().
	 * invalidSessionUrl("/");
	 * 
	 * }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, "/usuarioLogon")/* .permitAll()
			.anyRequest().authenticated() */.access("hasRole('USER')")
			.and()

			// filtra requisições de login
			.addFilterBefore(new JWTLoginFilter("/usuarioLogon", authenticationManager()),
			UsernamePasswordAuthenticationFilter.class)

			// filtra outras requisições para verificar a presença do JWT no header
			.addFilterBefore(new JWTAuthenticationFilter(),
			UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()/* .passwordEncoder(new BCryptPasswordEncoder()) */.dataSource(dataSource)
				.usersByUsernameQuery(
						"select login as USERNAME, senha as PASSWORD, habilitado as ENABLED from usuario where login=?")

				.authoritiesByUsernameQuery("select u.login as USERNAME, p.tipo as ROLE from usuario u inner join usuario_privilegio up on" + 
				" u.id = up.usuario_id inner join privilegio p on p.id = up.privilegio_id where u.login =?");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bootstrap/**", "/css/**", "/images/**", "/javascript/**", "/jquery/**",
				"/partials/parts/**", "/partials/public/**", "/templates/**", "/logout", "/", "/home", "/cadastro", "/login", "/usuario");
	}

	/* @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	} */

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
