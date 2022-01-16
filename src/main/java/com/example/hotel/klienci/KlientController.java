package com.example.hotel.klienci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(path = "/klienci")
public class KlientController {

    private final KlientService klientService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping(path = "/lista")
    public String getKlients(Model model, Principal principal) {
//        System.out.println(principal.getName());
//        System.out.println(principal.toString());
        model.addAttribute("clientsAttributes", klientService.getKlients());
        return "views/klienci";
    }

    @GetMapping(path = "/form")
    public String getForm(Model model) {
        model.addAttribute("clientsAttributes", new Klienci());
        model.addAttribute("formTitle", "Przykładowy formularz");
        return "views/form";
    }

    @PostMapping(path = "/form")
    public String addProgrammingLanguageSubmit(@ModelAttribute Klienci klienci) {
        klienci.setPassword(passwordEncoder.encode(klienci.getPassword()));
        klientService.addNewKlient(klienci);
        return "redirect:/klienci/lista";
    }

//    @PostMapping
//    public void registerNewKlient(@RequestBody Klienci klient) {
//        klientService.addNewKlient(klient);
//    }
//
//    @DeleteMapping(path = "{klientId}")
//    public void deleteKlient(@PathVariable("klientId") Long klientid) {
//        klientService.deleteKlient(klientid);
//    }
//
//    @PutMapping(path = "{klientId}")
//    public void updateKlient(
//            @PathVariable("klientId") Long klientId,
//            @RequestParam(required = false) String imie,
//            @RequestParam(required = false) String nazwisko) {
//        klientService.updateKlient(klientId, imie, nazwisko);
//    }
}
