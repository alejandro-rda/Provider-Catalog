package rtl.tot.corp.mrex.vndm.provider.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class RedisCacheConfiguration {
      @Value("${azure.redis.host}")
      private String host;
      
      @Value("${azure.redis.key}")
      private String key;
      
      @Value("${azure.redis.port}")
      private int port;
}
