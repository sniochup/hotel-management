package com.example.hotel.klienci;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class KlientConfig {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Bean
//    CommandLineRunner commandLineRunner(KlientRepository klientRepository) {
//        return args -> {
//            Klienci pawel = new Klienci(
//                    "Paweł",
//                    "Śnioszek",
//                    LocalDate.of(2000, Month.JULY, 6),
//                    "pawel",
//                    passwordEncoder.encode("pawel"));
//
//            Klienci julia = new Klienci(
//                    "Julia",
//                    "Tokłowicz",
//                    LocalDate.of(1933, Month.JANUARY, 8),
//                    "julia",
//                    passwordEncoder.encode("julia"));
//
//            System.out.println("Adding pawel and julia");
//            klientRepository.saveAll(List.of(pawel, julia));
//
//            System.out.print("Number of klients: ");
//            System.out.println(klientRepository.count());
//
//            klientRepository
//                    .findById(2L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> System.out.println("Student with ID 2 not found"));
//
//            klientRepository
//                    .findById(3L)
//                    .ifPresentOrElse(
//                            System.out::println,
//                            () -> System.out.println("Student with ID 3 not found"));
//
//            System.out.println("Select all klients");
//            List<Klient> klients = klientRepository.findAll();
//            klients.forEach(System.out::println);
//
//            System.out.println("Delete pawel");
//            klientRepository.deleteById(1L);
//
//            System.out.print("Number of klients: ");
//            System.out.println(klientRepository.count());
//        };
//    }
}
