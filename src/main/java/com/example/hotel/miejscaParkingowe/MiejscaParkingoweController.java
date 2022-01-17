package com.example.hotel.miejscaParkingowe;


import com.example.hotel.pokoje.PokojeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
