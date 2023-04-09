package com.chhin.fitnesstracker.config.security;

import com.chhin.fitnesstracker.FTRuntimeException;
import com.chhin.fitnesstracker.config.security.login.FTClientAuthenticationDetailsSource;
import com.chhin.fitnesstracker.config.security.login.handler.WebSecurityLoginSuccessHandler;
import com.chhin.fitnesstracker.config.security.provider.JdbcProvider;
import com.chhin.fitnesstracker.service.FTUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private static final String LOGIN = "/login";
  private final FTUserService ftUserService;
  private final FTClientAuthenticationDetailsSource ftClientAuthenticationDetailsSource;

  public WebSecurityConfig(FTUserService ftUserService,
                           FTClientAuthenticationDetailsSource ftClientAuthenticationDetailsSource) {
    this.ftUserService = ftUserService;
    this.ftClientAuthenticationDetailsSource = ftClientAuthenticationDetailsSource;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      HttpSecurity httpSecurity, JdbcProvider jdbcProvider)
      throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(jdbcProvider).build();

  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                         AuthenticationManager authenticationManager) throws Exception {

    httpSecurity.authorizeHttpRequests(
        (auth) -> auth.requestMatchers("/**").permitAll().requestMatchers("/webjars/**")
            .permitAll().and()
            .authenticationManager(authenticationManager));

    httpSecurity.authorizeHttpRequests(
        (auth) -> {
          try {
            auth.anyRequest().authenticated().and().formLogin().loginPage(LOGIN)
                .loginProcessingUrl(LOGIN).successHandler(webSecurityLoginSuccessHandler())
                .defaultSuccessUrl("/dashboard")
                .failureUrl(LOGIN).authenticationDetailsSource(ftClientAuthenticationDetailsSource)
                .permitAll();
          } catch (Exception e) {
            throw new FTRuntimeException("Could not setup login page", e);
          }
        }
    );

    httpSecurity.logout().clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
        .deleteCookies("JSESSIONID").invalidateHttpSession(true);
    httpSecurity.sessionManagement().maximumSessions(1).expiredUrl("/").and()
        .invalidSessionUrl("/");
    httpSecurity.sessionManagement().sessionFixation().migrateSession();

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebSecurityLoginSuccessHandler webSecurityLoginSuccessHandler() {
    return new WebSecurityLoginSuccessHandler(ftUserService);
  }

}
