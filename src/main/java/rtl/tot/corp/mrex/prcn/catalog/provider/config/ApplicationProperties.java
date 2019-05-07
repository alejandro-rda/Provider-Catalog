package rtl.tot.corp.mrex.prcn.catalog.provider.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que contendra las variables necesarias para el funcionamiento correcto
 * de la aplicacion.
 * Class: ApplicationProperties <br/>
 * Copyright: &copy; 2019 Tottus Corporativo;<br/>
 * Company: Tottus Corporativo;<br/>
 *
 * @author Tottus Corporativo; (TOT) <br/>
 *         <br/>
 *         Service Provider: Kruger; SAC (KR) <br/>
 *         Developed by:
 *         <ul>
 *         <li>Jose Lozano From (KR)</li>
 *         </ul>
 *         <br/>
 *         Changes:
 *         <ul>
 *         <li>2019-05-06 (JLP) Creaci&oacute;n de Clase.</li>
 *         </ul>
 * @version 1.0
 */
@Configuration
@Qualifier("env")
@Setter
@Getter
public class ApplicationProperties {
  
  @Value("${provider.param.rut.max-lenght}")
  private int maxLenghtProviderRut;
  
  @Value("${provider.param.dvRut.max-lenght}")
  private int maxLenghtDvRut;
  
  @Value("${provider.param.nameVendor.max-lenght}")
  private int maxLenghtNameVendor;
  
  @Value("${provider.param.address.max-lenght}")
  private int maxLenghtAddress;
    
  @Value("${provider.param.currencycode.max-lenght}")
  private int maxLenghtCurrencyCode;
  
  @Value("${provider.param.city.max-lenght}")
  private int maxLenghtCity;
  
  @Value("${provider.param.country.max-lenght}")
  private int maxLenghtCountry;
  
  @Value("${provider.param.country-code.max-lenght}")
  private int maxLenghtCountryCode;
  
  @Value("${provider.param.phone-area-code.max-lenght}")
  private int maxLenghtPhoneAreaCode;
  
  @Value("${provider.param.phone-number.max-lenght}")
  private int maxLenghtPhoneNumber;
  
  @Value("${provider.param.fax-number.max-lenght}")
  private int maxLenghtFaxNumber;
  
  @Value("${provider.param.zip-code.max-lenght}")
  private int maxLenghtZipCode;
  
  @Value("${provider.param.email.max-lenght}")
  private int maxLenghtEmail;
  
  @Value("${provider.param.email.format}")
  private String formatEmail;
  
  @Value("${provider.param.comuna.max-lenght}")
  private int maxLenghtComuna;
  
  @Value("${provider.param.type-vendor.max-lenght}")
  private int maxLenghtTypeVendor;
  
  
  
  
  
  
}
