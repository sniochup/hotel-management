package com.example.hotel.pokoje;


import com.example.hotel.pracownicy.Pracownicy;
import com.example.hotel.pracownicy.PracownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokojeService {

    private final PokojeRepository pokojeRepository;

    @Autowired
    public PokojeService(PokojeRepository pokojeRepository) {
        this.pokojeRepository = pokojeRepository;
    }

    public void addNewPokoj(Pokoje pokoje) {
        pokojeRepository.save(pokoje);
    }

    public List<Pokoje> getPokoje() {
        System.out.println(pokojeRepository.findAll().get(0).getTyp_pokoju().getStandard());
        return pokojeRepository.findAll();
    }
}
