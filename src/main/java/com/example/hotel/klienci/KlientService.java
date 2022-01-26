package com.example.hotel.klienci;

import com.example.hotel.pracownicy.PracownicyRepository;
import com.example.hotel.rabaty.Rabaty;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KlientService implements UserDetailsService {

    private final KlientRepository klientRepository;
    private final PracownicyRepository pracownicyRepository;

    @Autowired
    public KlientService(KlientRepository klientRepository, PracownicyRepository pracownicyRepository) {
        this.klientRepository = klientRepository;
        this.pracownicyRepository = pracownicyRepository;
    }

    public List<Klienci> getKlients() {
        return klientRepository.findAll();
    }

    public Optional<Klienci> getKlientByLogin(String login) {
        return klientRepository.findByLogin(login);
    }

    public List<Klienci> getKlientsSortByImie() {
        return klientRepository.findAll(Sort.by(Sort.Direction.ASC, "imie"));
    }

    public List<Klienci> getKlientsSortByImieNazwisko() {
        return klientRepository.findAll(Sort.by(Sort.Direction.ASC, "nazwisko"));
    }

    public List<Klienci> getKlientSortByLogin() {
        return klientRepository.findAll(Sort.by(Sort.Direction.ASC, "login"));
    }

    public void addNewKlient(Klienci klient) {
        LocalDate localDate = (klient.getRok_urodzenia()).toLocalDate();
        if (Period.between(localDate, LocalDate.now()).getYears() < 18) {
            throw new IllegalStateException("Klient too young!");
        }
        else if (klientRepository.findByLogin(klient.getLogin()).isPresent() || pracownicyRepository.findByLogin(klient.getLogin()).isPresent() || Objects.equals(klient.getLogin(), "admin")) {
            throw new DataIntegrityViolationException("Login exist!");
        }
        klientRepository.save(klient);
    }

    public void deleteKlient(Long klientid) {
        boolean exist = klientRepository.existsById(klientid);
        if (!exist) {
            throw new IllegalStateException(
                    "klient with id " + klientid + " does not exists");
        }
        klientRepository.deleteById(klientid);
    }

    public void setRabat(String login, Rabaty rabaty) {
        Klienci klient = klientRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalStateException(
                        "klient with login " + login + "does not exists"));
        klient.setRabat(rabaty);
    }

    @Transactional
    public void updateKlient(Long klientId, String imie, String nazwisko) {
        Klienci klient = klientRepository.findById(klientId)
                .orElseThrow(() -> new IllegalStateException(
                    "klient with id " + klientId + "does not exists"));
        if (imie != null && imie.length() > 0 &&
                !Objects.equals(klient.getImie(), imie)) {
            klient.setImie(imie);
        }
        if (nazwisko != null && nazwisko.length() > 0 &&
                !Objects.equals(klient.getNazwisko(), nazwisko)) {
            klient.setNazwisko(nazwisko);
        }
    }

    public Klienci authenticate(String login, String password) {
        return klientRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        return (UserDetails) klientRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with login " + login + " not found"));
    }
}
