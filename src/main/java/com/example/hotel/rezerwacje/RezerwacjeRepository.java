package com.example.hotel.rezerwacje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RezerwacjeRepository extends JpaRepository<Rezerwacje, Long> {
}