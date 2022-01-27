package com.example.hotel.stanowiska;

import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rabaty.RabatyRepository;
import com.example.hotel.rezerwacje.Rezerwacje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StanowiskaService {

    private final StanowiskaRepository stanowiskaRepository;

    @Autowired
    public StanowiskaService(StanowiskaRepository stanowiskaRepository) {
        this.stanowiskaRepository = stanowiskaRepository;
    }

    public Optional<Stanowiska> getById(String nazwa) {
        return stanowiskaRepository.findById(nazwa);
    }

    public void deleteStanowiska(Stanowiska stanowiska) {
        stanowiskaRepository.delete(stanowiska);
    }

    public void addNewStanowiska(Stanowiska stanowiska) {
        stanowiskaRepository.save(stanowiska);
    }

    public List<Stanowiska> getStanowiska() {
        return stanowiskaRepository.findAll();
    }
}
