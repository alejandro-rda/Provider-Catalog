package rtl.tot.corp.mrex.vndm.provider.application;

import static org.mockito.Mockito.when;

import java.util.Optional;

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
  
  //CREATE
  @Test
  public void createProviderSucessful() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByKey(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.empty());
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
    when(providerRepository.getProviderByKey(Mockito.anyString(), Mockito.anyString()))
    .thenReturn(Optional.of(Provider.builder().rut("123123123").build()));
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
  
  @Test(expected = IncompleteCommandException.class)
  public void createProviderCommandIncompleteError() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByKey(Mockito.anyString(),Mockito.anyString()))
    .thenReturn(Optional.empty());
    when(commandBus.executeCreate(Mockito.any())).thenReturn(false);
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
    providerAplicationService.createProvider(UtilTest.getProviderDto());
  }
  
  //UPDATE
  @Test
  public void updateProviderSucessful() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByKey(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Optional.of(new Provider()));
    when(commandBus.executeUpdate(Mockito.any())).thenReturn(true);
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
    providerAplicationService.updateProvider(UtilTest.getProviderDto());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void updateProviderIllegalDtoError() throws IncompleteCommandException, Exception {
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
  public void updateProviderProviderNotFoundError() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByKey(Mockito.anyString(),Mockito.anyString()))
        .thenReturn(null);
    when(commandBus.executeUpdate(Mockito.any())).thenReturn(true);
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
    providerAplicationService.updateProvider(UtilTest.getProviderDto());
  }
  
  @Test(expected = IncompleteCommandException.class)
  public void updateProviderCommandIncompleteError() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByKey(Mockito.anyString(),Mockito.anyString()))
        .thenReturn(Optional.of(new Provider()));
    when(commandBus.executeUpdate(Mockito.any())).thenReturn(false);
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
    providerAplicationService.updateProvider(UtilTest.getProviderDto());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void updateProviderIllegalDto() throws IncompleteCommandException, Exception {
    when(providerRepository.getProviderByKey(Mockito.anyString(),Mockito.anyString()))
        .thenReturn(Optional.of(new Provider()));
    when(commandBus.executeUpdate(Mockito.any())).thenReturn(false);
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
    providerAplicationService.updateProvider(new ProviderDto());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void updateProviderIllegalLenthDto() throws IncompleteCommandException, Exception {
    ProviderDto providerDto = UtilTest.getProviderDto();
    providerDto.setRut("123123123123123123123123");
    providerDto.setDvRut("12345");
    providerDto.setCity("san juan de lurigancho lima peru donde los $$$$ vuelan y las botellas :Ddsssss");
    providerDto.setPhoneAreaCode("5112020212501"); 
    providerDto.setPhoneNumber("2323323232323232323232323");
    providerDto.setFaxNumber("555555555555555555555555555555555555555555");
    providerDto.setZipCode("111111111111111111115555555555555");
    providerDto.setEmail("krugerkrugerkrugerkrugerkrugerkrugerkrugerkrugerkrugerkrugerkrugerkrugerkruger@gmail.c");
    
    when(providerRepository.getProviderByKey(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(Optional.of(new Provider()));
    when(commandBus.executeUpdate(Mockito.any())).thenReturn(true);
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
    providerAplicationService.updateProvider(providerDto);
  }
  
}
