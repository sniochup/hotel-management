package com.example.hotel.typyPokojow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypyPokojowRepository extends JpaRepository<TypyPokojow, Long> {
}