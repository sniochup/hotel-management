package com.example.hotel.typyPokojow;

import com.example.hotel.pokoje.Pokoje;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Typy_pokojow")
@Table(
        name = "typy_pokojow",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_typu_unique", columnNames = "id_typu")
        }
)
public class TypyPokojow {

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

    @OneToMany(mappedBy = "typ_pokoju")
    private Set<Pokoje> pokoj;

    @Column(
            name = "standard",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
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

    public TypyPokojow(String standard,
                       Boolean czy_klima,
                       Boolean czy_balkon,
                       Boolean czy_kuchnia) {
        this.id_typu = id_typu;
        this.standard = standard;
        this.czy_klima = czy_klima;
        this.czy_balkon = czy_balkon;
        this.czy_kuchnia = czy_kuchnia;
    }

    @Override
    public String toString() {
        return "TypyPokojow{" +
                "id_typu=" + id_typu +
                ", standard='" + standard + '\'' +
                ", czy_klima=" + czy_klima +
                ", czy_balkon=" + czy_balkon +
                ", czy_kuchnia=" + czy_kuchnia +
                '}';
    }
}

