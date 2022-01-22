package com.example.hotel.pokoje;

import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rezerwacje.Rezerwacje;
import com.example.hotel.typyPokojow.TypyPokojow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Pokoje")
@Table(
        name = "pokoje",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_pokoju_unique", columnNames = "id_pokoju")
        }
)

public class Pokoje {
        @Id
        @SequenceGenerator(
                name = "pokoje_sequence",
                sequenceName = "pokoje_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "pokoje_sequence"
        )
        @Column(
                name = "id_pokoju",
                updatable = false
        )
        private Long id_pokoju;

        @ManyToMany(mappedBy = "pokoje")
        private Set<Rezerwacje> rezerwacja = new HashSet<>();

        @Column(
                name = "cena",
                nullable = false,
                columnDefinition = "NUMERIC(6, 2)"
        )
        private Float cena;

        @Column(
                name = "liczba_osob",
                nullable = false,
                columnDefinition = "NUMERIC(2)"
        )
        private Integer liczba_osob;

        @Column(
                name = "metraz",
                nullable = false,
                columnDefinition = "NUMERIC(2)"
        )
        private Integer metraz;

        @Column(
                name = "czy_dostepny",
                nullable = false,
                columnDefinition = "BOOLEAN"
        )
        private Boolean czy_dostepny;

        @ManyToOne
        @JoinColumn(name = "id_typu", nullable = false)
        private TypyPokojow typ_pokoju;

        public Pokoje(Long id_pokoju,
                      Float cena,
                      Integer liczba_osob,
                      Integer metraz,
                      Boolean czy_dostepny,
                      TypyPokojow typ_pokoju) {
                this.id_pokoju = id_pokoju;
                this.rezerwacja = rezerwacja;
                this.cena = cena;
                this.liczba_osob = liczba_osob;
                this.metraz = metraz;
                this.czy_dostepny = czy_dostepny;
                this.typ_pokoju = typ_pokoju;
        }

        public Pokoje(Float cena,
                      Integer liczba_osob,
                      Integer metraz,
                      Boolean czy_dostepny) {

                this.id_pokoju = id_pokoju;
                this.cena = cena;
                this.liczba_osob = liczba_osob;
                this.metraz = metraz;
                this.czy_dostepny = czy_dostepny;
        }

        @Override
        public String toString() {
                return "Pokoje{" +
                        "id_pokoju=" + id_pokoju +
                        ", cena=" + cena +
                        ", liczba_osob=" + liczba_osob +
                        ", metraz=" + metraz +
                        ", czy_dostepny=" + czy_dostepny +
                        ", typ_pokoju=" + typ_pokoju +
                        '}';
        }
}
