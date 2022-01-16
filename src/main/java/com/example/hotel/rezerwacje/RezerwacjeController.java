package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = "/rezerwacje")
public class RezerwacjeController {

    private final RezerwacjeService rezerwacjeService;

    @Autowired
    public RezerwacjeController(RezerwacjeService rezerwacjeService) {
        this.rezerwacjeService = rezerwacjeService;
    }

    @GetMapping(path = "/wyswietl")
    public String getRezerwacje(Model model) {
        model.addAttribute("rAttributes", rezerwacjeService.getRezerwacje());
        return "views/rezerwacje_lista";
    }
}
