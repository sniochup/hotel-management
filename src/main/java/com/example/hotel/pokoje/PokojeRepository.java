package com.example.hotel.pokoje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokojeRepository extends JpaRepository<Pokoje, Long> {
}