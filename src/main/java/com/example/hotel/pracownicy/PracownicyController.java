package com.example.hotel.pracownicy;


import com.example.hotel.stanowiska.StanowiskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping(path = "/pracownicy")
public class PracownicyController {

    private final PracownicyService pracownicyService;
    private final StanowiskaService stanowiskaService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
//            System.out.println(pracownicy.getCzy_zatrudniony());
            pracownicyService.addNewPracownik(pracownicy);
        } catch (DataIntegrityViolationException e) {
            return "redirect:/pracownicy/dodaj?login";
        } catch (IllegalStateException e) {
            return "redirect:/pracownicy/dodaj?placa";
        }

        return "redirect:/pracownicy/wyswietl";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getUpdateForm(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("pracownicyAttributes", pracownicyService.getById(id).get());
        model.addAttribute("sAttributes", stanowiskaService.getStanowiska());
        model.addAttribute("formTitle", "Edytowanie pracownika");
        return "views/pracownicy_edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateUser(@PathVariable(name = "id") Long id, @ModelAttribute Pracownicy pracownik) {
        try {
            pracownicyService.updatePracownik(id, pracownik.getPlaca_pod(), pracownik.getStanowisko(),
                    pracownik.getCzy_zatrudniony());
        } catch (IllegalStateException e) {
            return "redirect:/pracownicy/edit/" + id + "?placa";
        }
        return "redirect:/pracownicy/wyswietl";
    }

}
