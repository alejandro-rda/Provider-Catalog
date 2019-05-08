package rtl.tot.corp.mrex.vndm.provider.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import corp.falabella.api.response.common.api.controller.ResponseHandler;
import rtl.tot.corp.mrex.vndm.provider.application.dto.ProviderDto;

public class UtilTest {

  public static ProviderDto getProviderDto() {

    ProviderDto dto = new ProviderDto();
    dto.setRut("1234567890");
    dto.setDvRut("1");
    dto.setNameVendor("Proveedor Prueba");
    dto.setAddress("calle 123 cruce con av principal");
    dto.setCurrencyCode("PEN");
    dto.setCity("LIMA");
    dto.setCountry("PERU");
    dto.setCountryCode("PE");
    dto.setPhoneAreaCode("511");
    dto.setPhoneAreaCode("911111111");
    dto.setZipCode("LIMA 01");
    dto.setEmail("vendor@provider.com");
    dto.setComuna("LIMA");
    dto.setTypeVendor("NAC");
    return dto;
  }

  public static ResponseEntity<Object> getOKResponseCommand(HttpStatus status, String message) {
    return new ResponseHandler().getCommandResponse(status, message);
  }

  public static ResponseEntity<Object> getAppCustomErrorResponse(String errorMessage) {
    return new ResponseHandler().getAppCustomErrorResponse(errorMessage);
  }

  public static ResponseEntity<Object> getExceptionGeneral() {
    return new ResponseHandler().getAppExceptionResponse();
  }

}
