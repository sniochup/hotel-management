package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientRepository;
import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rabaty.RabatyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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
}
