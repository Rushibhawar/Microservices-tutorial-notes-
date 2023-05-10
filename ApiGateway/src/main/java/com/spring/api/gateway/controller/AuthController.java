package com.spring.api.gateway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.api.gateway.models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	
	/*
	 * This is a Java class named AuthController that contains a REST endpoint for
	 * user authentication. The endpoint is mapped to /auth/login and accepts GET
	 * requests. The method login has three parameters: OAuth2AuthorizedClient,
	 * OidcUser, and Model. The @RegisteredOAuth2AuthorizedClient annotation is used
	 * to retrieve the authorized client with the name "okta" and
	 * the @AuthenticationPrincipal annotation is used to retrieve the OIDC user
	 * object. The Model parameter is used to add attributes to the view.
	 * 
	 * The login method retrieves the user's email and authorities from the OIDC
	 * user object. It then creates an AuthResponse object and sets the userId,
	 * accessToken, refreshToken, expireAt, and authorities fields using the values
	 * retrieved from the OIDC user and authorized client objects. Finally, the
	 * method returns a ResponseEntity with an HTTP status of OK and the
	 * AuthResponse object in the response body.
	 */	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient auth2AuthorizedClient,
			@AuthenticationPrincipal OidcUser oidcUser,
			Model model
			){
		logger.info("user email : {}",oidcUser.getEmail());
		
		List<String> authorities = oidcUser.getAuthorities().stream().map(grantAuthoritie -> {
			return grantAuthoritie.getAuthority();
		}).collect(Collectors.toList());
		
		//creating AuthResponse object and setting userId and accessToken
		AuthResponse authResponse = AuthResponse
												.builder()
												.userId(oidcUser.getEmail())
												.accessToken(auth2AuthorizedClient.getAccessToken().getTokenValue())
												.refreshToken(auth2AuthorizedClient.getRefreshToken().getTokenValue())
												.expireAt(auth2AuthorizedClient.getAccessToken().getExpiresAt().getEpochSecond())
												.authorities(authorities)
												.build();
		
		return ResponseEntity.status(HttpStatus.OK).body(authResponse);
		
	}
	
}
