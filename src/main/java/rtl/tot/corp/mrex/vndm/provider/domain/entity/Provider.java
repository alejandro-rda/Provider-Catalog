package rtl.tot.corp.mrex.vndm.provider.domain.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Document(collection = "provider4")
public class Provider {

  @Id
  private ObjectId _id;

  private String rut;
  private String dvRut;
  private String nameVendor;
  private String address;
  private String currencyCode;
  private String city;
  private String country;
  private String countryCode;
  private String phoneAreaCode;
  private String phoneNumber;
  private String faxNumber;
  private String zipCode;
  private String email;
  private String comuna;
  private String typeVendor;
  
  public Provider() {
    super();
  }
  

}
