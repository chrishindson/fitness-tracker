package com.chhin.fitnesstracker.config.security.login;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Objects;

public class FTClientAuthenticationDetails extends WebAuthenticationDetails {

  private final String authCode;

  public FTClientAuthenticationDetails(HttpServletRequest httpServletRequest) {
    super(httpServletRequest);
    this.authCode = httpServletRequest.getParameter("authCode");
  }

  public String getAuthCode() {
    return authCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FTClientAuthenticationDetails that = (FTClientAuthenticationDetails) o;
    return Objects.equals(authCode, that.authCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), authCode);
  }
}
