package com.example.hotel.klient;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity(name = "Klient")
@Table(
        name = "klient",
        uniqueConstraints = {
                @UniqueConstraint(name = "client_login_unique", columnNames = "login")
        }
)
@EqualsAndHashCode
public class Klient implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "klient_sequence",
            sequenceName = "klient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "klient_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "imie",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String imie;

    @Column(
            name = "nazwisko",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nazwisko;

    @Column(
            name = "rok_urodzenia",
            nullable = false,
            columnDefinition = "date"
    )
    private LocalDate rok_urodzenia;

    @Column(
            name = "rabaty_id_rabatu"
    )
    private Long rabaty_id_rabatu;

    @Column(
            name = "rabat_id_rabatu"
    )
    private Long rabat_id_rabatu;

    @Column(
            name = "login",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String login;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    public Klient(String imie,
                  String nazwisko,
                  LocalDate rok_urodzenia,
                  Long rabaty_id_rabatu,
                  Long rabat_id_rabatu,
                  String login,
                  String password) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_urodzenia = rok_urodzenia;
        this.rabaty_id_rabatu = rabaty_id_rabatu;
        this.rabat_id_rabatu = rabat_id_rabatu;
        this.login = login;
        this.password = password;
    }

    public Klient() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public LocalDate getRok_urodzenia() {
        return rok_urodzenia;
    }

    public void setRok_urodzenia(LocalDate rok_urodzenia) {
        this.rok_urodzenia = rok_urodzenia;
    }

    public Long getRabaty_id_rabatu() {
        return rabaty_id_rabatu;
    }

    public void setRabaty_id_rabatu(Long rabaty_id_rabatu) {
        this.rabaty_id_rabatu = rabaty_id_rabatu;
    }

    public Long getRabat_id_rabatu() {
        return rabat_id_rabatu;
    }

    public void setRabat_id_rabatu(Long rabat_id_rabatu) {
        this.rabat_id_rabatu = rabat_id_rabatu;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("USER");
        return Collections.singletonList(authority);
    }

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", rok_urodzenia=" + rok_urodzenia +
                ", rabaty_id_rabatu=" + rabaty_id_rabatu +
                ", rabat_id_rabatu=" + rabat_id_rabatu +
                ", login=" + login +
                '}';
    }
}
