package com.example.hotel.rabaty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RabatyRepository extends JpaRepository<Rabaty, Long> {
}