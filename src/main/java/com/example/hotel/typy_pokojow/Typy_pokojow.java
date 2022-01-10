package com.example.hotel.typy_pokojow;

import javax.persistence.*;

@Entity(name = "Typy_pokojow")
@Table(
        name = "typy_pokojow",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_typu_unique", columnNames = "id_typu")
        }
)
public class Typy_pokojow {

    @Id
    @SequenceGenerator(
            name = "typy_pokojow_sequence",
            sequenceName = "typy_pokojow_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "typy_pokojow_sequence"
    )
    @Column(
            name = "id_typu",
            updatable = false
    )
    private Long id_typu;

    @Column(
            name = "standard",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String standard;

    @Column(
            name = "czy_klima",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean czy_klima;

    @Column(
            name = "czy_balkon",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean czy_balkon;

    @Column(
            name = "czy_kuchnia",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean czy_kuchnia;

    public Typy_pokojow(String standard,
                        Boolean czy_klima,
                        Boolean czy_balkon,
                        Boolean czy_kuchnia) {
        this.id_typu = id_typu;
        this.standard = standard;
        this.czy_klima = czy_klima;
        this.czy_balkon = czy_balkon;
        this.czy_kuchnia = czy_kuchnia;
    }

    public Typy_pokojow() {

    }

    public Long getId_typu() {
        return id_typu;
    }

    public void setId_typu(Long id_typu) {
        this.id_typu = id_typu;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Boolean getCzy_klima() {
        return czy_klima;
    }

    public void setCzy_klima(Boolean czy_klima) {
        this.czy_klima = czy_klima;
    }

    public Boolean getCzy_balkon() {
        return czy_balkon;
    }

    public void setCzy_balkon(Boolean czy_balkon) {
        this.czy_balkon = czy_balkon;
    }

    public Boolean getCzy_kuchnia() {
        return czy_kuchnia;
    }

    public void setCzy_kuchnia(Boolean czy_kuchnia) {
        this.czy_kuchnia = czy_kuchnia;
    }

    @Override
    public String toString() {
        return "Typ_pokoju{" +
                "id_typu=" + id_typu +
                ", standard='" + standard + '\'' +
                ", czy_klima='" + czy_klima + '\'' +
                ", czy_balkon=" + czy_balkon +
                ", czy_kuchnia=" + czy_kuchnia +
                '}';
    }

}

