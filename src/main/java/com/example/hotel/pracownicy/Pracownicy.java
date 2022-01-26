package com.example.hotel.pracownicy;

import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.uslugi.Uslugi;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Pracownicy")
@Table(
        name = "pracownicy",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_pracownika_unique", columnNames = "id_pracownika"),
                @UniqueConstraint(name = "pracownik_login_unique", columnNames = "login")
        }
)
@EqualsAndHashCode
public class Pracownicy implements UserDetails {

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
    private Date data_zatrudnienia;

    @Column(
            name = "placa_pod",
            nullable = false,
            columnDefinition = "NUMERIC(6, 2)"
    )
    private Float placa_pod;

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

    @Column(
            name = "login",
            nullable = false,
            columnDefinition = "VARCHAR(20)"
    )
    private String login;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    public Pracownicy(String imie,
                      String nazwisko,
                      Date data_zatrudnienia,
                      Float placa_pod,
                      Boolean czy_zatrudniony,
                      Stanowiska stanowisko,
                      String login,
                      String password) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_zatrudnienia = data_zatrudnienia;
        this.placa_pod = placa_pod;
        this.czy_zatrudniony = czy_zatrudniony;
        this.stanowisko = stanowisko;
        this.login = login;
        this.password = password;
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
                ", login='" + login + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("PRACOWNIK");
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
