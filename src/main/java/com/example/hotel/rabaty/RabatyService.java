package com.example.hotel.rabaty;

import com.example.hotel.pakietyWyzywien.PakietyWyzywien;
import com.example.hotel.pakietyWyzywien.PakietyWyzywienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//        System.out.println(rabatyRepository.findAll().get(0));
        return rabatyRepository.findAll();
    }
}
