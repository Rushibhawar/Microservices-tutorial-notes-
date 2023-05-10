package com.spring.api.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	/*
	 * This is a method in a Spring Boot application that defines a
	 * SecurityWebFilterChain for handling security-related tasks. The method takes
	 * an instance of ServerHttpSecurity as a parameter, which is used to configure
	 * the security for the application.
	 * 
	 * In this particular implementation, the method authorizes all exchanges with
	 * any endpoint in the application to be authenticated. The method also
	 * configures the application to use OAuth2 for both the client and resource
	 * server. Additionally, it sets up the application to use JWT for token
	 * verification and authentication. Finally, the method returns the
	 * SecurityWebFilterChain that has been built with the provided configuration.
	 */
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
		httpSecurity
					.authorizeExchange()
					.anyExchange()
					.authenticated()
					.and()
					.oauth2Client()
					.and()
					.oauth2ResourceServer()
					.jwt();
		
		
		return httpSecurity.build();
	}
}
