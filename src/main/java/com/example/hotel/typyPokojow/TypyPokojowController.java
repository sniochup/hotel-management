package com.example.hotel.typyPokojow;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.stanowiska.StanowiskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("typyPokojowAttributes", new TypyPokojow());
        model.addAttribute("formTitle", "Dodawanie nowego typu pokoju");
        return "views/typyPokojow_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute TypyPokojow typyPokojow) {
        typyPokojowService.addNewTypyPokojow(typyPokojow);
        return "redirect:/typyPokojow/wyswietl";
    }
}
