package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping(path = "/rezerwacje")
public class RezerwacjeController {

    private final RezerwacjeService rezerwacjeService;

    @Autowired
    public RezerwacjeController(RezerwacjeService rezerwacjeService) {
        this.rezerwacjeService = rezerwacjeService;
    }

    @GetMapping(path = "/wyswietl")
    public String getRezerwacje(Model model, Authentication authentication) {

        if (Objects.equals(authentication.getAuthorities().iterator().next().toString(), "KLIENT")) {
//            System.out.println(rezerwacjeService.getRezerwacjeID(authentication.getName()).get(0).getUslugi()
            model.addAttribute("rAttributes", rezerwacjeService.getRezerwacjeID(authentication.getName()));
            return "views/klient/rezerwacje";
        }

        model.addAttribute("rAttributes", rezerwacjeService.getRezerwacje());
        return "views/rezerwacje_lista";
    }
}
