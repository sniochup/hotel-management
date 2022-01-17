package com.example.hotel.pracownicy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PracownicyRepository extends JpaRepository<Pracownicy, Long> {

    Optional<Pracownicy> findByLoginAndPassword(String login, String password);
    Optional<Pracownicy> findByLogin(String login);
}