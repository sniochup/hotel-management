CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Klienci
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Julia', 'Tokłowicz', TO_DATE('2000-05-08', 'YYYY-MM-DD'), 'julia', crypt('julia', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Paweł', 'Śnioszek', TO_DATE('2000-06-25', 'YYYY-MM-DD'), 'pawel', crypt('pawel', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Anna', 'Nowak', TO_DATE('1998-02-06', 'YYYY-MM-DD'), 'anowak', crypt('anowak123', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Jan', 'Kowalski', TO_DATE('1933-01-01', 'YYYY-MM-DD'), 'kowal', crypt('janek26', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Jakub', 'Wiśniewski', TO_DATE('2002-05-02', 'YYYY-MM-DD'), 'wisnia', crypt('banan', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Kacper', 'Wojcik', TO_DATE('1986-05-29', 'YYYY-MM-DD'), 'kacperek', crypt('kac29', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Filip', 'Kowalczyk', TO_DATE('1961-12-24', 'YYYY-MM-DD'), 'fifi', crypt('taco', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Paulina', 'Kaminska', TO_DATE('1995-04-13', 'YYYY-MM-DD'), 'paula', crypt('kotek568', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Katarzyna', 'Lewandowska', TO_DATE('1989-02-19', 'YYYY-MM-DD'), 'kasia20', crypt('lewa09', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Aleksandra', 'Zielińska', TO_DATE('1982-11-27', 'YYYY-MM-DD'), 'ala5', crypt('alka5978', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Monika', 'Szymanska', TO_DATE('2001-06-02', 'YYYY-MM-DD'), 'monia555', crypt('szymonika5', gen_salt('bf')));
INSERT INTO klienci(id_klienta, imie, nazwisko, rok_urodzenia, login, password) VALUES (nextval('klient_sequence'), 'Mateusz', 'Wojciechowski', TO_DATE('1998-07-14', 'YYYY-MM-DD'), 'mati2137', crypt('wociachMM59', gen_salt('bf')));

-- Miejsca parkingowe
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 100.50, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 99.99, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 80, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 69.99, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 49.99, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 50, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 50, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 60, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 30, 'niepelnosprawni');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 123.23, 'normalne');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 129.99, 'normalne');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 109.99, 'normalne');
INSERT INTO Miejsca_parkingowe(id_miejsca, cena, typ) VALUES (nextval('parking_sequence'), 101.90, 'normalne');

-- Pakiet wyzywienia
INSERT INTO Pakiety_wyzywien(id_pakietu, typ, cena_dzien) VALUES (nextval('pakiet_wyzywienia_sequence'), 'sniadanie', 39.99);
INSERT INTO Pakiety_wyzywien(id_pakietu, typ, cena_dzien) VALUES (nextval('pakiet_wyzywienia_sequence'), 'sniadanie + obiadokolacja', 99.99);

-- Typ pokoju
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'economic', '1', '1', '0');
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'economic', '1', '0', '0');
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'superior', '1', '1', '1');
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'superior', '1', '1', '0');
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'deluxe', '1', '0', '1');
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'deluxe', '1', '1', '0');
INSERT INTO Typy_pokojow(id_typu, standard, czy_klima, czy_balkon, czy_kuchnia) VALUES (nextval('typy_pokojow_sequence'), 'deluxe', '1', '1', '1');

-- Pokoje
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 99.99, 1, 20, 1, '1');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 100, 1, 20, 2, '1');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 150, 2, 22, 3, '1');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 149.99, 2, 22, 4, '0');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 200, 3, 30, 5, '1');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 199.99, 2, 30, 6, '1');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 249.49, 3, 40, 7, '1');
INSERT INTO Pokoje(id_pokoju, cena, liczba_osob, metraz, id_typu, czy_dostepny) VALUES (nextval('pokoje_sequence'), 279.99, 3, 40, 7, '1');

-- Stanowiska
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('sprzatacz', 1500, 2000);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('recepcjonista', 2000, 3000);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('masazysta', 2000, 3500);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('jogin', 3500, 4100);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('trener osobisty', 2850, 4500);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('instruktor surfingu', 1890, 3100);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('kucharz', 5000, 6000);
INSERT INTO Stanowiska(nazwa, placa_min, placa_max) VALUES ('menager', 9000, 9999.99);

-- Pracownicy
INSERT INTO Pracownicy (id_pracownika, imie, nazwisko, data_zatrudnienia, placa_pod, nazwa, czy_zatrudniony, login, password) VALUES (nextval('pracownicy_sequence'), 'Prac', 'Prac', TO_DATE('2022-01-23', 'YYYY-MM-DD'), 2000, 'masazysta', '1', 'prac', crypt('prac', gen_salt('bf')));
INSERT INTO Pracownicy (id_pracownika, imie, nazwisko, data_zatrudnienia, placa_pod, nazwa, czy_zatrudniony, login, password) VALUES (nextval('pracownicy_sequence'), 'Anna', 'Paruszewska', TO_DATE('2021-01-01', 'YYYY-MM-DD'), 1500, 'sprzatacz', '1', 'anna', crypt('anna', gen_salt('bf')));
INSERT INTO Pracownicy (id_pracownika, imie, nazwisko, data_zatrudnienia, placa_pod, nazwa, czy_zatrudniony, login, password) VALUES (nextval('pracownicy_sequence'), 'Magdalena', 'Slowiakowska', TO_DATE('2019-01-01', 'YYYY-MM-DD'), 2200, 'recepcjonista', '0', 'Magdalena', crypt('Magdalena', gen_salt('bf')));
INSERT INTO Pracownicy (id_pracownika, imie, nazwisko, data_zatrudnienia, placa_pod, nazwa, czy_zatrudniony, login, password) VALUES (nextval('pracownicy_sequence'), 'Jan', 'Konieczny', TO_DATE('2020-08-09', 'YYYY-MM-DD'), 9500, 'menager', '1', 'janek', crypt('janek', gen_salt('bf')));

-- Rabaty
INSERT INTO Rabaty(id_rabatu, wysokosc_rabatu, typ) VALUES (nextval('rabaty_sequence'), 5, 'Bronze');
INSERT INTO Rabaty(id_rabatu, wysokosc_rabatu, typ) VALUES (nextval('rabaty_sequence'), 10, 'Silver');
INSERT INTO Rabaty(id_rabatu, wysokosc_rabatu, typ) VALUES (nextval('rabaty_sequence'), 15, 'Gold');

-- Usługi
INSERT INTO Uslugi (id_uslugi, nazwa, cena) VALUES (nextval('uslugi_sequence'), 'masaz', 199.99);
INSERT INTO Uslugi (id_uslugi, nazwa, cena) VALUES (nextval('uslugi_sequence'), 'trener osobisty', 150);

-- Rezerwacja
INSERT INTO Rezerwacje(id_rezerwacji, data_od, data_do, status, status_platnosci, id_klienta, id_pakietu, platnosc) VALUES (nextval('rezerwacje_sequence'), TO_DATE('12-12-2022', 'DD-MM-YYYY'), TO_DATE('20-12-2022', 'DD-MM-YYYY'), 'Przyjeta', 'Oczekuje płatności', 1, 1, 3528.75);
INSERT INTO Rezerwacje(id_rezerwacji, data_od, data_do, status, status_platnosci, id_klienta, id_pakietu, platnosc) VALUES (nextval('rezerwacje_sequence'), TO_DATE('01-01-2021', 'DD-MM-YYYY'), TO_DATE('05-01-2021', 'DD-MM-YYYY'), 'Odwolana', 'Oczekuje płatności', 2, 1, 1689.9);
INSERT INTO Rezerwacje(id_rezerwacji, data_od, data_do, status, status_platnosci, id_klienta, id_pakietu, platnosc) VALUES (nextval('rezerwacje_sequence'), TO_DATE('01-03-2022', 'DD-MM-YYYY'), TO_DATE('05-03-2022', 'DD-MM-YYYY'), 'Przyjeta', 'Oplacona', 1, 2, 2468.75);
INSERT INTO Rezerwacje(id_rezerwacji, data_od, data_do, status, status_platnosci, id_klienta, id_pakietu, platnosc) VALUES (nextval('rezerwacje_sequence'), TO_DATE('10-03-2022', 'DD-MM-YYYY'), TO_DATE('15-03-2022', 'DD-MM-YYYY'), 'Zamknieta', 'Oplacona', 1, 2, 1489.6);

-- Zarezerwowane pokoje
INSERT INTO Zarezerwowane_pokoje (id_pokoju, id_rezerwacji) VALUES (1, 1);
INSERT INTO Zarezerwowane_pokoje (id_pokoju, id_rezerwacji) VALUES (4, 2);
INSERT INTO Zarezerwowane_pokoje (id_pokoju, id_rezerwacji) VALUES (5, 3);
INSERT INTO Zarezerwowane_pokoje (id_pokoju, id_rezerwacji) VALUES (2, 4);

-- Zarezerwowane miejsca parkingowe
INSERT INTO Zarezerw_miejsca_parkingowe(id_miejsca, id_rezerwacji) VALUES (1, 1);
INSERT INTO Zarezerw_miejsca_parkingowe(id_miejsca, id_rezerwacji) VALUES (4, 2);

-- Zamowione usługi
INSERT Into Zamowione_uslugi(id_rezerwacji, id_uslugi) VALUES (1, 1);
INSERT Into Zamowione_uslugi(id_rezerwacji, id_uslugi) VALUES (2, 1);

-- Wykonywane uslugi
INSERT Into wykonywane_uslugi(id_pracownika, id_uslugi) VALUES (1, 1);



