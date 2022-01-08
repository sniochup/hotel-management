package com.example.hotel;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

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

    @Column(
            name = "rabaty_id_rabatu"
    )
    private Long rabaty_id_rabatu;

    @Column(
            name = "rabat_id_rabatu"
    )
    private Long rabat_id_rabatu;

    public Klient(String imie,
                  String nazwisko,
                  LocalDate rok_urodzenia,
                  Long rabaty_id_rabatu,
                  Long rabat_id_rabatu) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_urodzenia = rok_urodzenia;
        this.rabaty_id_rabatu = rabaty_id_rabatu;
        this.rabat_id_rabatu = rabat_id_rabatu;
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

    public Long getRabaty_id_rabatu() {
        return rabaty_id_rabatu;
    }

    public void setRabaty_id_rabatu(Long rabaty_id_rabatu) {
        this.rabaty_id_rabatu = rabaty_id_rabatu;
    }

    public Long getRabat_id_rabatu() {
        return rabat_id_rabatu;
    }

    public void setRabat_id_rabatu(Long rabat_id_rabatu) {
        this.rabat_id_rabatu = rabat_id_rabatu;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", rok_urodzenia=" + rok_urodzenia +
                ", rabaty_id_rabatu=" + rabaty_id_rabatu +
                ", rabat_id_rabatu=" + rabat_id_rabatu +
                '}';
    }
}
