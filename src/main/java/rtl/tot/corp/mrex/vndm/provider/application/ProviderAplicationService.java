package rtl.tot.corp.mrex.vndm.provider.application;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import corp.falabella.api.response.common.application.Notification;
import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.vndm.provider.application.dto.ProviderDto;
import rtl.tot.corp.mrex.vndm.provider.config.ApplicationProperties;
import rtl.tot.corp.mrex.vndm.provider.domain.cache.CacheRepository;
import rtl.tot.corp.mrex.vndm.provider.domain.command.CommandBus;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.vndm.provider.domain.exception.IncompleteCommandException;
import rtl.tot.corp.mrex.vndm.provider.domain.exception.NotFoundException;
import rtl.tot.corp.mrex.vndm.provider.domain.repository.ProviderRepository;

/**
 * Main class for running Spring Boot framework.<br/>
 * <b>Class</b>: ProviderCatalogApplication<br/>
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
@Service
@Slf4j
public class ProviderAplicationService {
  
  
  @Autowired
  private CommandBus commandBus;
  
  @Autowired
  private ProviderRepository providerRepository;
  
  @Autowired
  private CacheRepository cacheRepository;
  
  @Autowired
  @Qualifier("env")
  private ApplicationProperties env;
  
  /**
   * Registra proveedor.
   *
   * @param providerDto ProviderDto
   */
  
  public void createProvider(ProviderDto providerDto) throws IncompleteCommandException, Exception {
    log.info("Into createProvider(ProviderDto providerDto)");
    Notification notification = this.createValidation(providerDto);
    if (notification.hasErrors()) {
      throw new IllegalArgumentException(notification.errorMessage());
    }
    Provider provider = assemblerProvider(providerDto);
    notification = this.createValidationFunctional(provider);
    if (notification.hasErrors()) {
      throw new IllegalArgumentException(notification.errorMessage());
    }  
    boolean isCreated = commandBus.executeCreate(provider);
    if (!isCreated) {
      throw new IncompleteCommandException("Operation Failed");
    }
    log.info("Sucessful Operation");
  }
  
  /**
   * Actualiza informacion de proveedor.
   *
   * @param providerDto ProviderDto
   */
  public void updateProvider(ProviderDto providerDto) throws IncompleteCommandException, Exception {
    log.info("Into updateProvider(ProviderDto providerDto)");
    Notification notification = this.createValidation(providerDto);
    if (notification.hasErrors()) {
      throw new IllegalArgumentException(notification.errorMessage());
    }
    Provider provider = assemblerProvider(providerDto);
    notification = updateValidationFunctional(provider);
    if (notification.hasErrors()) {
      throw new IllegalArgumentException(notification.errorMessage());
    }
    boolean isUpdate = commandBus.executeUpdate(provider);
    if (!isUpdate) {
      throw new IncompleteCommandException("Operation Failed");
    }
    
    log.info("Sucessful Operation");
  }
  
  /**
   * Consulta proveedor.
   *
   * @param rut String
   * @param countryCode String
   */
  
  public Optional<Provider> readProviders(String countryCode,String rut) throws NotFoundException, Exception {
    log.info("Into readProvider(String countryCode, String rut)");
    Provider provider = Provider.builder().countryCode(countryCode).rut(rut).build();
    Optional<Provider> provCache = cacheRepository.getProvidersCache(provider);
    log.info("Service: Obtiene Data de Cache ..." + provCache);
    if (provCache.isPresent()) {
      return provCache;
    }
    Notification notification = new Notification();
    Optional<Provider> provDB = providerRepository.getProviderByKey(provider.getRut(), provider.getCountryCode());
    if (!provDB.isPresent()) {
      notification.addError("Provider not exists");
      throw new IllegalArgumentException(notification.errorMessage());
    }
    return provDB;
  }

  private Notification createValidation(ProviderDto providerDto) {
    Notification notification = new Notification();
    if (Objects.isNull(providerDto.getRut()) || providerDto.getRut().isEmpty()) {
      notification.addError("'rut' can not be null or empty");
    }

    if (Objects.nonNull(providerDto.getRut()) 
        && providerDto.getRut().length() > env.getMaxLenghtProviderRut()) {
      notification.addError("Lenght 'rut' can not be more lenght that " + env.getMaxLenghtProviderRut());
    }

    if (Objects.isNull(providerDto.getDvRut()) 
        || providerDto.getDvRut().isEmpty()) {
      notification.addError("'dvRut' can not be null or empty");
    }

    if (Objects.nonNull(providerDto.getDvRut()) 
        && providerDto.getDvRut().length() > env.getMaxLenghtDvRut()) {
      notification.addError("Lenght 'dvRut' can not be more lenght that " + env.getMaxLenghtDvRut());
    }

    if (Objects.isNull(providerDto.getNameVendor()) 
        || providerDto.getNameVendor().isEmpty()) {
      notification.addError("'nameVendor' can not be null or empty");
    }

    if (Objects.isNull(providerDto.getNameVendor())
        || providerDto.getNameVendor().length() > env.getMaxLenghtNameVendor()) {
      notification.addError("Lenght 'nameVendor' can not be more lenght that " + env.getMaxLenghtNameVendor());
    }

    if (Objects.isNull(providerDto.getAddress()) 
        || providerDto.getAddress().isEmpty()) {
      notification.addError("'address' can not be null or empty");
    }

    if (Objects.isNull(providerDto.getAddress()) 
        || providerDto.getAddress().length() > env.getMaxLenghtNameVendor()) {
      notification.addError("Lenght 'address' can not be more lenght that " + env.getMaxLenghtNameVendor());
    }

    if (Objects.isNull(providerDto.getCurrencyCode()) 
        || providerDto.getCurrencyCode().isEmpty()) {
      notification.addError("'currencyCode' can not be null or empty");
    }

    if (Objects.isNull(providerDto.getCurrencyCode())
        || providerDto.getCurrencyCode().length() > env.getMaxLenghtCurrencyCode()) {
      notification.addError("Lenght 'currencyCode' can not be more lenght that " + env.getMaxLenghtCurrencyCode());
    }

    if (Objects.nonNull(providerDto.getCity()) 
        && providerDto.getCity().length() > env.getMaxLenghtCity()) {
      notification.addError("Lenght 'city' can not be more lenght that " + env.getMaxLenghtCity());
    }

    if (Objects.isNull(providerDto.getCountry()) 
        || providerDto.getCountry().isEmpty()) {
      notification.addError("'country' can not be null or empty");
    }

    if (Objects.isNull(providerDto.getCountry()) 
        || providerDto.getCountry().length() > env.getMaxLenghtCountry()) {
      notification.addError("Lenght 'country' can not be more lenght that " + env.getMaxLenghtCountry());
    }

    if (Objects.isNull(providerDto.getCountryCode()) 
        || providerDto.getCountryCode().isEmpty()) {
      notification.addError("'countryCode' can not be null or empty");
    }

    if (Objects.isNull(providerDto.getCountryCode())
        || providerDto.getCountryCode().length() > env.getMaxLenghtCountryCode()) {
      notification.addError("Lenght 'countryCode' can not be more lenght that " + env.getMaxLenghtCountryCode());
    }

    if (Objects.nonNull(providerDto.getPhoneAreaCode())
        && providerDto.getPhoneAreaCode().length() > env.getMaxLenghtPhoneAreaCode()) {
      notification.addError("Lenght 'phoneAreaCode' can not be more lenght that " + env.getMaxLenghtPhoneAreaCode());
    }

    if (Objects.nonNull(providerDto.getPhoneNumber())
        && providerDto.getPhoneNumber().length() > env.getMaxLenghtPhoneNumber()) {
      notification.addError("Lenght 'phoneNumber' can not be more lenght that " + env.getMaxLenghtPhoneNumber());
    }

    if (Objects.nonNull(providerDto.getFaxNumber())
        && providerDto.getFaxNumber().length() > env.getMaxLenghtFaxNumber()) {
      notification.addError("Lenght 'faxNumber' can not be more lenght that " + env.getMaxLenghtFaxNumber());
    }

    if (Objects.nonNull(providerDto.getZipCode()) && providerDto.getZipCode().length() > env.getMaxLenghtZipCode()) {
      notification.addError("Lenght 'zipCode' can not be more lenght that " + env.getMaxLenghtZipCode());
    }

    if (Objects.isNull(providerDto.getEmail()) || providerDto.getEmail().isEmpty()) {
      notification.addError("'email' can not be null or empty");
    }

    if (Objects.isNull(providerDto.getEmail()) || providerDto.getEmail().length() > env.getMaxLenghtEmail()) {
      notification.addError("Lenght 'email' can not be more lenght that " + env.getMaxLenghtEmail());
    }
    
    if (Objects.nonNull(providerDto.getEmail()) 
        && !this.validatePattern(env.getFormatEmail(), providerDto.getEmail())) {
      notification.addError("the 'email' format has to be " + env.getFormatEmail());
    }
    return notification;
  }
  
  private boolean validatePattern(String regex, String pivot) {
    log.info("Into validatePattern(String regex, String pivot)");
    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern .matcher(pivot);
    return matcher.find();
  }
  
  private Notification createValidationFunctional(Provider provider) {
    Notification notification = new Notification();
    Optional<Provider> prov = providerRepository.getProviderByKey(provider.getRut(), provider.getCountryCode());
    log.info("Verify if exist Provider: " + prov.toString());
    if (prov.isPresent()) {
      notification.addError("Provider is already exists");
    }
    return notification;
  }
  
  private Notification updateValidationFunctional(Provider provider) {
    log.info("Into updateValidationFunctional(Provider provider)");
    Notification notification = new Notification();
    Optional<Provider> prov = providerRepository.getProviderByKey(provider.getRut(), provider.getCountryCode());
    if (Objects.isNull(prov)) {
      notification.addError("Provider not exists");
    }
    return notification;
  }
  
  private Provider assemblerProvider(ProviderDto providerDto) {
    log.info("Into assemblerProvider(ProviderDto providerDto)");
    return 
        Provider.builder()
        .rut(providerDto.getRut())
        .dvRut(providerDto.getDvRut())
        .nameVendor(providerDto.getNameVendor())
        .address(providerDto.getAddress())
        .currencyCode(providerDto.getCurrencyCode())
        .city(providerDto.getCity())
        .country(providerDto.getCountry())
        .countryCode(providerDto.getCountryCode())
        .phoneAreaCode(providerDto.getPhoneAreaCode())
        .phoneNumber(providerDto.getPhoneNumber())
        .faxNumber(providerDto.getFaxNumber())
        .zipCode(providerDto.getZipCode())
        .email(providerDto.getEmail())
        .comuna(providerDto.getComuna())
        .typeVendor(providerDto.getTypeVendor())
        .build();
    
    
    
    
    
  }

}
