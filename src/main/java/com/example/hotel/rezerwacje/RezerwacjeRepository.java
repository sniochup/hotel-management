package com.example.hotel.rezerwacje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RezerwacjeRepository extends JpaRepository<Rezerwacje, Long> {

    @Query("SELECT r FROM Rezerwacje r JOIN Klienci k on k.id_klienta = r.klient.id_klienta WHERE k.login = ?1")
    List<Rezerwacje> findAllByKlient(String login);
}