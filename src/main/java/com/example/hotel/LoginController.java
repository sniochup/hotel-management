package com.example.hotel;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class LoginController {

    private final KlientService klientService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public LoginController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping(path = "/login")
    public String getFormLogin(Model model) {
        model.addAttribute("formTitle", "Logowanie");
        return "views/login";
    }

    @GetMapping(path = "/")
    public String getHome(Model model, Principal principal) {
        var authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities.iterator().next());
        return "views/index";
    }

    @GetMapping(path = "/rejestracja")
    public String getRejestracja(Model model) {
        model.addAttribute("clientsAttributes", new Klienci());
        model.addAttribute("formTitle", "Rejestracja");
        return "views/rejestracja";
    }

    @PostMapping(path = "/rejestracja")
    public String addProgrammingLanguageSubmit(@ModelAttribute Klienci klienci) {
        try {
            klienci.setPassword(passwordEncoder.encode(klienci.getPassword()));
            klientService.addNewKlient(klienci);
        } catch (IllegalStateException e){
            return "redirect:/rejestracja?wiek";
        } catch (DataIntegrityViolationException e){
            return "redirect:/rejestracja?login";
        }

        return "redirect:/login?rejestracja";
    }
}

