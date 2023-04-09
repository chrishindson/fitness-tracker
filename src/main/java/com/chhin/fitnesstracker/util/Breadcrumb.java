package com.chhin.fitnesstracker.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Breadcrumb implements Serializable {

  @JsonProperty("pageTitle")
  private String pageTitle;
  @JsonProperty("url")
  private String url;

  @JsonProperty("history")
  private List<Breadcrumb> history;

  @JsonCreator
  public Breadcrumb(@JsonProperty("pageTitle") String pageTitle, @JsonProperty("url") String url) {
    this.pageTitle = pageTitle;
    this.url = url;
  }

  public String getPageTitle() {
    return pageTitle;
  }

  public void setPageTitle(String pageTitle) {
    this.pageTitle = pageTitle;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<Breadcrumb> getHistory() {
    return history;
  }

  public void setHistory(List<Breadcrumb> history) {
    this.history = history;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Breadcrumb that = (Breadcrumb) o;
    return Objects.equals(pageTitle, that.pageTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageTitle);
  }

  @Override
  public String toString() {
    return "Breadcrumb{" + "pageTitle='" + pageTitle + '\'' + '}';
  }
}
