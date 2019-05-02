package rtl.tot.corp.mrex.prcn.catalog.provider.common.application;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Component
public class EventProperties {
  
  private String channel;
  private String commerce;
  private String country;
  private String mimeType;
  private String version;

  
}
