package com.example.hotel.klienci;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KlientRepository extends JpaRepository<Klienci, Long> {

    Optional<Klienci> findByLoginAndPassword(String login, String password);
    Optional<Klienci> findByLogin(String login);
}
