package com.distributore.distributore.model;


import jakarta.persistence.*;


@Entity
@Table(name="colonne")
public class Colonna {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    Integer numero;
    String nome;
    Integer quantita;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Colonna(Integer numero, String nome, Integer quantita) {
        this.numero = numero;
        this.nome = nome;
        this.quantita = quantita;
    }
    public Colonna(){

    }
    
}
