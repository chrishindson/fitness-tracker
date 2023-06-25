//package com.chhin.fitnesstracker.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.webflow.config.AbstractFlowConfiguration;
//import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
//import org.springframework.webflow.engine.builder.ViewFactoryCreator;
//import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
//import org.springframework.webflow.executor.FlowExecutor;
//import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
//import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
//import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
//
//import java.util.Collections;
//
//@Configuration
//public class WebFlowConfig extends AbstractFlowConfiguration {
//  @Autowired
//  private LocalValidatorFactoryBean localValidatorFactoryBean;
//
//  @Bean
//  public FlowBuilderServices flowBuilderServices(ViewResolver thymeleafViewResolver, ViewFactoryCreator viewFactoryCreator) {
//    return getFlowBuilderServicesBuilder().setViewFactoryCreator(viewFactoryCreator).setValidator(this.localValidatorFactoryBean).build();
//  }
//
//  @Bean
//  public FlowDefinitionRegistry flowRegistry() {
//    return getFlowDefinitionRegistryBuilder(flowBuilderServices())
//        .setBasePath("classpath:flows")
//        .addFlowLocationPattern("/**/*-flow.xml")
//        .build();
//  }
//
//  @Bean
//  public FlowExecutor flowExecutor() {
//    return getFlowExecutorBuilder(flowRegistry()).build();
//  }
//
//  @Bean
//  public FlowBuilderServices flowBuilderServices() {
//    return getFlowBuilderServicesBuilder().setDevelopmentMode(true).build();
//  }
//
//  @Bean
//  public FlowHandlerMapping flowHandlerMapping(FlowDefinitionRegistry flowDefinitionRegistry) {
//    FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
//    flowHandlerMapping.setOrder(-1);
//    flowHandlerMapping.setFlowRegistry(flowDefinitionRegistry);
//    return flowHandlerMapping;
//  }
//
//  @Bean
//  public FlowHandlerAdapter flowHandlerAdapter(FlowExecutor flowExecutor) {
//    FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
//    handlerAdapter.setFlowExecutor(flowExecutor);
//    handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
//    return handlerAdapter;
//  }
//
//  @Bean
//  public ViewFactoryCreator viewFactoryCreator(ViewResolver thymeleafViewResolver) {
//    MvcViewFactoryCreator viewFactoryCreator = new MvcViewFactoryCreator();
//    viewFactoryCreator.setViewResolvers(Collections.singletonList(thymeleafViewResolver));
//    viewFactoryCreator.setUseSpringBeanBinding(true);
//    return viewFactoryCreator;
//  }
//}
//
