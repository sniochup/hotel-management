package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class RezerwacjeService {

    private final RezerwacjeRepository rezerwacjeRepository;

    @Autowired
    public RezerwacjeService(RezerwacjeRepository rezerwacjeRepository) {
        this.rezerwacjeRepository = rezerwacjeRepository;
    }

    public void addNewRezerwacja(Rezerwacje rezerwacje) {
        rezerwacjeRepository.save(rezerwacje);
    }

    public List<Rezerwacje> getRezerwacje() {
        System.out.println(rezerwacjeRepository.findAll().get(0).getKlient().getLogin());
        return rezerwacjeRepository.findAll();
    }
}
