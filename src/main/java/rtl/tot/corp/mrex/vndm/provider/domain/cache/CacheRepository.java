package rtl.tot.corp.mrex.vndm.provider.domain.cache;

import java.util.Optional;

import org.springframework.stereotype.Service;

import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;

@Service
public  interface  CacheRepository {
  
  public Optional<Provider> getProvidersCache(String rut,String countryCodeOrigin);
  
  public Boolean addProviderCache(Provider provider);
}
