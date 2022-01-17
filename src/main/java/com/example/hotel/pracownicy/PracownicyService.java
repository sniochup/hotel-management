package com.example.hotel.pracownicy;


import com.example.hotel.rezerwacje.Rezerwacje;
import com.example.hotel.rezerwacje.RezerwacjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracownicyService {

    private final PracownicyRepository pracownicyRepository;

    @Autowired
    public PracownicyService(PracownicyRepository pracownicyRepository) {
        this.pracownicyRepository = pracownicyRepository;
    }

    public void addNewPracownik(Pracownicy pracownicy) {
        pracownicyRepository.save(pracownicy);
    }

    public List<Pracownicy> getPracownicy() {
        System.out.println(pracownicyRepository.findAll().get(0).getStanowisko().getNazwa());
        return pracownicyRepository.findAll();
    }

}
