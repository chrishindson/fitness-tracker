package com.chhin.fitnesstracker.config.security.login.handler;

import com.chhin.fitnesstracker.service.FTUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class WebSecurityLoginSuccessHandler implements AuthenticationSuccessHandler {

  private final FTUserService ftUserService;

  public WebSecurityLoginSuccessHandler(FTUserService ftUserService) {
    this.ftUserService = ftUserService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
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
