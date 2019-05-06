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
  
  @Value("${provider.param.rut.lenght}")
  private int lenghtProviderRut;
  
//  @Value("${provider.param.dvRut.lenght}")
//  private int lenghtDvRut;
  

}
