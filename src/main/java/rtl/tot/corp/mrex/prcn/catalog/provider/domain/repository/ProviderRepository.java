package rtl.tot.corp.mrex.prcn.catalog.provider.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.prcn.catalog.provider.infrastructure.document.repository.ProviderDocumentRepository;

@Service
public class ProviderRepository {

  @Autowired
  ProviderDocumentRepository providerDocumentRepository;
  
  public Provider getProviderByRut(String rut) {
    return providerDocumentRepository.getByRut(rut);
  }
  
}
