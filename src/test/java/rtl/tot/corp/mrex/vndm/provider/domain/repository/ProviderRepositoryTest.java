package rtl.tot.corp.mrex.vndm.provider.domain.repository;

import rtl.tot.corp.mrex.vndm.provider.util.UtilTest;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import rtl.tot.corp.mrex.vndm.provider.infrastructure.document.repository.ProviderDocumentRepository;

@RunWith(SpringRunner.class)
public class ProviderRepositoryTest {

  @Mock
  ProviderDocumentRepository providerDocumentRepository;

  @InjectMocks
  ProviderRepository providerRepository;

  @Test
  public void getProviderByRutSucess() {
    when(providerDocumentRepository.getByRut(Mockito.anyString()))
    .thenReturn(UtilTest.getProvider());
    providerRepository.getProviderByRut("");
  }

}
