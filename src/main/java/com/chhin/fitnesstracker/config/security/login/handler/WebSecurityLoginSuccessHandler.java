package com.chhin.fitnesstracker.config.security.login.handler;

import com.chhin.fitnesstracker.service.FTUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class WebSecurityLoginSuccessHandler implements AuthenticationSuccessHandler {
  Logger logger = LoggerFactory.getLogger(WebSecurityLoginSuccessHandler.class);

  private final FTUserService ftUserService;

  public WebSecurityLoginSuccessHandler(FTUserService ftUserService) {
    this.ftUserService = ftUserService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    logger.debug("logged in user {}", authentication);
    clearAuthenticationAttributes(request);
  }

  protected void clearAuthenticationAttributes(HttpServletRequest httpServletRequest) {
    HttpSession session = httpServletRequest.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

}
