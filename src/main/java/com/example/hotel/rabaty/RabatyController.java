package com.example.hotel.rabaty;

import com.example.hotel.pakietyWyzywien.PakietyWyzywienService;
import com.example.hotel.stanowiska.Stanowiska;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/rabaty")
public class RabatyController {

    private final RabatyService rabatyService;

    @Autowired
    public RabatyController(RabatyService rabatyService) {
        this.rabatyService = rabatyService;
    }

    @GetMapping(path = "/wyswietl")
    public String getRabaty(Model model) {
        model.addAttribute("rAttributes", rabatyService.getRabaty());
        return "views/rabaty_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("rabatyAttributes", new Rabaty());
        model.addAttribute("formTitle", "Dodawanie nowego rabatu");
        return "views/rabaty_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Rabaty rabaty) {
        rabatyService.addNewRabaty(rabaty);
        return "redirect:/rabaty/wyswietl";
    }
}
