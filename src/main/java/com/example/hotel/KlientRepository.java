package com.example.hotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {
}
