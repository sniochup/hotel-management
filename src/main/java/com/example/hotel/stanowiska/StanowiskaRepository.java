package com.example.hotel.stanowiska;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StanowiskaRepository extends JpaRepository<Stanowiska, String> {
}