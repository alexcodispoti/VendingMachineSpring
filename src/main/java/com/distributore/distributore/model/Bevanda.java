package com.distributore.distributore.model;


import jakarta.persistence.*;


@Entity
@Table(name="bevande")
public class Bevanda {
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
    String nome;
    Float prezzo;

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Bevanda(String codice, String nome, Float prezzo) {
        this.codice = codice;
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Bevanda (){
        
    }


    }

