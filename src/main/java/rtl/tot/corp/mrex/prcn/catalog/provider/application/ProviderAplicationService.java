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
//import falabella.rest.response.*;

@Service
@Slf4j
public class ProviderAplicationService {

  @Autowired
  private CommandBus commandBus;
  
  @Autowired
  private ProviderRepository providerRepository;

  public void createProvider(ProviderDto providerDto) throws IncompleteCommandException, Exception {
    log.info("Into createProvider(ProviderDto providerDto)");
    Notification notification = this.createValidation(providerDto);
    if (notification.hasErrors()) {
      throw new IllegalArgumentException(notification.errorMessage());
    }
    Provider provider = assemblerProvider( providerDto );
    notification = this.createValidationFunctional( provider );
    if (notification.hasErrors()) {
      throw new IllegalArgumentException(notification.errorMessage());
    }
    boolean isCreated = commandBus.execute(provider);
    if ( !isCreated ) {
      throw new IncompleteCommandException();
    }
    log.info("Sucessful Operation");
  }

  private Notification createValidation(ProviderDto providerDto) {
    Notification notification = new Notification();
    if (Objects.isNull(providerDto.getCountry()) || providerDto.getCountry().isEmpty()) {
      notification.addError("Country can not be null or empty");
    }
    if (Objects.isNull(providerDto.getName()) || providerDto.getName().isEmpty()) {
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
    return Provider.builder().rut(providerDto.getRut()).country(providerDto.getCountry()).name(providerDto.getName())
        .build();
  }

}
