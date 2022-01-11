package com.example.hotel.pakiety_wyzywien;

import javax.persistence.*;

@Entity(name = "Pakiety_wyzywien")
@Table(
        name = "pakiety_wyzywien",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_pakietu_unique", columnNames = "id_pakietu")
        }
)

public class Pakiety_wyzywien {

    @Id
    @SequenceGenerator(
            name = "parking_sequence",
            sequenceName = "parking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parking_sequence"
    )
    @Column(
            name = "id_pakietu",
            updatable = false
    )
    private Long id_pakietu;

    @Column(
            name = "typ",
            updatable = false,
            columnDefinition = "STRING"
    )
    private String typ;

    @Column(
            name = "cena_dzien",
            updatable = false,
            columnDefinition = "NUMBER"
    )
    private Integer cena_dzien;


    public Pakiety_wyzywien(String typ, Integer cena_dzien) {
        this.id_pakietu = id_pakietu;
        this.typ = typ;
        this.cena_dzien = cena_dzien;
    }

    public Pakiety_wyzywien() {

    }

    public Long getId_pakietu() {
        return id_pakietu;
    }

    public void setId_pakietu(Long id_pakietu) {
        this.id_pakietu = id_pakietu;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Integer getCena_dzien() {
        return cena_dzien;
    }

    public void setCena_dzien(Integer cena_dzien) {
        this.cena_dzien = cena_dzien;
    }

    @Override
    public String toString() {
        return "Pakiety_wyzywien{" +
                "id_pakietu=" + id_pakietu +
                ", typ='" + typ + '\'' +
                ", cena_dzien=" + cena_dzien +
                '}';
    }
}
