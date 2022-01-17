package com.example.hotel.pracownicy;


import com.example.hotel.rezerwacje.RezerwacjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
