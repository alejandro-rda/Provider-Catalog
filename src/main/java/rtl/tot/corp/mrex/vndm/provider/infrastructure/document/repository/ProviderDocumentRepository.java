package rtl.tot.corp.mrex.vndm.provider.infrastructure.document.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import rtl.tot.corp.mrex.vndm.provider.domain.entity.Provider;

@Repository
public interface ProviderDocumentRepository extends MongoRepository<Provider, ObjectId> {
  
  @Query("{'rut': ?0}")
  public Optional<Provider> getByRut(String rut);
  
  @Query("{'rut': ?0, 'countryCode': ?1}")
  public Optional<Provider> getProvider(String rut, String countryCode);
  
}
