package com.chhin.fitnesstracker.config.security.provider;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
//import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
//import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
//import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
//import org.springframework.security.oauth2.client.oidc.authentication.OidcAuthorizationCodeAuthenticationProvider;
//import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.stereotype.Component;
//
//@Component
//public class OAuth2Provider extends OidcAuthorizationCodeAuthenticationProvider {
//
//  public OAuth2Provider(
//      OAuth2UserService<OidcUserRequest, OidcUser> userService) {
//    super(new DefaultAuthorizationCodeTokenResponseClient(), userService);
//  }
//
//  @Override
//  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//    return super.authenticate(authentication);
//  }
//  @Override
//  public boolean supports(Class<?> authentication){
//    return authentication.equals(OAuth2LoginAuthenticationToken.class);
//  }
//}
