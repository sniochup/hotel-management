package com.example.hotel.klient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class KlientController {

    private final KlientService klientService;

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping(path = "/get")
    public String getKlient(Model model) {
        model.addAttribute("clientsAttributes", klientService.getKlient());
        return "views/index";
    }

    @GetMapping(path = "/form")
    public String getForm(Model model) {
        model.addAttribute("clientsAttributes", new Klient());
//        model.addAttribute("programmingLanguage", new ProgrammingLanguage());
        model.addAttribute("formTitle", "Przykładowy formularz");
        return "views/form";
    }

    @PostMapping
    public void registerNewKlient(@RequestBody Klient klient) {
        klientService.addNewKlient(klient);
    }

    @DeleteMapping(path = "{klientId}")
    public void deleteKlient(@PathVariable("klientId") Long klientid) {
        klientService.deleteKlient(klientid);
    }

    @PutMapping(path = "{klientId}")
    public void updateKlient(
            @PathVariable("klientId") Long klientId,
            @RequestParam(required = false) String imie,
            @RequestParam(required = false) String nazwisko) {
        klientService.updateKlient(klientId, imie, nazwisko);
    }
}
