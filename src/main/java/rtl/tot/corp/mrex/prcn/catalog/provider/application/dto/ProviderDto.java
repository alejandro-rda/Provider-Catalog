package rtl.tot.corp.mrex.prcn.catalog.provider.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProviderDto {

  @JsonProperty(value = "name")
  private String name;

  @JsonProperty(value = "rut")
  private String rut;
  
  @JsonProperty(value = "country")
  private String country;

}
