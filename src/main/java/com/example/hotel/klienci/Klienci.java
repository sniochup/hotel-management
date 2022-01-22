package com.example.hotel.klienci;

import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rezerwacje.Rezerwacje;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity(name = "Klienci")
@Table(
        name = "klienci",
        uniqueConstraints = {
                @UniqueConstraint(name = "client_login_unique", columnNames = "login"),
                @UniqueConstraint(name = "client_id_unique", columnNames = "id_klienta")
        }
)
@EqualsAndHashCode
public class Klienci implements UserDetails {

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
            name = "id_klienta",
            updatable = false
    )
    private Long id_klienta;

    @ManyToOne
    @JoinColumn(name = "id_rabatu")
    private Rabaty rabat;

    @OneToMany(mappedBy = "klient")
    private Set<Rezerwacje> rezerwacje;

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
            name = "rok_urodzenia",
            nullable = false,
            columnDefinition = "date"
    )
    private Date rok_urodzenia;

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


    public Klienci(String imie,
                   String nazwisko,
                   Date rok_urodzenia,
                   String login,
                   String password) {

        this.id_klienta = id_klienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.rok_urodzenia = rok_urodzenia;
        this.login = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("KLIENT");
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

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id_klienta +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", rok_urodzenia=" + rok_urodzenia +  '\'' +
                ", login=" + login + '\'' +
                ", id_rabatu=" + rabat + '\'' +
                '}';
    }
}
