package com.example.hotel.rabaty;

import com.example.hotel.klient.Klient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Rabaty")
@Table(
        name = "rabaty",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_rabatu_unique", columnNames = "id_rabatu")
        }
)
public class Rabaty {
    @Id
    @SequenceGenerator(
            name = "rabaty_sequence",
            sequenceName = "rabaty_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rabaty_sequence"
    )
    @Column(
            name = "id_rabatu",
            updatable = false
    )
    private Long id_rabatu;

    @OneToMany(mappedBy = "rabat")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Collection<Klient> klient;

    @Column(
            name = "wysokosc_rabatu",
            nullable = false,
            columnDefinition = "FLOAT"
    )
    private Float wysokosc_rabatu;

    @Column(
            name = "typ",
            nullable = false,
            columnDefinition = "STRING"
    )
    private String typ;

    public Rabaty(Float wysokosc_rabatu,
                  String typ) {
        this.id_rabatu = id_rabatu;
        this.wysokosc_rabatu = wysokosc_rabatu;
        this.typ = typ;
    }

    public Rabaty() {

    }

    public Long getId_rabatu() {
        return id_rabatu;
    }

    public void setId_rabatu(Long id_rabatu) {
        this.id_rabatu = id_rabatu;
    }

    public Float getWysokosc_rabatu() {
        return wysokosc_rabatu;
    }

    public void setWysokosc_rabatu(Float wysokosc_rabatu) {
        this.wysokosc_rabatu = wysokosc_rabatu;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "Rabat{" +
                "id_rabatu=" + id_rabatu +
                ", wysokosc_rabatu='" + wysokosc_rabatu + '\'' +
                ", typ='" + typ + '\'' +
                '}';
    }

}
