package com.example.hotel.klient;

import com.example.hotel.rabaty.Rabaty;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Klient")
@Table(
        name = "klient",
        uniqueConstraints = {
                @UniqueConstraint(name = "client_imie_unique", columnNames = "imie")
        }
)
public class Klient {

    @Id
    @SequenceGenerator(
            name = "klient_sequence",
            sequenceName = "klient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "klient_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_rabatu", table = "Rabaty")
    private Rabaty rabat;

    @Column(
            name = "imie",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String imie;

    @Column(
            name = "nazwisko",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nazwisko;

    @Column(
            name = "rok_urodzenia",
            nullable = false,
            columnDefinition = "date"
    )
    private LocalDate rok_urodzenia;


    public Klient(String imie,
                  String nazwisko,
                  LocalDate rok_urodzenia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_urodzenia = rok_urodzenia;
    }

    public Klient() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public LocalDate getRok_urodzenia() {
        return rok_urodzenia;
    }

    public void setRok_urodzenia(LocalDate rok_urodzenia) {
        this.rok_urodzenia = rok_urodzenia;
    }

    public Long getId_rabatu() {
        return rabat.getId_rabatu();
    }

    public void setId_rabatu(Long id_rabatu) {
        this.rabat.setId_rabatu(id_rabatu);
    }


    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", rok_urodzenia=" + rok_urodzenia +
                ", id_rabatu=" + rabat +
                '}';
    }
}
