package rtl.tot.corp.mrex.vndm.provider.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProviderDto {
  
  @ApiModelProperty(
      name = "rut",
      value = "nro identifier vendor",
      example = "1234567890",
      required = true,
      dataType = "String",
      allowableValues = "range[, 20]",
      position = 1)
  @JsonProperty(value = "rut")
  private String rut;

  @ApiModelProperty(
      name = "dvRut",
      value = "verifier digit of nroRUT",
      example = "1",
      required = true,
      dataType = "String",
          allowableValues = "range[, 3]",
      position = 2)
  @JsonProperty(value = "dvRut")
  private String dvRut;
  
  @ApiModelProperty(
      name = "nameVendor",
      value = "vendor name",
      example = "Proveedor Prueba",
      required = true,
      dataType = "String",
      allowableValues = "range[, 80]",
      position = 3)
  @JsonProperty(value = "nameVendor")
  private String nameVendor;
  
  @ApiModelProperty(
      name = "address",
      value = "provider address",
      example = "av principal",
      required = true,
      dataType = "String",
      allowableValues = "range[, 200]",
      position = 4)
  @JsonProperty(value = "address")
  private String address;
  
  @ApiModelProperty(
      name = "currencyCode",
      value = "currency code of vendor",
      example = "PEN",
      required = true,
      dataType = "String",
      allowableValues = "range[, 10]",
      position = 5)
  @JsonProperty(value = "currencyCode")
  private String currencyCode;

  @ApiModelProperty(
      name = "city",
      value = "city vendor",
      example = "LIMA",
      required = false,
      dataType = "String",
      allowableValues = "range[, 50]",
      position = 6)
  @JsonProperty(value = "city")
  private String city;
  
  @ApiModelProperty(
      name = "country",
      value = "country",
      example = "LIMA",
      required = true,
      dataType = "String",
      allowableValues = "range[, 50]",
      position = 7)
  @JsonProperty(value = "country")
  private String country;
  
  @ApiModelProperty(
      name = "countryCode",
      value = "code country",
      example = "PE",
      required = true,
      dataType = "String",
      allowableValues = "range[, 5]",
      position = 8)
  @JsonProperty(value = "countryCode")
  private String countryCode;
  
  @ApiModelProperty(
      name = "phoneAreaCode",
      value = "code area of phone number",
      example = "511",
      required = false,
      dataType = "String",
      allowableValues = "range[, 10]",
      position = 9)
  @JsonProperty(value = "phoneAreaCode")
  private String phoneAreaCode;
  
  @ApiModelProperty(
      name = "phoneNumber",
      value = "phone number",
      example = "911111111",
      required = false,
      dataType = "String",
      allowableValues = "range[, 20]",
      position = 10)
  @JsonProperty(value = "phoneNumber")
  private String phoneNumber;
  
  @ApiModelProperty(
      name = "faxNumber",
      value = "fax number",
      example = "441561",
      required = false,
      dataType = "String",
      allowableValues = "range[, 20]",
      position = 11)
  @JsonProperty(value = "faxNumber")
  private String faxNumber;
  
  @ApiModelProperty(
      name = "zipCode",
      value = "zip code vendor",
      example = "LIMA 01",
      required = false,
      dataType = "String",
      allowableValues = "range[, 30]",
      position = 12)
  @JsonProperty(value = "zipCode")
  private String zipCode;
  
  @ApiModelProperty(
      name = "email",
      value = "email",
      example = "vendor@provider.com",
      required = true,
      dataType = "String",
      allowableValues = "range[, 50]",
      position = 13)
  @JsonProperty(value = "email")
  private String email;
  
  @ApiModelProperty(
      name = "comuna",
      value = "comuna of vendor",
      example = "LIMA",
      required = false,
      dataType = "String",
      allowableValues = "range[, 30]",
      position = 14)
  @JsonProperty(value = "comuna")
  private String comuna;
  
  @ApiModelProperty(
      name = "typeVendor",
      value = "type of vendor",
      example = "NAC|IMP",
      required = false,
      allowableValues = "range[, 20]",
      dataType = "String",
      position = 14)
  @JsonProperty(value = "typeVendor")
  private String typeVendor;

}
