package rtl.tot.corp.mrex.vndm.provider.application;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import rtl.tot.corp.mrex.vndm.provider.application.dto.ProviderDto;
import rtl.tot.corp.mrex.vndm.provider.config.ApplicationProperties;
import rtl.tot.corp.mrex.vndm.provider.domain.command.CommandBus;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.vndm.provider.domain.exception.IncompleteCommandException;
import rtl.tot.corp.mrex.vndm.provider.domain.repository.ProviderRepository;
import rtl.tot.corp.mrex.vndm.provider.util.UtilTest;


@RunWith(SpringRunner.class)
public class ProviderAplicationServiceTest {
  
  @InjectMocks
  ProviderAplicationService providerAplicationService;
  
  @Mock
  CommandBus commandBus;
  
  @Mock
  ProviderRepository providerRepository;
  
  @Mock
  ApplicationProperties env;
  
  @Test
  public void createProviderSucessful() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByRut(Mockito.anyString())).thenReturn(null);
    when(commandBus.executeCreate(Mockito.any())).thenReturn(true);
    when(env.getMaxLenghtProviderRut()).thenReturn(20); //
    when(env.getMaxLenghtDvRut()).thenReturn(3); //
    when(env.getMaxLenghtNameVendor()).thenReturn(80); //
    when(env.getMaxLenghtAddress()).thenReturn(200); //
    when(env.getMaxLenghtCurrencyCode()).thenReturn(10); //
    when(env.getMaxLenghtCity()).thenReturn(50); //
    when(env.getMaxLenghtCountry()).thenReturn(50); //
    when(env.getMaxLenghtCountryCode()).thenReturn(50); //
    when(env.getMaxLenghtPhoneAreaCode()).thenReturn(10); //
    when(env.getMaxLenghtPhoneNumber()).thenReturn(20); //
    when(env.getMaxLenghtFaxNumber()).thenReturn(30); //
    when(env.getMaxLenghtZipCode()).thenReturn(30); //
    when(env.getMaxLenghtEmail()).thenReturn(50); //
    when(env.getFormatEmail()).thenReturn("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"); //
    providerAplicationService.createProvider(UtilTest.getProviderDto());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void createProviderIllegalDtoError() throws IncompleteCommandException, Exception {
    when(env.getMaxLenghtProviderRut()).thenReturn(20); 
    when(env.getMaxLenghtDvRut()).thenReturn(3);
    when(env.getMaxLenghtNameVendor()).thenReturn(80);
    when(env.getMaxLenghtAddress()).thenReturn(200);
    when(env.getMaxLenghtCurrencyCode()).thenReturn(10);
    when(env.getMaxLenghtCity()).thenReturn(50);
    when(env.getMaxLenghtCountry()).thenReturn(50);
    when(env.getMaxLenghtCountryCode()).thenReturn(50);
    when(env.getMaxLenghtPhoneAreaCode()).thenReturn(10);
    when(env.getMaxLenghtPhoneNumber()).thenReturn(20);
    when(env.getMaxLenghtFaxNumber()).thenReturn(30);
    when(env.getMaxLenghtZipCode()).thenReturn(30);
    when(env.getMaxLenghtEmail()).thenReturn(50);
    when(env.getFormatEmail()).thenReturn("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
    providerAplicationService.createProvider(new ProviderDto());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void createProviderAlreadyProviderExist() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByRut(Mockito.anyString()))
    .thenReturn(Provider.builder().rut("123123123").build());
    when(commandBus.executeCreate(Mockito.any())).thenReturn(true);
    when(env.getMaxLenghtProviderRut()).thenReturn(20);
    when(env.getMaxLenghtDvRut()).thenReturn(3);
    when(env.getMaxLenghtNameVendor()).thenReturn(80);
    when(env.getMaxLenghtAddress()).thenReturn(200);
    when(env.getMaxLenghtCurrencyCode()).thenReturn(10);
    when(env.getMaxLenghtCity()).thenReturn(50);
    when(env.getMaxLenghtCountry()).thenReturn(50);
    when(env.getMaxLenghtCountryCode()).thenReturn(50);
    when(env.getMaxLenghtPhoneAreaCode()).thenReturn(10);
    when(env.getMaxLenghtPhoneNumber()).thenReturn(20);
    when(env.getMaxLenghtFaxNumber()).thenReturn(30); //
    when(env.getMaxLenghtZipCode()).thenReturn(30); //
    when(env.getMaxLenghtEmail()).thenReturn(50); //
    when(env.getFormatEmail()).thenReturn("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"); //
    providerAplicationService.createProvider(UtilTest.getProviderDto());
  }
  
}
