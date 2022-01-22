package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientService;
import com.example.hotel.miejscaParkingowe.MiejscaParkingowe;
import com.example.hotel.miejscaParkingowe.MiejscaParkingoweService;
import com.example.hotel.pakietyWyzywien.PakietyWyzywienService;
import com.example.hotel.pokoje.PokojeService;
import com.example.hotel.uslugi.UslugiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping(path = "/rezerwacje")
public class RezerwacjeController {

    private final RezerwacjeService rezerwacjeService;
    private final KlientService klientService;
    private final PokojeService pokojeService;
    private final MiejscaParkingoweService miejscaParkingoweService;
    private final PakietyWyzywienService pakietyWyzywienService;
    private final UslugiService uslugiService;

    @Autowired
    public RezerwacjeController(RezerwacjeService rezerwacjeService,
                                KlientService klientService,
                                PokojeService pokojeService,
                                MiejscaParkingoweService miejscaParkingoweService,
                                PakietyWyzywienService pakietyWyzywienService,
                                UslugiService uslugiService) {
        this.rezerwacjeService = rezerwacjeService;
        this.klientService = klientService;
        this.pokojeService = pokojeService;
        this.miejscaParkingoweService = miejscaParkingoweService;
        this.pakietyWyzywienService = pakietyWyzywienService;
        this.uslugiService = uslugiService;
    }

    @GetMapping(path = "/wyswietl")
    public String getRezerwacje(Model model, Authentication authentication) {

        if (Objects.equals(authentication.getAuthorities().iterator().next().toString(), "KLIENT")) {
//            System.out.println(rezerwacjeService.getRezerwacjeID(authentication.getName()).get(0).getUslugi()
            model.addAttribute("rAttributes", rezerwacjeService.getRezerwacjeID(authentication.getName()));
            return "views/klient/rezerwacje";
        }

        model.addAttribute("rAttributes", rezerwacjeService.getRezerwacje());
        return "views/rezerwacje_lista";
    }

    @GetMapping(path = "/dodaj")
    public String getRejestracja(Model model, Authentication authentication) {

        model.addAttribute("rAttributes", new Rezerwacje());
        model.addAttribute("pAttributes", pokojeService.getPokoje());
        model.addAttribute("mAttributes", miejscaParkingoweService.getMiejscaParkingowe());
        model.addAttribute("wAttributes", pakietyWyzywienService.getPakietyWyzywien());
        model.addAttribute("uAttributes", uslugiService.getUslugi());
        model.addAttribute("formTitle", "Nowa Rezerwacja");
        return "views/rezerwacje_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Rezerwacje rezerwacje, Authentication authentication) {
        rezerwacje.setKlient(klientService.getKlientByLogin(authentication.getName()).get());
        rezerwacje.setStatus("Przyjęta");
        rezerwacje.setStatus_platnosci("Oczekuje płatności");
        rezerwacjeService.addNewRezerwacja(rezerwacje);
        return "redirect:/rezerwacje/wyswietl";
    }
}
