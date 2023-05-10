package com.spring.micro.service.config.interceptor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestTemplate;




public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
	/*
	 * The RestTemplate is a class provided by Spring Framework that is used to make
	 * RESTful API calls. When making HTTP requests, it sends an HTTP request
	 * message to a server and receives an HTTP response message.
	 * 
	 * The ClientHttpRequestInterceptor interface allows you to intercept and modify
	 * HTTP requests and responses that are processed by the RestTemplate.
	 * 
	 * However, the RestTemplate does not intercept requests directly after
	 * implementing ClientHttpRequestInterceptor. You need to create an instance of
	 * the RestTemplateInterceptor class and add it to the list of interceptors for
	 * the RestTemplate using the setInterceptors() method.
	 * 
	 * In the given code, a RestTemplate bean is created, and an instance of
	 * RestTemplateInterceptor class is added to the list of interceptors for this
	 * RestTemplate bean. This interceptor intercepts the HTTP requests and modifies
	 * them before sending them to the server. The RestTemplate then sends these
	 * modified HTTP requests to the server.
	 */
	
	private Logger logger = LoggerFactory.getLogger(RestTemplate.class);
	
	private OAuth2AuthorizedClientManager auth2AuthorizedClientManager;
	
	
	public RestTemplateInterceptor(OAuth2AuthorizedClientManager auth2AuthorizedClientManager) {
		this.auth2AuthorizedClientManager = auth2AuthorizedClientManager;
	}



	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String token =  auth2AuthorizedClientManager
												.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build())
												.getAccessToken()
												.getTokenValue();
		logger.info("Rest template inceptor : "+token);
		
		request.getHeaders().add("Authorization", "Bearer "+token);
		
		
		return execution.execute(request, body);
	}

}
