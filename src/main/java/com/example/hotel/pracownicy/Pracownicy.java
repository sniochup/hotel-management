package com.example.hotel.pracownicy;

import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.uslugi.Uslugi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

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
            columnDefinition = "VARCHAR(20)"
    )
    private String imie;

    @Column(
            name = "nazwisko",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
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
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Integer placa_pod;

    @Column(
            name = "czy_zatrudniony",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean czy_zatrudniony;

    @ManyToOne
    @JoinColumn(name = "nazwa", nullable = false)
    private Stanowiska stanowisko;

    @ManyToOne
    @JoinColumn(name = "id_ulsugi", nullable = false)
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
