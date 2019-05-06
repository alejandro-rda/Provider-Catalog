package rtl.tot.corp.mrex.prcn.catalog.provider.application;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corp.falabella.api.response.common.application.Notification;
import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.prcn.catalog.provider.application.dto.ProviderDto;
//import rtl.tot.corp.mrex.prcn.catalog.provider.common.application.Notification;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.command.CommandBus;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.exception.IncompleteCommandException;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.repository.ProviderRepository;

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
    boolean isCreated = commandBus.execute(provider);
    if (!isCreated) {
      throw new IncompleteCommandException();
    }
    log.info("Sucessful Operation");
  }

  private Notification createValidation(ProviderDto providerDto) {
    Notification notification = new Notification();
    if (Objects.isNull(providerDto.getCountry()) || providerDto.getCountry().isEmpty()) {
      notification.addError("Country can not be null or empty");
    }
    if (Objects.isNull(providerDto.getNameVendor()) || providerDto.getNameVendor().isEmpty()) {
      notification.addError("Name can not be null or empty");
    }
    if (Objects.isNull(providerDto.getRut()) || providerDto.getRut().isEmpty()) {
      notification.addError("Rut can not be null or empty");
    }
    return notification;
  }
  
  private Notification createValidationFunctional(Provider provider) {
    Notification notification = new Notification();
    Provider prov = providerRepository.getProviderByRut(provider.getRut());
    if (Objects.nonNull(prov)) {
      notification.addError("Provider is already exists");
    }
    return notification;
  }

  private Provider assemblerProvider(ProviderDto providerDto) {
    log.info("Into assemblerProvider(ProviderDto providerDto)");
    return 
        Provider.builder()
        .rut(providerDto.getRut())
        .country(providerDto.getCountry())
        .name(providerDto.getNameVendor())
        .build();
  }

}
