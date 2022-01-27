package com.example.hotel.stanowiska;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.pracownicy.Pracownicy;
import com.example.hotel.pracownicy.PracownicyRepository;
import com.example.hotel.pracownicy.PracownicyService;
import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.rabaty.RabatyRepository;
import com.example.hotel.rezerwacje.Rezerwacje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StanowiskaService {

    private final StanowiskaRepository stanowiskaRepository;
    private final PracownicyService pracownicyService;

    @Autowired
    public StanowiskaService(StanowiskaRepository stanowiskaRepository,
                             PracownicyService pracownicyService) {
        this.stanowiskaRepository = stanowiskaRepository;
        this.pracownicyService = pracownicyService;
    }

    public Optional<Stanowiska> getById(String nazwa) {
        return stanowiskaRepository.findById(nazwa);
    }

    public void deleteStanowiska(Stanowiska stanowiska) {
        for (Pracownicy p : pracownicyService.getPracownicy()) {
            if (Objects.equals(p.getStanowisko().getNazwa(), stanowiska.getNazwa())) {
                throw new IllegalStateException("Ktoś pracuje na tym stanowisku");
            }
        }
        stanowiskaRepository.delete(stanowiska);
    }

    public void addNewStanowiska(Stanowiska stanowiska) {
        stanowiskaRepository.save(stanowiska);
    }

    public List<Stanowiska> getStanowiska() {
        return stanowiskaRepository.findAll();
    }
}
