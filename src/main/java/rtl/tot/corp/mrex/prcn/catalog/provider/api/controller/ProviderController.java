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

/**
 * Clase que expondra los servicios Rest correspondientes al
 * mantenimiento de proveedores.<br/>
 * <b>Class</b>: ProviderController<br/>
 * <b>Copyright</b>: &copy; 2018 Saga Falabella del Peru;.<br/>
 * <b>Company</b>: Saga Falabella del Peru.<br/>
 * 
 * @author jlozanoportillo <br/>
 *         <u>Service Provider</u>: kruger <br/>
 *         <u>Developed by</u>: <br/>
 *         <ul>
 *         <li>Jose Lozano</li>
 *         </ul>
 *         <u>Changes</u>:<br/>
 *         <ul>
 *         <li>May 03, 2019 Creaci&oacute;n de Clase.</li>
 *         </ul>
 * @version 1.0
 */

@RestController
@Api(tags = "Provider", description = "Provider")
@RequestMapping("/mrex/pctm/v1")
@Slf4j
public class ProviderController {
  
  @Autowired
  private ProviderAplicationService providerAplicationService;
  
  @Autowired
  private ResponseHandler responseHandler;
  
  
  /**
   * Servicio para registrar proveedor nuevo.
   *
   * @param request
   *          informacion del proveedor a registrar.
   * @return ResponseCommandDto Informacion de la confirmacion del registro.
   */
  
  
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
