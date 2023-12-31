package com.chhin.fitnesstracker.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequestScope
public class ParamReplacer {
  private final HttpServletRequest httpServletRequest;

  public ParamReplacer(HttpServletRequest httpServletRequest) {
    this.httpServletRequest = httpServletRequest;
  }

  public String getUpdatedUri(String name, Object... values) {
    UriComponentsBuilder uriComponentsBuilder;
    if (httpServletRequest.getQueryString() == null) {
      uriComponentsBuilder =
          UriComponentsBuilder.fromHttpUrl(httpServletRequest.getRequestURL().toString());
    } else {
      uriComponentsBuilder =
          UriComponentsBuilder.fromHttpUrl(
              httpServletRequest.getRequestURL().toString()
                  + "?"
                  + httpServletRequest.getQueryString());
    }
    return uriComponentsBuilder.replaceQueryParam(name, values).build().toUriString();
  }
}
