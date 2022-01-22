package com.example.hotel.uslugi;

import com.example.hotel.stanowiska.Stanowiska;
import com.example.hotel.stanowiska.StanowiskaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UslugiService {

    private final UslugiRepository uslugiRepository;

    @Autowired
    public UslugiService(UslugiRepository uslugiRepository) {
        this.uslugiRepository = uslugiRepository;
    }

    public void addNewUslugi(Uslugi uslugi) {
        uslugiRepository.save(uslugi);
    }

    public List<Uslugi> getUslugi() {
        return uslugiRepository.findAll();
    }
}
