package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientRepository;
import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.pracownicy.Pracownicy;
import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rabaty.RabatyService;
import com.example.hotel.stanowiska.Stanowiska;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RezerwacjeService {

    private final RezerwacjeRepository rezerwacjeRepository;
    private final RabatyService rabatyService;

    @Autowired
    public RezerwacjeService(RezerwacjeRepository rezerwacjeRepository,
                             RabatyService rabatyService) {
        this.rezerwacjeRepository = rezerwacjeRepository;
        this.rabatyService = rabatyService;
    }

    public Optional<Rezerwacje> getById(Long id) {
        return rezerwacjeRepository.findById(id);
    }

    public void addNewRezerwacja(Rezerwacje rezerwacje) {
        rezerwacjeRepository.save(rezerwacje);
    }

    public List<Rezerwacje> getRezerwacje() {
        return rezerwacjeRepository.findAll();
    }

    public List<Rezerwacje> getRezerwacjeID(String login) {
        return rezerwacjeRepository.findAllByKlient(login);
    }

    public Rabaty oblicz_rabat(String login) {
        int count = getRezerwacjeID(login).size();
//        System.out.println(count);
        if (count < 5) {
            return null;
        }
        else if (count < 10) {
            return rabatyService.getRabatyId(1L).get();
        }
        else if (count < 15) {
            return rabatyService.getRabatyId(1L).get();
        }
        else {
            return rabatyService.getRabatyId(1L).get();
        }
    }

    @Transactional
    public void updateRezerwacja(Long id, String status_rezerwacji, String status_platnosci) {
        Rezerwacje rezerwacja = rezerwacjeRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "rezerwacja with id " + id + " does not exists"));

        if (status_rezerwacji != null &&
                !Objects.equals(rezerwacja.getStatus(), status_rezerwacji)) {
            rezerwacja.setStatus(status_rezerwacji);
        }
        if (status_platnosci != null &&
                !Objects.equals(rezerwacja.getStatus_platnosci(), status_platnosci)) {
            rezerwacja.setStatus_platnosci(status_platnosci);
        }

    }
}
