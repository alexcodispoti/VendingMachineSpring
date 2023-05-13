package com.distributore.distributore.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tessere")
public class Tessera {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    String codice;
    Float credito;
    
    public Tessera(String codice, Float credito) {
        this.codice = codice;
        this.credito = credito;
    }

    public Tessera(){
        
    }
    public String getCodice() {
        return codice;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }
    public Float getCredito() {
        return credito;
    }
    public void setCredito(Float credito) {
        this.credito = credito;
    }

    


    
}
