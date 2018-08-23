package co.com.belatrix.moneyxchange.repos;

import co.com.belatrix.moneyxchange.models.CurrencyRate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "currencyRate", path = "currencyRate")
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, Long> {

    Iterable<CurrencyRate> findByName(@Param("name") String name);
    
    @Query("SELECT t FROM CurrencyRate t where t.code IN :codes")
    Iterable<CurrencyRate> findAllByCode(@Param("codes") Iterable<String> codes);
    
    CurrencyRate findByCode(@Param("code") String code);
}
