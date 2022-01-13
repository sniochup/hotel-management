package com.example.hotel.uslugi;

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
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Uslugi")
@Table(
        name = "uslugi",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_uslugi_unique", columnNames = "id_uslugi")
        }
)
public class Uslugi {

    @Id
    @SequenceGenerator(
            name = "uslugi_sequence",
            sequenceName = "uslugi_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "uslugi_sequence"
    )
    @Column(
            name = "id_uslugi",
            updatable = false
    )
    private Long id_uslugi;

    @ManyToMany(mappedBy = "uslugi")
    private Set<Rezerwacje> rezerwacje = new HashSet<>();

    @Column(
            name = "nazwa",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String nazwa;

    @OneToMany(mappedBy = "usluga")
    private Set<Pracownicy> pracownik;

    @Column(
            name = "cena",
            nullable = false,
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Integer cena;

    public Uslugi(String nazwa, Integer cena) {
        this.id_uslugi = id_uslugi;
        this.nazwa = nazwa;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Uslugi{" +
                "id_uslugi=" + id_uslugi +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                '}';
    }
}
