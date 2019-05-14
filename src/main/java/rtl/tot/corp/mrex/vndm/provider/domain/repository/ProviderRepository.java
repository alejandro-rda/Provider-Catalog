package rtl.tot.corp.mrex.vndm.provider.domain.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.vndm.provider.infrastructure.document.repository.ProviderDocumentRepository;

@Service
public class ProviderRepository {

  @Autowired
  ProviderDocumentRepository providerDocumentRepository;
  
  public Optional<Provider> getProviders(String rut) {
    return providerDocumentRepository.getByRut(rut);
  }
 
  public Optional<Provider> getProviderByKey(String rut, String countryCode) {
    return providerDocumentRepository.getProvider(rut, countryCode);
  }
  
}
