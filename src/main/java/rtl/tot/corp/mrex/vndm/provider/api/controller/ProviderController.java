package rtl.tot.corp.mrex.vndm.provider.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.falabella.api.response.common.api.controller.ResponseHandler;
import corp.falabella.api.response.common.application.dto.ResponseCommandDto;
import corp.falabella.api.response.common.application.dto.ResponseErrorDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.vndm.provider.application.ProviderAplicationService;
import rtl.tot.corp.mrex.vndm.provider.application.dto.ProviderDto;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.vndm.provider.domain.exception.IncompleteCommandException;

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
   * @param request informacion del proveedor a registrar.
   * @return ResponseCommandDto Informacion de la confirmacion del registro.
   */
  @PostMapping(
      value = "/provider", 
      produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, 
      consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  @ApiOperation(
      value = "Crear Proveedor", 
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
      response = ResponseCommandDto.class, 
      httpMethod = "POST")
  @ApiResponses({ @ApiResponse(code = 201, response = String.class, message = "Producto created"),
      @ApiResponse(code = 400, response = ResponseErrorDto.class, message = "Bad Request"),
      @ApiResponse(code = 401, response = ResponseErrorDto.class, message = "Unauthorized"),
      @ApiResponse(code = 403, response = ResponseErrorDto.class, message = "Forbidden"),
      @ApiResponse(code = 406, response = ResponseErrorDto.class, message = "The provifrt entered already exists"),
      @ApiResponse(code = 500, response = ResponseErrorDto.class, message = "Internal Server Error"),
      @ApiResponse(code = 501, response = ResponseErrorDto.class, message = "Not Implemented") })
  public ResponseEntity<Object> createProvider(@RequestBody ProviderDto request) {
    log.info("Into createProvider(ProviderDto request)");
    try {
      providerAplicationService.createProvider(request);
      return responseHandler.getCommandResponse(HttpStatus.CREATED, "provider created");

    } catch (IncompleteCommandException | IllegalArgumentException e) {
      log.info("me ejecuto correctamente");
      return responseHandler.getAppCustomErrorResponse(e.getMessage());
      
    } catch (Exception e) {
      log.info("General exception");
      return responseHandler.getAppExceptionResponse();
    }

  }
  
  /**
   * Servicio para actualiza proveedor nuevo.
   *
   * @param request informacion del proveedor a actualizar.
   * @return ResponseCommandDto Informacion de la confirmacion de la actualizacion.
   */
  @ApiOperation(
      value = "Actualizar Proveedor", 
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, 
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
      response = ResponseCommandDto.class, 
      httpMethod = "PUT")
  @ApiResponses({ @ApiResponse(code = 202, response = String.class, message = "Provider successfully updated"),
      @ApiResponse(code = 400, response = ResponseErrorDto.class, message = "Bad Request"),
      @ApiResponse(code = 401, response = ResponseErrorDto.class, message = "Unauthorized"),
      @ApiResponse(code = 403, response = ResponseErrorDto.class, message = "Forbidden"),
      @ApiResponse(code = 404, response = ResponseErrorDto.class, message = "Provider not found"),
      @ApiResponse(code = 406, response = ResponseErrorDto.class, message = "The provifrt entered already exists"),
      @ApiResponse(code = 500, response = ResponseErrorDto.class, message = "Internal Server Error"),
      @ApiResponse(code = 501, response = ResponseErrorDto.class, message = "Not Implemented") })
  @PutMapping(value = "/provider", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = {
      MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResponseEntity<Object> updateProvider(@RequestBody ProviderDto request) {
    log.info("Into createProvider(ProviderDto request)");
    try {
      providerAplicationService.updateProvider(request);
      return responseHandler.getCommandResponse(HttpStatus.ACCEPTED, "provider update");

    } catch (IncompleteCommandException e) {
      return responseHandler.getResponse(e.getMessage(), HttpStatus.NOT_FOUND);

    } catch (IllegalArgumentException e) {
      return responseHandler.getAppCustomErrorResponse(e.getMessage());

    } catch (Exception e) {
      log.info("General exception");
      return responseHandler.getAppExceptionResponse();
    }

  }
  
  
  /**
   * Servicio para consultar un(os) proveedor(es).
   *
   * @param request informacion del rut del proveedor .
   * @return ResponseCommandDto Informacion del proveedor.
   */
  @GetMapping(
      value = "/provider/{countryCode}/{rut}", 
      produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, 
      consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  
  @ApiResponses({ @ApiResponse(code = 201, response = String.class, message = "Producto created"),
      @ApiResponse(code = 400, response = ResponseErrorDto.class, message = "Bad Request"),
      @ApiResponse(code = 401, response = ResponseErrorDto.class, message = "Unauthorized"),
      @ApiResponse(code = 403, response = ResponseErrorDto.class, message = "Forbidden"),
      @ApiResponse(code = 406, response = ResponseErrorDto.class, message = "The provifrt entered already exists"),
      @ApiResponse(code = 500, response = ResponseErrorDto.class, message = "Internal Server Error"),
      @ApiResponse(code = 501, response = ResponseErrorDto.class, message = "Not Implemented") })
  public ResponseEntity<Object> readProvider(@PathVariable String countryCode, @PathVariable String rut) {
    try {
      Optional<Provider> provider=providerAplicationService.readProviders(countryCode,rut);
      return responseHandler.getCommandResponse(HttpStatus.ACCEPTED, provider.get());

    } catch (IncompleteCommandException | IllegalArgumentException e) {
      return responseHandler.getAppCustomErrorResponse(e.getMessage());
      
    } catch (Exception e) {
      log.info("General exception");
      return responseHandler.getAppExceptionResponse();
    }

  }
}
