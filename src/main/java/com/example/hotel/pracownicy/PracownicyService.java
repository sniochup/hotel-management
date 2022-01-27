package com.example.hotel.pracownicy;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientRepository;
import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.stanowiska.StanowiskaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PracownicyService implements UserDetailsService {

    private final PracownicyRepository pracownicyRepository;
    private final KlientRepository klientRepository;
    private final StanowiskaRepository stanowiskaRepository;

    @Autowired
    public PracownicyService(PracownicyRepository pracownicyRepository,
                             KlientRepository klientRepository,
                             StanowiskaRepository stanowiskaRepository) {
        this.pracownicyRepository = pracownicyRepository;
        this.klientRepository = klientRepository;
        this.stanowiskaRepository = stanowiskaRepository;
    }

    public Pracownicy authenticate(String login, String password) {
        return pracownicyRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    public Optional<Pracownicy> getById(Long id) {
        return pracownicyRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        return (UserDetails) pracownicyRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with login " + login + " not found"));
    }

    public void addNewPracownik(Pracownicy pracownicy) {

        Stanowiska stanowiska = stanowiskaRepository.getById(pracownicy.getStanowisko().getNazwa());

        if (klientRepository.findByLogin(pracownicy.getLogin()).isPresent() || pracownicyRepository.findByLogin(pracownicy.getLogin()).isPresent() || Objects.equals(pracownicy.getLogin(), "admin")) {
            throw new DataIntegrityViolationException("Login exist!");
        }
        else if (!pracownicyRepository.czyPensjaZakresie(pracownicy.getPlaca_pod(), pracownicy.getStanowisko().getNazwa())){
            throw new IllegalStateException(String.format("Placa na stanowisku %s wynosi (min: %s, max: %s)",
                    pracownicy.getStanowisko().getNazwa(),
                    stanowiska.getPlaca_min(),
                    stanowiska.getPlaca_max()));
        }
//        else if (pracownicy.getPlaca_pod() < stanowiska.getPlaca_min() || pracownicy.getPlaca_pod() > stanowiska.getPlaca_max()){
//            throw new IllegalStateException(String.format("Placa na stanowisku %s wynosi (min: %s, max: %s)",
//                    pracownicy.getStanowisko().getNazwa(),
//                    stanowiska.getPlaca_min(),
//                    stanowiska.getPlaca_max()));
//        }

        pracownicyRepository.save(pracownicy);
    }

    public List<Pracownicy> getPracownicy() {
        return pracownicyRepository.findAll();
    }

    public Optional<Pracownicy> getPracownicyByLogin(String login) {
        return pracownicyRepository.findByLogin(login);
    }

    @Transactional
    public void updatePracownik(Long id_pracownika, Float placa_pod, Stanowiska stanowiska, Boolean czy_zatrudniony) {

        if (placa_pod < stanowiska.getPlaca_min() || placa_pod > stanowiska.getPlaca_max()){
            throw new IllegalStateException(String.format("Placa na stanowisku %s wynosi (min: %s, max: %s)",
                    stanowiska.getNazwa(),
                    stanowiska.getPlaca_min(),
                    stanowiska.getPlaca_max()));
        }

        Pracownicy pracownik = pracownicyRepository.findById(id_pracownika)
                .orElseThrow(() -> new IllegalStateException(
                        "pracownik with id " + id_pracownika + " does not exists"));

        if (placa_pod != null &&
                !Objects.equals(pracownik.getPlaca_pod(), placa_pod)) {
            pracownik.setPlaca_pod(placa_pod);
        }
        if (stanowiska != null &&
                !Objects.equals(pracownik.getStanowisko(), stanowiska)) {
            pracownik.setStanowisko(stanowiska);
        }
        if (czy_zatrudniony != null &&
                !Objects.equals(pracownik.getCzy_zatrudniony(), czy_zatrudniony)) {
            pracownik.setCzy_zatrudniony(czy_zatrudniony);
        }
    }
}
