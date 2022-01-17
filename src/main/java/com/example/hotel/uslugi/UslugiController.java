package com.example.hotel.uslugi;

import com.example.hotel.stanowiska.StanowiskaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
