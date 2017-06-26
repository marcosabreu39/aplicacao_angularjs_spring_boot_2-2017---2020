package br.agenda3.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*Equivalente aos nós filter e o filter mapping que seriam adicionados ao web.xml Spring Security*/
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
