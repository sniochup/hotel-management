package com.example.hotel.stanowiska;

import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rabaty.RabatyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StanowiskaService {

    private final StanowiskaRepository stanowiskaRepository;

    @Autowired
    public StanowiskaService(StanowiskaRepository stanowiskaRepository) {
        this.stanowiskaRepository = stanowiskaRepository;
    }

    public void addNewStanowiska(Stanowiska stanowiska) {
        stanowiskaRepository.save(stanowiska);
    }

    public List<Stanowiska> getStanowiska() {
        System.out.println(stanowiskaRepository.findAll().get(0));
        return stanowiskaRepository.findAll();
    }
}
