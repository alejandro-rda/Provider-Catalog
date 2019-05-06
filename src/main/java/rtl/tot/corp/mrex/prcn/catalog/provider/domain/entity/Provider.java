package rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@Data
@Document(collection = "provider")
public class Provider {

  @Id
  private ObjectId _id;

  private String name;

  private String rut;

  private String country;

}
