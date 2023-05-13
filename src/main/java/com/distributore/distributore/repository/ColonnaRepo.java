package com.distributore.distributore.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import com.distributore.distributore.model.Colonna;

public interface ColonnaRepo extends CrudRepository<Colonna,Long> {
    
    ArrayList<Colonna> findByNome(String nome);
}
