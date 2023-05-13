package com.distributore.distributore.repository;

import org.springframework.data.repository.CrudRepository;
import com.distributore.distributore.model.Bevanda;

public interface BevandaRepo extends CrudRepository<Bevanda,Long>  {
    
    Bevanda findByCodice(String codicebevanda);
    
}
