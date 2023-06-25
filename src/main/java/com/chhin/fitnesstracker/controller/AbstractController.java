package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.util.Breadcrumb;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class AbstractController {

  public static final String ATTR_BINDING_RESULT = BindingResult.MODEL_KEY_PREFIX;
  public static final String CONTROLLER_HOME_LANDING = "/home";
  public static final String PAGE_TITLE = "pageTitle";
  public static final String PAGE_TITLE_RAW = "pageTitleRaw";
  public static final String BREADCRUMBS = "breadcrumbs";
  public static final String REDIRECT = "redirect:";
  public String titleString;
  public List<Breadcrumb> breadcrumbList;

  public AbstractController() {
  }

  @ModelAttribute(BREADCRUMBS)
  public List<Breadcrumb> getBreadcrumbs() {
    return this.breadcrumbList;
  }

  public void setBreadcrumbList(List<Breadcrumb> breadcrumbList) {
    this.breadcrumbList = breadcrumbList;
  }

  public String getHomeMapping(String controllerMapping) {
    return controllerMapping + "/home";
  }

  public void getStarterBreadcrumbs(String pageTitle, Model model, HttpServletRequest request) {
    resetBreadcrumbs(pageTitle, request);
    model.addAttribute(BREADCRUMBS, getBreadcrumbs());
    populateTitle(model, pageTitle);
  }

  public void getBreadcrumbs(String pageTitle, Model model, HttpServletRequest request) {
    retrieveBreadcrumbs(pageTitle, request);
    model.addAttribute(BREADCRUMBS, getBreadcrumbs());
    populateTitle(model, pageTitle);
  }

  public void getBreadcrumbs(String pageTitle, Model model, HttpServletRequest request,
                             List<Breadcrumb> breadcrumbs) {
    if (breadcrumbs != null) {
      setBreadcrumbList(breadcrumbs);
    } else {
      setBreadcrumbList(getBreadcrumbs());
    }
    retrieveBreadcrumbs(pageTitle, request);
    model.addAttribute(BREADCRUMBS, getBreadcrumbs());
    populateTitle(model, pageTitle);
  }

  private void resetBreadcrumbs(String pageTitle, HttpServletRequest request) {
    List<Breadcrumb> breadcrumbs = new ArrayList<>();
    breadcrumbs.add(new Breadcrumb(pageTitle, request.getContextPath() + request.getServletPath()));
    setBreadcrumbList(breadcrumbs);
  }


  void populateTitle(Model model, String pageTitle) {
    model.addAttribute(PAGE_TITLE_RAW, pageTitle);
    model.addAttribute(PAGE_TITLE, pageTitle + " | Health tracking");
  }

  private void retrieveBreadcrumbs(String pageTitle, HttpServletRequest request) {
    boolean found = false;
    List<Breadcrumb> breadcrumbs = new ArrayList<>();
    String requestString = request.getContextPath() + request.getServletPath();
    if (request.getQueryString() != null) {
      requestString += "?" + request.getQueryString();
    }
    if (getBreadcrumbs() != null) {
      for (Breadcrumb bc : breadcrumbList) {
        breadcrumbs.add(bc);
        if (bc.getUrl().equals(requestString)) {
          found = true;
          break;
        }
      }
    } else {
      breadcrumbs.add(new Breadcrumb("Dashboard", "/fitness-tracking/dashboard"));
    }
    if (!found) {

      breadcrumbs.add(new Breadcrumb(pageTitle, requestString));
    }
    setBreadcrumbList(breadcrumbs);
  }
}
