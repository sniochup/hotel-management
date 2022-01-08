package com.example.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class KlientService {

    private final KlientRepository klientRepository;

    @Autowired
    public KlientService(KlientRepository klientRepository) {
        this.klientRepository = klientRepository;
    }
    public List<Klient> getKlient() {
        return klientRepository.findAll();
    }

    public void addNewKlient(Klient klient) {
        if (Period.between(klient.getRok_urodzenia(), LocalDate.now()).getYears() < 18) {
            throw new IllegalStateException("Klient too young!");
        }
        klientRepository.save(klient);
    }

    public void deleteKlient(Long klientid) {
        boolean exist = klientRepository.existsById(klientid);
        if (!exist) {
            throw new IllegalStateException(
                    "klient with id " + klientid + " does not exists");
        }
        klientRepository.deleteById(klientid);
    }

    @Transactional
    public void updateKlient(Long klientId, String imie, String nazwisko) {
        Klient klient = klientRepository.findById(klientId)
                .orElseThrow(() -> new IllegalStateException(
                    "klient with id " + klientId + "does not exists"));
        if (imie != null && imie.length() > 0 &&
                !Objects.equals(klient.getImie(), imie)) {
            klient.setImie(imie);
        }
        if (nazwisko != null && nazwisko.length() > 0 &&
                !Objects.equals(klient.getNazwisko(), nazwisko)) {
            klient.setNazwisko(nazwisko);
        }
    }
}
