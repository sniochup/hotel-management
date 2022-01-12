package com.example.hotel.pracownicy;

import com.example.hotel.klient.Klient;
import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.uslugi.Uslugi;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Pracownicy")
@Table(
        name = "pracownicy",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_pracownika_unique", columnNames = "id_pracownika")
        }
)

public class Pracownicy {

    @Id
    @SequenceGenerator(
            name = "pracownicy_sequence",
            sequenceName = "praconwicy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pracownicy_sequence"
    )
    @Column(
            name = "id_pracownika",
            updatable = false
    )
    private Long id_pracownika;

    @Column(
            name = "imie",
            nullable = false,
            columnDefinition = "STRING"
    )
    private String imie;

    @Column(
            name = "nazwisko",
            nullable = false,
            columnDefinition = "STRING"
    )
    private String nazwisko;

    @Column(
            name =  "data_zatrudnienia",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate data_zatrudnienia;

    @Column(
            name = "placa_pod",
            nullable = false,
            columnDefinition = "NUMBER"
    )
    private Integer placa_pod;

    @Column(
            name = "czy_zatrudniony",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean czy_zatrudniony;

    @ManyToOne
    @JoinColumn(name = "nazwa", table = "Stanowiska")
    private Stanowiska stanowisko;

    @ManyToOne
    @JoinColumn(name = "id_uslugi", table = "Uslugi")
    private Uslugi usluga;

    public Pracownicy(String imie, String nazwisko, LocalDate data_zatrudnienia, Integer placa_pod, Boolean czy_zatrudniony, Stanowiska stanowisko, Uslugi usluga) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_zatrudnienia = data_zatrudnienia;
        this.placa_pod = placa_pod;
        this.czy_zatrudniony = czy_zatrudniony;
        this.stanowisko = stanowisko;
        this.usluga = usluga;
    }

    public Pracownicy() {

    }

    public Long getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(Long id_pracownika) {
        this.id_pracownika = id_pracownika;
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

    public LocalDate getData_zatrudnienia() {
        return data_zatrudnienia;
    }

    public void setData_zatrudnienia(LocalDate data_zatrudnienia) {
        this.data_zatrudnienia = data_zatrudnienia;
    }

    public Integer getPlaca_pod() {
        return placa_pod;
    }

    public void setPlaca_pod(Integer placa_pod) {
        this.placa_pod = placa_pod;
    }

    public Boolean getCzy_zatrudniony() {
        return czy_zatrudniony;
    }

    public void setCzy_zatrudniony(Boolean czy_zatrudniony) {
        this.czy_zatrudniony = czy_zatrudniony;
    }

    public Stanowiska getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(Stanowiska stanowisko) {
        this.stanowisko = stanowisko;
    }

    public Uslugi getUsluga() {
        return usluga;
    }

    public void setUsluga(Uslugi usluga) {
        this.usluga = usluga;
    }

    @Override
    public String toString() {
        return "Pracownicy{" +
                "id_pracownika=" + id_pracownika +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_zatrudnienia=" + data_zatrudnienia +
                ", placa_pod=" + placa_pod +
                ", czy_zatrudniony=" + czy_zatrudniony +
                ", stanowisko=" + stanowisko +
                ", usluga=" + usluga +
                '}';
    }
}
