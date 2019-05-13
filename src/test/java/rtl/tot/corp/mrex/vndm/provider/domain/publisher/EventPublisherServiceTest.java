package rtl.tot.corp.mrex.vndm.provider.domain.publisher;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import corp.falabella.api.response.common.application.EventProperties;
import corp.falabella.arq.event.provider.EventPublisher;
import rtl.tot.corp.mrex.vndm.provider.util.UtilTest;

@RunWith(SpringRunner.class)
public class EventPublisherServiceTest {

  @InjectMocks
  EventPublisherService eventPublisherService;
  
  @Mock
  EventPublisher eventPublisher;
  
  @Mock
  EventProperties eventProperties;
  
  
  
  
  @Test
  public void publishSucessfull() {
    when(eventProperties.getVersion()).thenReturn("1.0");
    when(eventProperties.getCountry()).thenReturn("PERU");
    when(eventProperties.getCommerce()).thenReturn(null);
    when(eventProperties.getChannel()).thenReturn("RTL");
    when(eventProperties.getMimeType()).thenReturn("SDL");
    when(eventPublisher.publish(Mockito.any())).thenReturn(true);
    
    eventPublisherService.publish(EventType.PROVIDER_CREATED,UtilTest.getProvider().get());
  }
  
  @Test
  public void publishError() {
    when(eventProperties.getVersion()).thenReturn("1.0");
    when(eventProperties.getCountry()).thenReturn("PERU");
    when(eventProperties.getCommerce()).thenReturn("XX");
    when(eventProperties.getChannel()).thenReturn("RTL");
    when(eventProperties.getMimeType()).thenReturn("SDL");
    when(eventPublisher.publish(Mockito.any())).thenReturn(true);
    
    eventPublisherService.publish(EventType.PROVIDER_CREATED,UtilTest.getProvider().get());
  }
}
