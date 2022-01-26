package com.example.hotel.pakietyWyzywien;

import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.rezerwacje.Rezerwacje;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Pakiety_wyzywien")
@Table(
        name = "pakiety_wyzywien",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_pakietu_unique", columnNames = "id_pakietu")
        }
)

public class PakietyWyzywien {

    @Id
    @SequenceGenerator(
            name = "pakiet_wyzywienia_sequence",
            sequenceName = "pakiet_wyzywienia_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pakiet_wyzywienia_sequence"
    )
    @Column(
            name = "id_pakietu",
            updatable = false
    )
    private Long id_pakietu;

    @Column(
            name = "typ",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String typ;

    @Column(
            name = "cena_dzien",
            nullable = false,
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Float cena_dzien;

    @OneToMany(mappedBy = "pakietyWyzywien")
    private Set<Rezerwacje> rezerwacjeSet;


    public PakietyWyzywien(String typ, Float cena_dzien) {
        this.id_pakietu = id_pakietu;
        this.typ = typ;
        this.cena_dzien = cena_dzien;
    }

    @Override
    public String toString() {
        return "PakietyWyzywien{" +
                "id_pakietu=" + id_pakietu +
                ", typ='" + typ + '\'' +
                ", cena_dzien=" + cena_dzien +
                '}';
    }
}
