package com.chhin.fitnesstracker.config;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.service.FTUserService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:messages.properties")
public class ApplicationConfig implements WebMvcConfigurer {
  @Bean
  @SessionScope
  public LoggedInUserService loggedInUserService(FTUserService ftUserService) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String name = "";
    if (authentication instanceof OAuth2AuthenticationToken) {
      name = ((DefaultOidcUser) authentication.getPrincipal()).getAttribute("email");
    } else {
      name = authentication.getName();
    }
    Integer anInt = ftUserService.findByUsernameOptional(name).map(FTUser::getUserId).orElse(null);
    return new LoggedInUserService(anInt, ftUserService);
  }
}
