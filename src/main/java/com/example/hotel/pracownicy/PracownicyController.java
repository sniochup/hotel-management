package com.example.hotel.pracownicy;


import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.rezerwacje.RezerwacjeService;
import com.example.hotel.stanowiska.StanowiskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping(path = "/pracownicy")
public class PracownicyController {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final PracownicyService pracownicyService;
    private final StanowiskaService stanowiskaService;

    @Autowired
    public PracownicyController(PracownicyService pracownicyService,
                                StanowiskaService stanowiskaService) {
        this.pracownicyService = pracownicyService;
        this.stanowiskaService = stanowiskaService;
    }

    @GetMapping(path = "/wyswietl")
    public String getPracownicy(Model model, Authentication authentication) {

        if (Objects.equals(authentication.getAuthorities().iterator().next().toString(), "PRACOWNIK")) {
            model.addAttribute("pAttributes", pracownicyService.getPracownicyByLogin(authentication.getName()).get());
            return "views/pracownik/mojeDane";
        }

        model.addAttribute("pAttributes", pracownicyService.getPracownicy());
        return "views/pracownicy_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("pracownicyAttributes", new Pracownicy());
        model.addAttribute("sAttributes", stanowiskaService.getStanowiska());
        model.addAttribute("formTitle", "Dodawanie nowego pracownika");
        return "views/pracownicy_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Pracownicy pracownicy) {
        try {
            pracownicy.setPassword(passwordEncoder.encode(pracownicy.getPassword()));
            System.out.println(pracownicy.getCzy_zatrudniony());
            pracownicyService.addNewPracownik(pracownicy);
        } catch (DataIntegrityViolationException e) {
            return "redirect:/pracownicy/dodaj?login";
        } catch (IllegalStateException e) {
            return "redirect:/pracownicy/dodaj?placa";
        }

        return "redirect:/pracownicy/wyswietl";
    }

}
