package com.chhin.fitnesstracker.config.security;

import com.chhin.fitnesstracker.config.security.login.FTClientAuthenticationDetailsSource;
import com.chhin.fitnesstracker.config.security.login.handler.WebSecurityLoginSuccessHandler;
import com.chhin.fitnesstracker.config.security.provider.JdbcProvider;
import com.chhin.fitnesstracker.service.FTUserService;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private static final String LOGIN = "/login";
  private final FTUserService ftUserService;
  private final FTClientAuthenticationDetailsSource ftClientAuthenticationDetailsSource;

  public WebSecurityConfig(
      FTUserService ftUserService,
      FTClientAuthenticationDetailsSource ftClientAuthenticationDetailsSource) {
    this.ftUserService = ftUserService;
    this.ftClientAuthenticationDetailsSource = ftClientAuthenticationDetailsSource;
  }

  @Bean
  public AuthenticationManager authenticationManager(
      HttpSecurity httpSecurity, JdbcProvider jdbcProvider) throws Exception {
    return httpSecurity
        .getSharedObject(AuthenticationManagerBuilder.class)
        .authenticationProvider(jdbcProvider)
        .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final var configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("/remote"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setExposedHeaders(List.of("*"));
    final var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain filterChain(
      HttpSecurity httpSecurity, AuthenticationManager authenticationManager) throws Exception {

    //    httpSecurity.authorizeHttpRequests(
    //        auth ->
    //            auth.requestMatchers("/**")
    //                .permitAll()
    //                .requestMatchers("/webjars/**")
    //                .permitAll()
    //                .and()
    //                .authenticationManager(authenticationManager));

    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> corsConfigurationSource())
        .authorizeHttpRequests(
            (auth) ->
                auth.requestMatchers("/**")
                    .permitAll()
                    .requestMatchers("/webjars/**")
                    .permitAll()
                    .requestMatchers("/logout")
                    .permitAll())
        .formLogin(
            (login) ->
                login
                    .loginPage(LOGIN)
                    .loginProcessingUrl(LOGIN)
                    .successHandler(webSecurityLoginSuccessHandler())
                    .defaultSuccessUrl("/dashboard")
                    .failureUrl(LOGIN)
                    .authenticationDetailsSource(ftClientAuthenticationDetailsSource))
        .logout(
            (logout) ->
                logout
                    .clearAuthentication(true)
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true))
        .authenticationManager(authenticationManager)
        .sessionManagement(
            (sessionManagement) ->
                sessionManagement
                    .sessionConcurrency(
                        sessionConcurrency -> sessionConcurrency.maximumSessions(1).expiredUrl("/"))
                    .sessionFixation()
                    .migrateSession())
        .build();
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
