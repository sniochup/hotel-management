package com.example.hotel.pracownicy;

import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.uslugi.Uslugi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
            sequenceName = "pracownicy_sequence",
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

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "wykonywane_uslugi",
            joinColumns = @JoinColumn(name = "id_pracownika"),
            inverseJoinColumns = @JoinColumn(name = "id_uslugi")
    )
    private Set<Uslugi> uslugi = new HashSet<>();

    public Pracownicy(String imie,
                      String nazwisko,
                      LocalDate data_zatrudnienia,
                      Integer placa_pod,
                      Boolean czy_zatrudniony,
                      Stanowiska stanowisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_zatrudnienia = data_zatrudnienia;
        this.placa_pod = placa_pod;
        this.czy_zatrudniony = czy_zatrudniony;
        this.stanowisko = stanowisko;
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
                ", uslugi=" + uslugi +
                '}';
    }
}
