package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.miejscaParkingowe.MiejscaParkingowe;
import com.example.hotel.pakietyWyzywien.PakietyWyzywien;
import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.typyPokojow.TypyPokojow;
import com.example.hotel.uslugi.Uslugi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Rezerwacje")
@Table(
        name = "rezerwacje",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_rezerwacji_unique", columnNames = "id_rezerwacji")
        }
)

public class Rezerwacje {
    @Id
    @SequenceGenerator(
            name = "rezerwacje_sequence",
            sequenceName = "rezerwacje_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rezerwacje_sequence"
    )
    @Column(
            name = "id_rezerwacji",
            updatable = false
    )
    private Long id_rezerwacji;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "zarezerwowane_pokoje",
            joinColumns = @JoinColumn(name = "id_rezerwacji"),
            inverseJoinColumns = @JoinColumn(name = "id_pokoju")
    )
    private Set<Pokoje> pokoje = new HashSet<>();

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "zarezerw_miejsca_parkingowe",
            joinColumns = @JoinColumn(name = "id_rezerwacji"),
            inverseJoinColumns = @JoinColumn(name = "id_miejsca")
    )
    private Set<MiejscaParkingowe> miejsca_parkingowe = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_pakietu", nullable = true)
    private PakietyWyzywien pakietyWyzywien;

    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "zamowione_uslugi",
            joinColumns = @JoinColumn(name = "id_rezerwacji"),
            inverseJoinColumns = @JoinColumn(name = "id_uslugi")
    )
    private Set<Uslugi> uslugi = new HashSet<>();

    @Column(
            name = "data_od",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date data_od;

    @Column(
            name = "data_do",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date data_do;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String status;

    @Column(
            name = "platnosc",
//            nullable = false,
            columnDefinition = "NUMERIC(10, 2)"
    )
    private Float platnosc;

    @Column(
            name = "status_platnosci",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String status_platnosci;

    @ManyToOne
    @JoinColumn(name = "id_klienta", nullable = false)
    private Klienci klient;

    public Rezerwacje(Date data_od,
                      Date data_do,
                      String status,
                      Float platnosc,
                      String status_platnosci) {
        this.id_rezerwacji = id_rezerwacji;
        this.data_od = data_od;
        this.data_do = data_do;
        this.status = status;
        this.platnosc = platnosc;
        this.status_platnosci = status_platnosci;
    }

    @Override
    public String toString() {
        return "Rezerwacje{" +
                "id_rezerwacji=" + id_rezerwacji +
                ", data_od=" + data_od +
                ", data_do=" + data_do +
                ", status='" + status + '\'' +
                ", platnosc=" + platnosc +
                ", status_platnosci='" + status_platnosci + '\'' +
                ", klient=" + klient +
                '}';
    }
}
