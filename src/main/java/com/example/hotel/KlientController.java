package com.example.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;

@RestController
@RequestMapping(path = "/hotel")
public class KlientController {

    private final KlientService klientService;

    @Autowired
    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping
    public List<Klient> getKlient() {
        return klientService.getKlient();
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
