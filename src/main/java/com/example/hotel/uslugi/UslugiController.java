package com.example.hotel.uslugi;

import com.example.hotel.stanowiska.StanowiskaService;
import com.example.hotel.typyPokojow.TypyPokojow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/uslugi")
public class UslugiController {

    private final UslugiService uslugiService;

    @Autowired
    public UslugiController(UslugiService uslugiService) {
        this.uslugiService = uslugiService;
    }

    @GetMapping(path = "/wyswietl")
    public String getUslugi(Model model) {
        model.addAttribute("uAttributes", uslugiService.getUslugi());
        return "views/uslugi_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("uslugiAttributes", new Uslugi());
        model.addAttribute("formTitle", "Dodawanie nowej usługi");
        return "views/uslugi_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Uslugi uslugi) {
        uslugiService.addNewUslugi(uslugi);
        return "redirect:/uslugi/wyswietl";
    }
}
