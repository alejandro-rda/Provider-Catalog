package rtl.tot.corp.mrex.vndm.provider.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.mrex.vndm.provider.domain.command.CommandBus;
import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;
import rtl.tot.corp.mrex.vndm.provider.domain.publisher.EventPublisherService;
import rtl.tot.corp.mrex.vndm.provider.domain.publisher.EventType;

@Service
@Slf4j
public class ProviderCommandBus implements CommandBus {

  @Autowired
  EventPublisherService publisher;
  
  @Override
  public boolean executeCreate(Provider provider) {
    log.info("Into execute(Provider provider)");
    return publisher.publish(EventType.PROVIDER_CREATED,provider);
  }
  
  @Override
  public boolean executeUpdate(Provider provider) {
    log.info("Into execute(Provider provider)");
    return publisher.publish(EventType.PROVIDER_UPDATED,provider);
  }
}
