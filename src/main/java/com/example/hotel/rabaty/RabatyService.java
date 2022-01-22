package com.example.hotel.rabaty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RabatyService {

    private final RabatyRepository rabatyRepository;

    @Autowired
    public RabatyService(RabatyRepository rabatyRepository) {
        this.rabatyRepository = rabatyRepository;
    }

    public void addNewRabaty(Rabaty rabaty) {
        rabatyRepository.save(rabaty);
    }

    public List<Rabaty> getRabaty() {
        return rabatyRepository.findAll();
    }

    public Optional<Rabaty> getRabatyId(Long id) {
        return rabatyRepository.findById(id);
    }
}
