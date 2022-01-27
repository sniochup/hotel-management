package com.example.hotel.pracownicy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PracownicyRepository extends JpaRepository<Pracownicy, Long> {

    @Query(nativeQuery = true, value = "SELECT czy_pensja_w_zakresie(?1, ?2)")
    Boolean czyPensjaZakresie(Float pensja, String stanowisko);

    Optional<Pracownicy> findByLoginAndPassword(String login, String password);
    Optional<Pracownicy> findByLogin(String login);
}