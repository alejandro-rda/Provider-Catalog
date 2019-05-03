package rtl.tot.corp.mrex.prcn.catalog.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import corp.falabella.api.response.common.api.controller.ResponseHandler;
import corp.falabella.api.response.common.application.EventProperties;

@Configuration
public class InitializerBean {
  
  @Bean
  public EventProperties getEventProperties() {
    return new EventProperties();
  }
  
  @Bean
  public ResponseHandler getResponseHandler() {
    return new ResponseHandler();
  }
}
