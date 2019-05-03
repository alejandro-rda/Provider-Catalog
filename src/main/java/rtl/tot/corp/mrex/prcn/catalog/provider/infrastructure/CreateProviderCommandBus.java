package rtl.tot.corp.mrex.prcn.catalog.provider.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.command.CommandBus;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.publisher.EventPublisherService;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.publisher.EventType;

@Service
@Slf4j
public class CreateProviderCommandBus implements CommandBus {

  @Autowired
  EventPublisherService publisher;
  
  @Override
  public boolean execute(Provider provider) {
    log.info("Into execute(Provider provider)");
    return publisher.publish(EventType.PROVIDER_CREATED,provider);
  }

}
