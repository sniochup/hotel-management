package com.example.hotel.uslugi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UslugiRepository extends JpaRepository<Uslugi, Long> {
}