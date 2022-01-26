package com.example.hotel.stanowiska;

import com.example.hotel.pracownicy.Pracownicy;
import com.example.hotel.rezerwacje.Rezerwacje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

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
            updatable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String nazwa;

    @OneToMany(mappedBy = "stanowisko")
    private Set<Pracownicy> pracownik;

    @Column(
            name = "placa_min",
            nullable = false,
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Float placa_min;

    @Column(
            name = "placa_max",
            nullable = false,
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Float placa_max;

    public Stanowiska(String nazwa,
                      Float placa_min,
                      Float placa_max) {
        this.nazwa = nazwa;
        this.placa_min = placa_min;
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
