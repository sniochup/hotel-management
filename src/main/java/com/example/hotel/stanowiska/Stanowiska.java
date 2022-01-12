package com.example.hotel.stanowiska;

import com.example.hotel.pracownicy.Pracownicy;
import com.example.hotel.rezerwacje.Rezerwacje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "Stanowiska")
@Table(
        name = "stanowiska",
        uniqueConstraints = {
                @UniqueConstraint(name = "nazwa_unique", columnNames = "nazwa")
        }
)


public class Stanowiska {

    @Id
    @Column(
            name = "nazwa",
            updatable = false
    )
    private String nazwa;

    @OneToMany(mappedBy = "stanowisko")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private Collection<Pracownicy> pracownik;

    @Column(
            name = "placa_min",
            nullable = false,
            columnDefinition = "NUMBER"
    )
    private Integer placa_min;

    @Column(
            name = "placa_max",
            nullable = false,
            columnDefinition = "NUMBER"
    )
    private Integer placa_max;

    public Stanowiska(String nazwa, Integer placa_min, Integer placa_max) {
        this.nazwa = nazwa;
        this.placa_min = placa_min;
        this.placa_max = placa_max;
    }

    public Stanowiska() {

    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getPlaca_min() {
        return placa_min;
    }

    public void setPlaca_min(Integer placa_min) {
        this.placa_min = placa_min;
    }

    public Integer getPlaca_max() {
        return placa_max;
    }

    public void setPlaca_max(Integer placa_max) {
        this.placa_max = placa_max;
    }

    @Override
    public String toString() {
        return "Stanowiska{" +
                "nazwa='" + nazwa + '\'' +
                ", placa_min=" + placa_min +
                ", placa_max=" + placa_max +
                '}';
    }
}
