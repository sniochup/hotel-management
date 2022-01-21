package com.example.hotel.miejscaParkingowe;


import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.pokoje.PokojeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiejscaParkingoweService {

    private final MiejscaParkingoweRepository miejscaParkingoweRepository;

    @Autowired
    public MiejscaParkingoweService(MiejscaParkingoweRepository miejscaParkingoweRepository) {
        this.miejscaParkingoweRepository = miejscaParkingoweRepository;
    }

    public void addNewMiejsceParkingowe(MiejscaParkingowe miejscaParkingowe) {
        miejscaParkingoweRepository.save(miejscaParkingowe);
    }

    public List<MiejscaParkingowe> getMiejscaParkingowe() {
        return miejscaParkingoweRepository.findAll();
    }
}
