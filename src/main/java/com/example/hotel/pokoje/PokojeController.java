package com.example.hotel.pokoje;


import com.example.hotel.pracownicy.PracownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/pokoje")
public class PokojeController {

    private final PokojeService pokojeService;

    @Autowired
    public PokojeController(PokojeService pokojeService) {
        this.pokojeService = pokojeService;
    }

    @GetMapping(path = "/wyswietl")
    public String getPracownicy(Model model) {
        model.addAttribute("pAttributes", pokojeService.getPokoje());
        return "views/pokoje_lista";
    }
}
