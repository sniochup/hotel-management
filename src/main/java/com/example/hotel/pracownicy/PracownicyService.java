package com.example.hotel.pracownicy;

import com.example.hotel.klienci.Klienci;
import com.example.hotel.klienci.KlientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PracownicyService implements UserDetailsService {

    private final PracownicyRepository pracownicyRepository;

    @Autowired
    public PracownicyService(PracownicyRepository pracownicyRepository) {
        this.pracownicyRepository = pracownicyRepository;
    }


    public Pracownicy authenticate(String login, String password) {
        return pracownicyRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        return (UserDetails) pracownicyRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with login " + login + " not found"));
    }
}
