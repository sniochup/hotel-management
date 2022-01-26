package com.example.hotel.miejscaParkingowe;


import com.example.hotel.pakietyWyzywien.PakietyWyzywien;
import com.example.hotel.pokoje.PokojeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/miejscaParkingowe")
public class MiejscaParkingoweController {

    private final MiejscaParkingoweService miejscaParkingoweService;

    @Autowired
    public MiejscaParkingoweController(MiejscaParkingoweService miejscaParkingoweService) {
        this.miejscaParkingoweService = miejscaParkingoweService;
    }

    @GetMapping(path = "/wyswietl")
    public String getMiejscaParkingowe(Model model) {
        model.addAttribute("mAttributes", miejscaParkingoweService.getMiejscaParkingowe());
        return "views/parking_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getForm(Model model) {
        model.addAttribute("miejscaParkingoweAttributes", new MiejscaParkingowe());
        model.addAttribute("formTitle", "Dodawanie nowego miejsca parkingowego");
        return "views/miejscaParkingowe_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute MiejscaParkingowe miejscaParkingowe) {
        miejscaParkingoweService.addNewMiejsceParkingowe(miejscaParkingowe);
        return "redirect:/miejscaParkingowe/wyswietl";
    }
}
