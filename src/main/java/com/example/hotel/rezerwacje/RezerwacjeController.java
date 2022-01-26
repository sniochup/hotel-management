package com.example.hotel.rezerwacje;

import com.example.hotel.klienci.KlientService;
import com.example.hotel.miejscaParkingowe.MiejscaParkingowe;
import com.example.hotel.miejscaParkingowe.MiejscaParkingoweService;
import com.example.hotel.pakietyWyzywien.PakietyWyzywienService;
import com.example.hotel.pokoje.Pokoje;
import com.example.hotel.pokoje.PokojeService;
import com.example.hotel.pracownicy.Pracownicy;
import com.example.hotel.rabaty.Rabaty;
import com.example.hotel.uslugi.Uslugi;
import com.example.hotel.uslugi.UslugiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("featureDate", LocalDate.now().plusYears(3));
        model.addAttribute("formTitle", "Nowa Rezerwacja");

        return "views/rezerwacje_addData";
    }

    @PostMapping(path = "/dodaj-rezerwacja")
    public String getRejestracjaCala(@ModelAttribute Rezerwacje rezerwacje, Model model) {

        LocalDate start = (rezerwacje.getData_od()).toLocalDate();
        LocalDate end = (rezerwacje.getData_do()).toLocalDate();

        if (ChronoUnit.DAYS.between(start, end) < 1) {
            return "redirect:/rezerwacje/dodaj?data";
        }

        ArrayList<Pokoje> z_pokoje = new ArrayList<Pokoje>();
        ArrayList<MiejscaParkingowe> z_parking = new ArrayList<MiejscaParkingowe>();

        for (Rezerwacje r : rezerwacjeService.getRezerwacje()) {
            LocalDate rs = (r.getData_od()).toLocalDate().plusDays(1);
            LocalDate re = (r.getData_do()).toLocalDate().minusDays(1);
            if (!re.isBefore(start) && !rs.isAfter(end)) {
                z_pokoje.addAll(r.getPokoje());
                z_parking.addAll(r.getMiejsca_parkingowe());
            }
        }

        List<Pokoje> pokoje_dost = pokojeService.getPokoje();
        for (Pokoje p : z_pokoje) {
            pokoje_dost.removeIf(d -> Objects.equals(p.getId_pokoju(), d.getId_pokoju()));
        }

        if (pokoje_dost.isEmpty()) {
            return "redirect:/rezerwacje/dodaj?brak";
        }

        List<MiejscaParkingowe> parking_dost = miejscaParkingoweService.getMiejscaParkingowe();
        for (MiejscaParkingowe p : z_parking) {
            parking_dost.removeIf(d -> Objects.equals(p.getId_miejsca(), d.getId_miejsca()));
        }

        model.addAttribute("pAttributes", pokoje_dost);
        model.addAttribute("mAttributes", parking_dost);

        model.addAttribute("rAttributes", new Rezerwacje());
        model.addAttribute("wAttributes", pakietyWyzywienService.getPakietyWyzywien());
        model.addAttribute("uAttributes", uslugiService.getUslugi());
        model.addAttribute("poczatek", start);
        model.addAttribute("koniec", end);

        model.addAttribute("formTitle", "Nowa Rezerwacja");
        return "views/rezerwacje_add";
    }

    @PostMapping(path = "/dodaj")
    public String addProgrammingLanguageSubmit(@ModelAttribute Rezerwacje rezerwacje, Authentication authentication) {
        System.out.println(rezerwacje);

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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getUpdateForm(@PathVariable(name="id") Long id, Model model) {
        model.addAttribute("rAttributes", rezerwacjeService.getById(id).get());
        model.addAttribute("pAttributes", pokojeService.getPokoje());
        model.addAttribute("mAttributes", miejscaParkingoweService.getMiejscaParkingowe());
        model.addAttribute("wAttributes", pakietyWyzywienService.getPakietyWyzywien());
        model.addAttribute("uAttributes", uslugiService.getUslugi());
        model.addAttribute("formTitle", "Edytowanie rezerwacji");
        return "views/rezerwacje_edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateRezerwacja(@PathVariable(name="id") Long id, @ModelAttribute Rezerwacje rezerwacja) {
        rezerwacjeService.updateRezerwacja(id, rezerwacja.getStatus(), rezerwacja.getStatus_platnosci());
        return "redirect:/rezerwacje/wyswietl";
    }
}
