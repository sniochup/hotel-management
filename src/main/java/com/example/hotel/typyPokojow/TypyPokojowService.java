package com.example.hotel.typyPokojow;

import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.stanowiska.StanowiskaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypyPokojowService {

    private final TypyPokojowRepository typyPokojowRepository;

    @Autowired
    public TypyPokojowService(TypyPokojowRepository typyPokojowRepository) {
        this.typyPokojowRepository = typyPokojowRepository;
    }

    public void addNewTypyPokojow(TypyPokojow typyPokojow) {
        typyPokojowRepository.save(typyPokojow);
    }

    public List<TypyPokojow> getTypyPokojow() {
        return typyPokojowRepository.findAll();
    }
}
