package com.example.hotel.pakietyWyzywien;

import com.example.hotel.pakietyWyzywien.PakietyWyzywienService;
import com.example.hotel.rabaty.Rabaty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/pakietyWyzywien")
public class PakietyWyzywienController {

    private final PakietyWyzywienService pakietyWyzywienService;

    @Autowired
    public PakietyWyzywienController(PakietyWyzywienService pakietyWyzywienService) {
        this.pakietyWyzywienService = pakietyWyzywienService;
    }

    @GetMapping(path = "/wyswietl")
    public String getPakietyWyzywien(Model model) {
        model.addAttribute("pAttributes", pakietyWyzywienService.getPakietyWyzywien());
        return "views/wyzywienie_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("pakietyWyzywienAttributes", new PakietyWyzywien());
        model.addAttribute("formTitle", "Dodawanie nowego pakietu wyżywienia");
        return "views/pakietyWyzywien_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute PakietyWyzywien pakietyWyzywien) {
        pakietyWyzywienService.addNewPakietyWyzywien(pakietyWyzywien);
        return "redirect:/pakietyWyzywien/wyswietl";
    }
}
