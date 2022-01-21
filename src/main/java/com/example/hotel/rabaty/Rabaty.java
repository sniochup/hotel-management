package com.example.hotel.rabaty;

import com.example.hotel.klienci.Klienci;
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

@Entity(name = "Rabaty")
@Table(
        name = "rabaty",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_rabatu_unique", columnNames = "id_rabatu")
        }
)
public class Rabaty {
    @Id
    @SequenceGenerator(
            name = "rabaty_sequence",
            sequenceName = "rabaty_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rabaty_sequence"
    )
    @Column(
            name = "id_rabatu",
            updatable = false,
            nullable = false
    )
    private Long id_rabatu;

    @OneToMany(mappedBy = "rabat")
    private Set<Klienci> klient;

    @Column(
            name = "wysokosc_rabatu",
            nullable = false,
            columnDefinition = "NUMERIC(3)"
    )
    private Integer wysokosc_rabatu;

    @Column(
            name = "typ",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String typ;

    public Rabaty(Integer wysokosc_rabatu,
                  String typ) {
        this.id_rabatu = id_rabatu;
        this.wysokosc_rabatu = wysokosc_rabatu;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "Rabaty{" +
                "id_rabatu=" + id_rabatu +
                ", wysokosc_rabatu=" + wysokosc_rabatu +
                ", typ='" + typ + '\'' +
                '}';
    }
}
