package com.spring.micro.service.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.spring.micro.service.config.interceptor.RestTemplateInterceptor;

@Configuration
public class ConfigClass {

	//this is the configuration for the RestTemplate without okta auth authorization
/*	@Bean
	@LoadBalanced //we use this annotation to use the service name instead of the host name or the ip-address
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
*/
	
	@Autowired
	ClientRegistrationRepository clientRegistrationRepository;
	
	@Autowired
	OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
	
	//this is the configuration for the RestTemplate with okta auth authorization
	@Bean
	@LoadBalanced //we use this annotation to use the service name instead of the host name or the ip-address
	public RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		List<ClientHttpRequestInterceptor> inceptors =  new ArrayList<>();
		inceptors.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, auth2AuthorizedClientRepository)));
		restTemplate.setInterceptors(inceptors);
		
		return restTemplate;
	}
	
	/*
	 * This is a Java Spring Bean configuration that creates and returns an instance
	 * of the OAuth2AuthorizedClientManager class. The manager is used to manage the
	 * authorized client credentials for an OAuth2 client.
	 * 
	 * The manager takes two parameters: a client registration repository and an
	 * authorized client repository. The client registration repository stores the
	 * registered clients that the manager can use to obtain access tokens, while
	 * the authorized client repository stores the authorized client credentials.
	 * 
	 * The code creates an instance of the OAuth2AuthorizedClientProviderBuilder
	 * class and calls the clientCredentials() method to create an instance of the
	 * OAuth2AuthorizedClientProvider class that uses the client credentials grant
	 * type.
	 * 
	 * Finally, it creates an instance of the DefaultOAuth2AuthorizedClientManager
	 * class, sets the authorized client provider to the one created above, and
	 * returns the manager instance as a Spring Bean.
	 */
	@Bean
	public OAuth2AuthorizedClientManager manager(
		ClientRegistrationRepository clientRegistrationRepository,
		OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository
			) {
		//provider
		OAuth2AuthorizedClientProvider auth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		
		DefaultOAuth2AuthorizedClientManager defaultAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
		defaultAuth2AuthorizedClientManager.setAuthorizedClientProvider(auth2AuthorizedClientProvider);
		return defaultAuth2AuthorizedClientManager;
	}
}
