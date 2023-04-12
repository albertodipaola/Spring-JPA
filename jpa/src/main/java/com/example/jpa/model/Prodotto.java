package com.example.jpa.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prodotti")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "descrizione", length = 1000)
    private String descrizione;
    
    @Column(name = "prezzo")
    private float prezzo;
    
    @Column(name = "netto")
    private float prezzoNetto;

    @OneToMany(mappedBy = "prodotto")
    private List<Variante> varianti;

    @ManyToMany(mappedBy = "prodotti")

    private List<Fornitore> fornitori;

    public Prodotto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getPrezzoNetto() {
        return prezzoNetto;
    }

    public void setPrezzoNetto(float prezzoNetto) {
        this.prezzoNetto = prezzoNetto;
    }

    public List<Variante> getVarianti() {
        return varianti;
    }

    public void setVarianti(List<Variante> varianti) {
        this.varianti = varianti;
    }

    
}
