package rtl.tot.corp.mrex.prcn.catalog.provider.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.prcn.catalog.provider.common.application.EventProperties;

@Component
@Slf4j
public class HeadersInterceptor implements HandlerInterceptor {
  
  @Autowired
  private EventProperties eventProperties;
  
  @Override
  public boolean preHandle(HttpServletRequest request, 
      HttpServletResponse response, Object handler) throws Exception {
    log.info("Into preHandle");
    eventProperties.setChannel( request.getHeader("Channel"));
    eventProperties.setCommerce(request.getHeader("Commerce"));
    eventProperties.setCountry( request.getHeader("Country"));
    eventProperties.setMimeType(request.getHeader("Content-Type"));
    eventProperties.setVersion("1.0");
    return true;
  }

}
