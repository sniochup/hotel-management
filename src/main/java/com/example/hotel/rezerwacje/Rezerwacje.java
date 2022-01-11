package com.example.hotel.rezerwacje;

import com.example.hotel.klient.Klient;
import com.example.hotel.pokoje.Pokoje;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @Column(
            name = "data_od",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate data_od;

    @Column(
            name = "data_do",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate data_do;

    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "STRING"
    )
    private String status;

    @Column(
            name = "platnosc",
            nullable = false,
            columnDefinition = "NUMBER"
    )
    private Integer platnosc;

    @Column(
            name = "status_platnosci",
            nullable = false,
            columnDefinition = "STRING"
    )
    private String status_platnosci;

    @ManyToOne
    @JoinColumn(name = "id_klienta", table = "Klient")
    private Klient klient;

    public Rezerwacje(LocalDate data_od,
                  LocalDate data_do,
                  String status,
                  Integer platnosc,
                  String status_platnosci) {
        this.id_rezerwacji = id_rezerwacji;
        this.data_od = data_od;
        this.data_do = data_do;
        this.status = status;
        this.platnosc = platnosc;
        this.status_platnosci = status_platnosci;
    }

    public Rezerwacje() {

    }

    public Long getId_rezerwacji() {
        return id_rezerwacji;
    }

    public void setId_rezerwacji(Long id_rezerwacji) {
        this.id_rezerwacji = id_rezerwacji;
    }

    public LocalDate getData_od() {
        return data_od;
    }

    public void setData_od(LocalDate data_od) {
        this.data_od = data_od;
    }

    public LocalDate getData_do() {
        return data_do;
    }

    public void setData_do(LocalDate data_do) {
        this.data_od = data_do;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPlatnosc() {
        return platnosc;
    }

    public void setPlatnosc(Integer platnosc) {
        this.platnosc = platnosc;
    }

    public String getStatus_platnosci() {
        return status_platnosci;
    }

    public void setStatus_platnosci(String status_platnosci) {
        this.status_platnosci = status_platnosci;
    }

    public Long getId_klienta() {
        return klient.getId();
    }

    public void setId_klienta(Long id_klienta) {
        this.klient.setId(id_klienta);
    }


    @Override
    public String toString() {
        return "Rezerwacja{" +
                "id rezerwacji=" + id_rezerwacji +
                ", data od=" + data_od + '\'' +
                ", data do=" + data_do + '\'' +
                ", status=" + status + '\'' +
                ", platnosc=" + platnosc + '\'' +
                ", status platnosci=" + status_platnosci + '\'' +
                ", id klienta=" + klient +
                '}';
    }

}
