package com.example.hotel.stanowiska;

import com.example.hotel.rabaty.RabatyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/stanowiska")
public class StanowiskaController {

    private final StanowiskaService stanowiskaService;

    @Autowired
    public StanowiskaController(StanowiskaService stanowiskaService) {
        this.stanowiskaService = stanowiskaService;
    }

    @GetMapping(path = "/wyswietl")
    public String getStanowiska(Model model) {
        model.addAttribute("sAttributes", stanowiskaService.getStanowiska());
        return "views/stanowiska_lista";
    }
}
