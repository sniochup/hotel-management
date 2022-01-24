package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.KlientService;
import com.example.hotel.miejscaParkingowe.MiejscaParkingowe;
import com.example.hotel.miejscaParkingowe.MiejscaParkingoweService;
import com.example.hotel.pakietyWyzywien.PakietyWyzywienService;
import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.pokoje.PokojeService;
import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.uslugi.Uslugi;
import com.example.hotel.uslugi.UslugiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
//            System.out.println(rezerwacjeService.getRezerwacjeID(authentication.getName()).get(0).getPakietyWyzywien());
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

        LocalDate start = (rezerwacje.getData_od()).toLocalDate();
        LocalDate end = (rezerwacje.getData_do()).toLocalDate();
        Long days = ChronoUnit.DAYS.between(start, end);
        System.out.println(days);

        float cena = 0;
        Integer liczba_osob = 0;

        for (Pokoje p : rezerwacje.getPokoje()) {
            cena += p.getCena() * days;
            liczba_osob += p.getLiczba_osob();
        }
//        System.out.println(liczba_osob);

        for (MiejscaParkingowe m : rezerwacje.getMiejsca_parkingowe()) {
            cena += m.getCena() * days;
        }

        if (rezerwacje.getPakietyWyzywien() != null) {
            cena += rezerwacje.getPakietyWyzywien().getCena_dzien() * liczba_osob * days;
        }

        for (Uslugi u : rezerwacje.getUslugi()) {
            cena += u.getCena() * liczba_osob;
        }

        Rabaty rabaty = rezerwacjeService.oblicz_rabat(authentication.getName());
        if (rabaty != null) {
            klientService.setRabat(authentication.getName(), rabaty);
//            System.out.println(1 - rabaty.getWysokosc_rabatu() * 0.01);
            cena = (float) (cena * (1 - rabaty.getWysokosc_rabatu() * 0.01));
        }

        rezerwacje.setPlatnosc(cena);

        rezerwacje.setKlient(klientService.getKlientByLogin(authentication.getName()).get());
        rezerwacje.setStatus("Przyjęta");
        rezerwacje.setStatus_platnosci("Oczekuje płatności");
        rezerwacjeService.addNewRezerwacja(rezerwacje);

        return "redirect:/rezerwacje/wyswietl";
    }
}
