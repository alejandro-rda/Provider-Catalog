package rtl.tot.corp.mrex.vndm.provider.infrastructure.cache;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.vndm.provider.domain.cache.CacheRepository;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;

/**
 * Clase que expondra los servicios Rest correspondientes al
 * mantenimiento de proveedores.<br/>
 * <b>Class</b>: ProviderController<br/>
 * <b>Copyright</b>: &copy; 2018 Saga Falabella del Peru;.<br/>
 * <b>Company</b>: Saga Falabella del Peru.<br/>
 * 
 * @author rcanchanya <br/>
 *         <u>Service Provider</u>: kruger <br/>
 *         <u>Developed by</u>: <br/>
 *         <ul>
 *         <li>Ronald Canchanya</li>
 *         </ul>
 *         <u>Changes</u>:<br/>
 *         <ul>
 *         <li>May 14, 2019 Creaci&oacute;n de Clase.</li>
 *         </ul>
 * @version 1.0
 */
@Service
@Slf4j
public class ProviderService implements CacheRepository{

  @Autowired
	private ProviderCache cacheProveedor;

  /**
   * Obtiene proveedor de Cache.
   *
   * @param provider Provider
   */
  @Override
	public Optional<Provider> getProvidersCache(Provider provider) {
    Optional<Provider> providers = cacheProveedor.getProviders(provider);
		if (Objects.nonNull(providers)) {
			log.info("Serving the provider from cache");
			return providers;
		}
		log.info("Cache Retorna Vacio");
		return null;
	}
  
  /**
   * Agregar proveedor a la Cache.
   *
   * @param provider Provider
  */
  public Boolean addProviderCache(Provider provider) {
    cacheProveedor.addProviderCache(provider);
    log.info("Provider Saved successful ", provider);
    return Boolean.TRUE;
  }
  
  /**
   * Actualizar proveedor a Cache.
   *
   * @param provider Provider
  */
  public boolean updateProvider(Provider provider ) {
    /*implementar update para cache*/
    log.info("Provider Updated successful ", provider);
    return true;


  }
	
}
