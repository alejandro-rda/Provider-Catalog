package rtl.tot.corp.mrex.prcn.catalog.provider.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.prcn.catalog.provider.application.ProviderAplicationService;
import rtl.tot.corp.mrex.prcn.catalog.provider.application.dto.ProviderDto;
//import rtl.tot.corp.mrex.prcn.catalog.provider.common.api.controller.ResponseHandler;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.exception.IncompleteCommandException;
import corp.falabella.api.response.common.api.controller.ResponseHandler;

@RestController
@Api(tags = "Provider", description = "Provider")
@RequestMapping("/mrex/pctm/v1")
@Slf4j
public class ProviderController {
  
  @Autowired
  private ProviderAplicationService providerAplicationService;
  

  
  @Autowired
  private ResponseHandler responseHandler;
  
  @PostMapping(
      value = "/provider", 
      produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, 
      consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResponseEntity<Object> createProvider(@RequestBody ProviderDto request) {
    log.info("Into createProvider(ProviderDto request)");
    try {
      providerAplicationService.createProvider(request);
      return responseHandler.getCommandResponse(HttpStatus.CREATED, "Operation Sucessfull");

    } catch (IncompleteCommandException | IllegalArgumentException e) {
      return responseHandler.getAppCustomErrorResponse(e.getMessage());
      
    } catch (Exception e) {
      log.info("General exception");
      return responseHandler.getAppExceptionResponse();
    }

  }

}
