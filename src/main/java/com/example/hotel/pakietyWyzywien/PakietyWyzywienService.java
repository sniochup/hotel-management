package com.example.hotel.pakietyWyzywien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PakietyWyzywienService {

    private final PakietyWyzywienRepository pakietyWyzywienRepository;

    @Autowired
    public PakietyWyzywienService(PakietyWyzywienRepository pakietyWyzywienRepository) {
        this.pakietyWyzywienRepository = pakietyWyzywienRepository;
    }

    public void addNewPakietyWyzywien(PakietyWyzywien pakietyWyzywien) {
        pakietyWyzywienRepository.save(pakietyWyzywien);
    }

    public List<PakietyWyzywien> getPakietyWyzywien() {
        return pakietyWyzywienRepository.findAll();
    }
}
