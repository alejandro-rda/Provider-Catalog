package rtl.tot.corp.mrex.prcn.catalog.provider.domain.publisher;

import java.time.LocalDateTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import corp.falabella.arq.event.Event;
import corp.falabella.arq.event.EventBuilder;
import corp.falabella.arq.event.provider.EventPublisher;
import corp.falabella.arq.infra.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
//import rtl.tot.corp.mrex.prcn.catalog.provider.common.application.EventProperties;
import corp.falabella.api.response.common.application.EventProperties;
import rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity.Provider;

/**
 * Clase que se encargara de publicar los eventos del proveedor correspondientes
 * a proveedor.<br/>
 * <b>Class</b>: EventPublisherService<br/>
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

@Slf4j
@Component
public class EventPublisherService {
  
  @Autowired
  private  EventPublisher eventPublisher;
  
  @Autowired
  private  EventProperties eventProperties;

  //  @Autowired
  //  public EventPublisherService(final EventPublisher eventPublisher, final EventProperties eventProperties) {
  //    this.eventPublisher = eventPublisher;
  //    this.eventProperties = eventProperties;
  //  }
  
  /**
   * Funcion que publica en el service Bus de Azure.
   * @param eventType tipo de evento.
   * @param provider provedor a ingresar.
   * @return conformidad de publicacion.
   */
  public boolean publish(EventType eventType, Provider provider) {
    log.info("Into publish(EventType eventType, EventDomain eventDomain)");
    try {
      final Event event = EventBuilder.newBuilder().generateEventId().eventType(eventType.toString())
          .entityId(this.getEntityId(provider))
          .entityType(this.getEntityType(provider))
          .dateTime(LocalDateTime.now())
          .version(eventProperties.getVersion())
          .country(eventProperties.getCountry())
          .commerce(eventProperties.getCommerce())
          .channel(eventProperties.getChannel())
          .mimeType(eventProperties.getMimeType())
          .metadata(this.getMetadata(provider)).build();
      return eventPublisher.publish(event);

    } catch (InvalidParameterException e) {
      log.error(eventType.toString() + " event could not be send. Cause: " + e.getMessage());
      return false;
    }
  }

  private String getEntityId(Provider provider) {
    log.info("Into getEntityId()");
    return provider.getRut();
  }
  
  // TODO
  private String getMetadata(Provider provider) {
    log.info("Into getMetadata()");
    ObjectMapper mapper = new ObjectMapper();
    String jsonValue;
    try {
      jsonValue = mapper.writeValueAsString(provider);
    } catch (JsonProcessingException e) {
      jsonValue = provider.toString();
    }
    return jsonValue;
  }

  private String getEntityType(Provider provider) {
    log.info("Into getEntityType()");
    return provider.getClass().getName();
  }

}
