package rtl.tot.corp.mrex.vndm.provider.api.controller;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import corp.falabella.api.response.common.api.controller.ResponseHandler;
import rtl.tot.corp.mrex.vndm.provider.application.ProviderAplicationService;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.vndm.provider.domain.exception.IncompleteCommandException;
import rtl.tot.corp.mrex.vndm.provider.domain.exception.NotFoundException;
import rtl.tot.corp.mrex.vndm.provider.util.UtilTest;

@RunWith(SpringRunner.class)
public class ProviderControllerTest {

  @InjectMocks
  ProviderController providerController;

  @Mock
  ProviderAplicationService providerAplicationService;

  @Mock
  ResponseHandler responseHandler;

  //CREATE
  @Test
  public void createProvider() throws IncompleteCommandException, Exception {
    Mockito.doNothing().when(providerAplicationService).createProvider(UtilTest.getProviderDto());
    when(responseHandler.getCommandResponse(HttpStatus.CREATED, "provider created"))
        .thenReturn(UtilTest.getOKResponseCommand(HttpStatus.CREATED, "provider created"));
    providerController.createProvider(UtilTest.getProviderDto());
  }

  @Test
  public void createProviderIncompleteCommandError() throws IncompleteCommandException, Exception {
    doThrow(IncompleteCommandException.class).when(providerAplicationService).createProvider(Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getAppCustomErrorResponse("Operation Failed"));

    providerController.createProvider(UtilTest.getProviderDto());
  }

  @Test
  public void createGeneralError() throws IncompleteCommandException, Exception {
    doThrow(Exception.class).when(providerAplicationService).createProvider(Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getExceptionGeneral());

    providerController.createProvider(UtilTest.getProviderDto());
  }
  
  //UPDATE
  @Test
  public void updateProvider() throws IncompleteCommandException, Exception {
    Mockito.doNothing().when(providerAplicationService).updateProvider(UtilTest.getProviderDto());
    when(responseHandler.getCommandResponse(HttpStatus.ACCEPTED, "provider update"))
        .thenReturn(UtilTest.getOKResponseCommand(HttpStatus.CREATED, "provider created"));
    providerController.updateProvider(UtilTest.getProviderDto());
  }
  
  @Test
  public void updateProviderIncompleteCommandError() throws IncompleteCommandException, Exception {
    doThrow(IncompleteCommandException.class).when(providerAplicationService).updateProvider(Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getAppCustomErrorResponse("Operation Failed"));

    providerController.updateProvider(UtilTest.getProviderDto());
  }
  
  @Test
  public void updateProviderIleglArgumentError() throws IncompleteCommandException, Exception {
    doThrow(IllegalArgumentException.class).when(providerAplicationService).updateProvider(Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getAppCustomErrorResponse("Operation Failed"));

    providerController.updateProvider(UtilTest.getProviderDto());
  }

  @Test
  public void updateGeneralError() throws IncompleteCommandException, Exception {
    doThrow(Exception.class).when(providerAplicationService).updateProvider(Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getExceptionGeneral());

    providerController.updateProvider(UtilTest.getProviderDto());
  }
  
  //GET
  @Test
  public void readProvider() throws NotFoundException, Exception {
    Mockito.doReturn(UtilTest.getProvider()).when(providerAplicationService).
    readProviders(UtilTest.getProviderDto().getRut(),UtilTest.getProviderDto().getCountryCode());
    when(responseHandler.getCommandResponse(HttpStatus.OK, "Provider information requested"))
    .thenReturn(UtilTest.getOKResponseCommand(HttpStatus.OK, "Provider information requested"));
    providerController.readProvider(UtilTest.getProviderDto().getRut(),UtilTest.getProviderDto().getCountryCode());
  }
  
  @Test
  public void readProviderNotFoundError() throws NotFoundException, Exception {
    doThrow(NotFoundException.class).when(providerAplicationService).readProviders(UtilTest.getProviderDto().getRut(),UtilTest.getProviderDto().getCountryCode());
    when(responseHandler.getCommandResponse(HttpStatus.OK, "provider update"))
        .thenReturn(UtilTest.getOKResponseCommand(HttpStatus.NOT_FOUND, "Operation Failed"));
    providerController.readProvider(UtilTest.getProviderDto().getRut(),UtilTest.getProviderDto().getCountryCode());
  }
  
  @Test
  public void readProviderIleglArgumentError() throws NotFoundException, Exception {
    doThrow(IllegalArgumentException.class).when(providerAplicationService).readProviders(Mockito.any(),Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getAppCustomErrorResponse("Operation Failed"));
    providerController.readProvider(UtilTest.getProviderDto().getRut(),UtilTest.getProviderDto().getCountryCode());
  }

  @Test
  public void readGeneralError() throws NotFoundException, Exception {
    doThrow(Exception.class).when(providerAplicationService).readProviders(Mockito.any(),Mockito.any());
    when(responseHandler.getAppCustomErrorResponse(Mockito.anyString()))
        .thenReturn(UtilTest.getExceptionGeneral());
    providerController.readProvider(UtilTest.getProviderDto().getRut(),UtilTest.getProviderDto().getCountryCode());
  }
}
