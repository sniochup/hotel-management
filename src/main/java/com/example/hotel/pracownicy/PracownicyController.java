package com.example.hotel.pracownicy;


import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.rezerwacje.RezerwacjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/pracownicy")
public class PracownicyController {

    private final PracownicyService pracownicyService;

    @Autowired
    public PracownicyController(PracownicyService pracownicyService) {
        this.pracownicyService = pracownicyService;
    }

    @GetMapping(path = "/wyswietl")
    public String getPracownicy(Model model) {
        model.addAttribute("pAttributes", pracownicyService.getPracownicy());
        return "views/pracownicy_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("pracownicyAttributes", new Pracownicy());
        model.addAttribute("formTitle", "Dodawanie nowego pracownika");
        return "views/pracownicy_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Pracownicy pracownicy) {
        pracownicyService.addNewPracownik(pracownicy);
        return "redirect:/pracownicy/wyswietl";
    }

}
