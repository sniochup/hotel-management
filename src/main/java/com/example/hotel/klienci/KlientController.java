package com.example.hotel.klienci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(path = "/klienci")
public class KlientController {

    private final KlientService klientService;

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping(path = "/wyswietl")
    public String getKlients(Model model) {
        model.addAttribute("clientsAttributes", klientService.getKlients());
        return "views/klienci_wyswietl";
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
