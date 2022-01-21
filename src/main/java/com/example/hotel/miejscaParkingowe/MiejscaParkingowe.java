package com.example.hotel.miejscaParkingowe;

import com.example.hotel.rezerwacje.Rezerwacje;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Miejsca_parkingowe")
@Table(
        name = "miejsca_parkingowe",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_miejsca_unique", columnNames = "id_miejsca")
        }
)
public class MiejscaParkingowe {

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
            name = "id_miejsca",
            updatable = false
    )
    private Long id_miejsca;

    @Column(
            name = "cena",
            nullable = false,
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Float cena;

    @Column(
            name = "typ",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String typ;

    @ManyToMany(mappedBy = "miejsca_parkingowe")
    private Set<Rezerwacje> rezerwacje = new HashSet<>();

    public MiejscaParkingowe(Float cena,
                              String typ) {
        this.id_miejsca = id_miejsca;
        this.cena = cena;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "MiejscaParkingowe{" +
                "id_miejsca=" + id_miejsca +
                ", cena=" + cena +
                ", typ='" + typ + '\'' +
                '}';
    }
}
