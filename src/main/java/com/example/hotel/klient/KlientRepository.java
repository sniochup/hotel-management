package com.example.hotel.klient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {

    Optional<Klient> findByLoginAndPassword(String login, String password);
    Optional<Klient> findByLogin(String login);
}
