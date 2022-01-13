package com.example.hotel.pracownicy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracownicyRepository extends JpaRepository<Pracownicy, Long> {
}