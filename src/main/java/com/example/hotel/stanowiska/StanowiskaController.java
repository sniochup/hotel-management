package com.example.hotel.stanowiska;

import com.example.hotel.rabaty.RabatyService;
import com.example.hotel.rezerwacje.Rezerwacje;
import com.example.hotel.uslugi.Uslugi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/stanowiska")
public class StanowiskaController {

    private final StanowiskaService stanowiskaService;

    @Autowired
    public StanowiskaController(StanowiskaService stanowiskaService) {
        this.stanowiskaService = stanowiskaService;
    }

    @GetMapping(path = "/wyswietl")
    public String getStanowiska(Model model) {
        model.addAttribute("sAttributes", stanowiskaService.getStanowiska());
        return "views/stanowiska_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("stanowiskaAttributes", new Stanowiska());
        model.addAttribute("formTitle", "Dodawanie nowego stanowiska");
        return "views/stanowiska_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Stanowiska stanowiska) {
        stanowiskaService.addNewStanowiska(stanowiska);
        return "redirect:/stanowiska/wyswietl";
    }

    @RequestMapping(value = "/usun/{nazwa}", method = RequestMethod.GET)
    public String deleteStanowiska(@PathVariable(name="nazwa") String nazwa, @ModelAttribute Stanowiska stanowiska) {
        stanowiskaService.deleteStanowiska(stanowiska);
        return "redirect:/stanowiska/wyswietl";
    }
}
