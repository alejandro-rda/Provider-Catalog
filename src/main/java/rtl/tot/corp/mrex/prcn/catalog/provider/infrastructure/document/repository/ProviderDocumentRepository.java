package rtl.tot.corp.mrex.prcn.catalog.provider.infrastructure.document.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import rtl.tot.corp.mrex.prcn.catalog.provider.domain.entity.Provider;

 @Repository
public interface ProviderDocumentRepository extends MongoRepository<Provider, ObjectId> {
  
  @Query("{'rut': ?0}")
  public Provider getByRut(String rut);

}
