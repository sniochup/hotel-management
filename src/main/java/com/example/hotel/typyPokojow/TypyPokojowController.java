package com.example.hotel.typyPokojow;

import com.example.hotel.stanowiska.StanowiskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/typyPokojow")
public class TypyPokojowController {

    private final TypyPokojowService typyPokojowService;

    @Autowired
    public TypyPokojowController(TypyPokojowService typyPokojowService) {
        this.typyPokojowService = typyPokojowService;
    }

    @GetMapping(path = "/wyswietl")
    public String getTypyPokojow(Model model) {
        model.addAttribute("tAttributes", typyPokojowService.getTypyPokojow());
        return "views/typyPokojow_lista";
    }
}
