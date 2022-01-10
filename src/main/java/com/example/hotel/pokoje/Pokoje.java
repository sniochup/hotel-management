package com.example.hotel.pokoje;

import com.example.hotel.typy_pokojow.Typy_pokojow;

import javax.persistence.*;
import java.time.LocalDate;

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

        @Column(
                name = "cena",
                updatable = false,
                columnDefinition = "NUMBER"
        )
        private Integer cena;

        @Column(
                name = "liczba_osob",
                updatable = false,
                columnDefinition = "NUMBER"
        )
        private Integer liczba_osob;

        @Column(
                name = "metraz",
                updatable = false,
                columnDefinition = "NUMBER"
        )
        private Integer metraz;

        @Column(
                name = "czy_dostepny",
                updatable = true,
                columnDefinition = "BOOLEAN"
        )
        private Boolean czy_dostepny;

        @ManyToOne
        @JoinColumn(name = "id_typu", table = "Typy_pokojow")
        private Typy_pokojow typ_pokoju;

        public Pokoje(Integer cena,
                      Integer liczba_osob,
                      Integer metraz,
                      Boolean czy_dostepny,
                      Typy_pokojow typ_pokoju) {

                this.id_pokoju = id_pokoju;
                this.cena = cena;
                this.liczba_osob = liczba_osob;
                this.metraz = metraz;
                this.czy_dostepny = czy_dostepny;
                this.typ_pokoju = typ_pokoju;
        }

        public Pokoje() {

        }

        public Long getId_pokoju() {
                return id_pokoju;
        }

        public void setId_pokoju(Long id_pokoju) {
                this.id_pokoju = id_pokoju;
        }

        public Integer getCena() {
                return cena;
        }

        public void setCena(Integer cena) {
                this.cena = cena;
        }

        public Integer getLiczba_osob() {
                return liczba_osob;
        }

        public void setLiczba_osob(Integer liczba_osob) {
                this.liczba_osob = liczba_osob;
        }

        public Integer getMetraz() {
                return metraz;
        }

        public void setMetraz(Integer metraz) {
                this.metraz = metraz;
        }

        public Boolean getCzy_dostepny() {
                return czy_dostepny;
        }

        public void setCzy_dostepny(Boolean czy_dostepny) {
                this.czy_dostepny = czy_dostepny;
        }

        public Long getTyp_pokoju() {
                return typ_pokoju.getId_typu();
        }

        public void setTyp_pokoju(Long id_typu) {
                this.typ_pokoju.setId_typu(id_typu);
        }

        @Override
        public String toString() {
                return "Pokoj{" +
                        "id pokoju=" + id_pokoju +
                        ", cena='" + cena+ '\'' +
                        ", liczba_osob='" + liczba_osob + '\'' +
                        ", metraz=" + metraz +
                        ", id typu pokoju=" + typ_pokoju +
                        '}';
        }
}
