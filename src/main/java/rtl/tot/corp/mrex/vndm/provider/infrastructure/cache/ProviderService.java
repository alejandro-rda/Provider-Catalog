package rtl.tot.corp.mrex.vndm.provider.infrastructure.cache;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.vndm.provider.domain.cache.CacheRepository;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;

@Service
@Slf4j
public class ProviderService implements CacheRepository{

  @Autowired
	private ProviderCache cacheService;

  @Override
	public Optional<Provider> getProvidersCache(Provider provider) {
    Optional<Provider> providers = cacheService.getProviders(provider);
		if (Objects.nonNull(providers)) {
			log.info("Serving the provider from cache");
			return providers;
		}
		log.info("Cache Retorna Vacio");
		return null;
	}
  
  public Boolean addProviderCache(Provider provider) {
    cacheService.addProviderCache(provider);
    log.info("Provider Saved successful ", provider);
    return Boolean.TRUE;
  }

	
}
