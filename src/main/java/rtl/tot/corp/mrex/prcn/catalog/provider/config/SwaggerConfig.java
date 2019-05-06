package rtl.tot.corp.mrex.prcn.catalog.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  
  
  /**
   * Configura Docket para el swagger.
   *
   * @return Docket.
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("rtl.tot.corp.mrex.prcn.catalog.provider"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(metaData());
  }
  
  private ApiInfo metaData() {
    @SuppressWarnings("deprecation")
    ApiInfo apiInfo = new ApiInfo(
            "Provider API of HT",
            "Provider API of HT2",
            "1.0",
            "Terms of service",
            "Contact the developer",
            "Apache 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0"
            );
    
    return apiInfo;
  }
  
}
