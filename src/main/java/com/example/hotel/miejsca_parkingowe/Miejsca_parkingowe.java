package com.example.hotel.miejsca_parkingowe;

import javax.persistence.*;

@Entity(name = "Miejsca_parkingowe")
@Table(
        name = "miejsca_parkingowe",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_miejsca_unique", columnNames = "id_miejsca")
        }
)

public class Miejsca_parkingowe {

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
            updatable = false,
            columnDefinition = "NUMBER"
    )
    private Integer cena;

   @Column(
           name = "typ",
           updatable = false,
           columnDefinition = "STRING"
   )
    private String typ;

   public Miejsca_parkingowe(Integer cena,
                             String typ) {
       this.id_miejsca = id_miejsca;
       this.cena = cena;
       this.typ = typ;
   }

   public Miejsca_parkingowe() {

   }

   public Long getId_miejsca() {
       return id_miejsca;
   }

   public void setId_miejsca(Long id_miejsca) {
       this.id_miejsca = id_miejsca;
   }

   public Integer getCena() {
       return cena;
   }

   public void setCena(Integer cena) {
       this.cena = cena;
   }

   public String getTyp() {
       return typ;
   }

   public void setTyp(String typ) {
       this.typ = typ;
   }

    @Override
    public String toString() {
        return "Miejsce parkingowe{" +
                "id miejsca=" + id_miejsca +
                ", cena='" + cena+ '\'' +
                ", typ='" + typ +
                '}';
    }


}
