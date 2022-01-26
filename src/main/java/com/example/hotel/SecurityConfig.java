package com.example.hotel;

import com.example.hotel.klienci.KlientService;
import com.example.hotel.pracownicy.PracownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final KlientService klientService;
    private final PracownicyService pracownicyService;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public SecurityConfig(KlientService klientService, PracownicyService pracownicyService) {
        this.klientService = klientService;
        this.pracownicyService = pracownicyService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider())
                .authenticationProvider(daoAuthenticationProviderPrac())
                .inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("WLASCICIEL");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(klientService);
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProviderPrac() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(pracownicyService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/rezerwacje/dodaj").hasAuthority("KLIENT")
                .antMatchers("/rezerwacje/edit/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/pracownicy/edit/**").hasAuthority("WLASCICIEL")
                .antMatchers("/pracownicy/dodaj").hasAuthority("WLASCICIEL")
                .antMatchers("/stanowiska/dodaj").hasAuthority("WLASCICIEL")
                .antMatchers("/pokoje/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/miejscaParkingowe/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/pakietyWyzywien/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/rabaty/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/stanowiska/wyswietl").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/pracownicy/wyswietl").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/uslugi/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/typyPokojow/**").hasAnyAuthority("WLASCICIEL", "PRACOWNIK")
                .antMatchers("/rejestracja").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/webjars/**", "/img/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}