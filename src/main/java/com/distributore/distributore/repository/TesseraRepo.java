package com.distributore.distributore.repository;

import org.springframework.data.repository.CrudRepository;
import com.distributore.distributore.model.Tessera;

public interface TesseraRepo extends CrudRepository<Tessera,Long>{

    Tessera findByCodice(String codicetessera);
    
}
