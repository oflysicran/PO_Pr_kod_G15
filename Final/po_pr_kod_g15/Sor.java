package po_pr_kod_g15;

import java.util.*;
import java.io.IOException;

public class Sor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pobranie danych wejściowych od użytkownika
        System.out.print("Podaj początkowy budżet: ");
        double initialBudget = Double.parseDouble(scanner.nextLine());

        System.out.print("Podaj dniówkę lekarza: ");
        double dailyWageDoctor = Double.parseDouble(scanner.nextLine());

        System.out.print("Podaj dniówkę pielęgniarki: ");
        double dailyWageNurse = Double.parseDouble(scanner.nextLine());

        System.out.print("Podaj liczbę lekarzy: ");
        int liczbaLekarzy = Integer.parseInt(scanner.nextLine());

        System.out.print("Podaj liczbę pielęgniarek: ");
        int liczbaPielegniarek = Integer.parseInt(scanner.nextLine());

        System.out.print("Podaj początkową liczbę pacjentów: ");
        int liczbaPacjentow = Integer.parseInt(scanner.nextLine());

        // Inicjalizacja budżetu i karetki
        Budzet budzet = new Budzet(initialBudget);
        Karetka karetka = new Karetka();

        // Generowanie personelu
        List<Personel> personelList = Personel.generujPersonel(liczbaLekarzy, liczbaPielegniarek);

        // Generowanie pacjentów z losowymi objawami i przypisaniem stanu
        List<Pacjent> hospitalizowani = new ArrayList<>();
        for (int i = 0; i < liczbaPacjentow; i++) {
            List<Objawy> objawy = StanPacjenta.generujLosoweObjawy();
            StanPacjenta.Stan stan = StanPacjenta.przypiszStan(objawy);
            hospitalizowani.add(new Pacjent(
                Osoba.losujImie(), Osoba.losujNazwisko(), stan, objawy
            ));
        }

        // Inicjalizacja zmiennych do śledzenia przebiegu symulacji
        int day = 1;
        boolean zdarzenieOczekujace = false;
        int totalRecovered = 0;
        int totalDeceased = 0;
        Random dayRand = new Random();

        // Główna pętla symulacji – trwa dopóki jest budżet
        while (budzet.ileZostalo() > 0) {
            System.out.println("=== Dzień " + day + " (Budżet: " + budzet.ileZostalo() + ") ===");

            // Oblicz koszt wynagrodzenia dla całego personelu
            long countDoctors = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ).count();
            long countNurses = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA).count();
            double costWages = countDoctors * dailyWageDoctor + countNurses * dailyWageNurse;

            // Przerwij symulację, jeśli nie starcza na pensje
            if (costWages > budzet.ileZostalo()) break;
            budzet.dodaj(-costWages);

            // Obsługa nowej tury karetki – możliwi nowi pacjenci
            karetka.nowaTura(day);
            List<Pacjent> nowiPacjenci = karetka.nowiPacjenci();
            if (!nowiPacjenci.isEmpty()) {
                hospitalizowani.addAll(nowiPacjenci);
                System.out.println("Przyjechała karetka z pacjentami:");
                nowiPacjenci.forEach(p -> System.out.println(" - " + p.sformatowaneId() + " " + p.imie + " " + p.nazwisko));

                double ambulanceCost = nowiPacjenci.size() * 300;
                if (ambulanceCost > budzet.ileZostalo()) break;
                budzet.dodaj(-ambulanceCost);
            } else {
                System.out.println("Brak nowych pacjentów w tej turze.");
            }

            // Sprawdź, którzy pacjenci zostali wyleczeni lub zmarli
            List<Pacjent> discharged = new ArrayList<>();
            List<Pacjent> deceased = new ArrayList<>();
            for (Pacjent p : new ArrayList<>(hospitalizowani)) {
                int roll = dayRand.nextInt(100);
                int szansaZgonu = StanPacjenta.szansaNaZgon(p.stan);
                int szansaWypis = StanPacjenta.szansaNaWypis(p.stan);

                if (roll < szansaZgonu) deceased.add(p);
                else if (roll < szansaZgonu + szansaWypis) discharged.add(p);
            }

            hospitalizowani.removeAll(deceased);
            hospitalizowani.removeAll(discharged);

            if (!deceased.isEmpty()) {
                System.out.println("Pacjenci zmarli:");
                deceased.forEach(p -> System.out.println(" - " + p.imie + " " + p.nazwisko));
                totalDeceased += deceased.size();
            }

            if (!discharged.isEmpty()) {
                System.out.println("Pacjenci wyleczeni:");
                discharged.forEach(p -> System.out.println(" - " + p.imie + " " + p.nazwisko));
                totalRecovered += discharged.size();
                budzet.nagrodaZaWyleczenie(discharged.size() * 2000);
            }

            // Możliwość wystąpienia zdarzenia losowego
            if (!zdarzenieOczekujace && dayRand.nextInt(100) < 30) {
                zdarzenieOczekujace = true;
                System.out.println("! Pojawiło się nowe zdarzenie! Wybierz opcję 7 w menu.");
            }

            System.out.println("Obecnie w SOR: " + hospitalizowani.size());

            // MENU użytkownika – działania w danym dniu
            boolean cont = false;
            while (!cont) {
                System.out.println("Wybierz opcję:");
                System.out.println("1. Pokaż objawy konkretnego pacjenta");
                System.out.println("2. Lista wszystkich pacjentów");
                System.out.println("3. Podgląd personelu");
                System.out.println("4. Kontynuuj");
                System.out.println("5. Pokaż bilans zysku/straty");
                System.out.println("6. Zatrudnij/zwolnij personel");
                if (zdarzenieOczekujace) System.out.println("7. Obsłuż zdarzenie");
                System.out.print("> ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Podaj ID pacjenta: ");
                        String id = scanner.nextLine();
                        hospitalizowani.stream()
                            .filter(p -> p.sformatowaneId().equals(id))
                            .findFirst().ifPresentOrElse(Pacjent::pokazObjawy,
                                () -> System.out.println("Nie znaleziono pacjenta o ID " + id));
                        break;
                    case "2":
                        hospitalizowani.forEach(p -> System.out.println(" - " + p));
                        break;
                    case "3":
                        long docs = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ).count();
                        long docsAvail = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ && p.dostepnosc).count();
                        long nurses = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA).count();
                        long nursesAvail = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA && p.dostepnosc).count();
                        System.out.println("Personel: Lekarze " + docs + " (" + docsAvail + " dostępnych), " +
                                "Pielęgniarki " + nurses + " (" + nursesAvail + " dostępnych)");
                        break;
                    case "5":
                        double net = budzet.ileZostalo() - initialBudget;
                        System.out.println(net >= 0 ? "Dotychczasowy zysk: " + net : "Dotychczasowa strata: " + Math.abs(net));
                        break;
                    case "6":
                        System.out.println("Czy chcesz zatrudnić czy zwolnić personel? (zatrudnij/zwolnij)");
                        String action = scanner.nextLine().trim().toLowerCase();
                        System.out.println("Podaj typ personelu (LEKARZ/PIELĘGNIARKA):");
                        Personel.Rola role = Personel.Rola.valueOf(scanner.nextLine().trim().toUpperCase());
                        System.out.println("Podaj liczbę osób:");
                        int num = Integer.parseInt(scanner.nextLine());

                        if (action.equals("zatrudnij")) {
                            for (int i = 0; i < num; i++) {
                                personelList.add(new Personel(
                                    Osoba.losujImie(), Osoba.losujNazwisko(), role, Personel.losujDostepnosc()
                                ));
                            }
                            System.out.println("Zatrudniono " + num + " " + role + ".");
                        } else if (action.equals("zwolnij")) {
                            int removed = 0;
                            Iterator<Personel> it = personelList.iterator();
                            while (it.hasNext() && removed < num) {
                                Personel p = it.next();
                                if (p.rolaPersonelu == role) { it.remove(); removed++; }
                            }
                            System.out.println("Zwolniono " + removed + " " + role + ".");
                        } else {
                            System.out.println("Nieznana akcja.");
                        }
                        break;
                    case "7":
                        if (zdarzenieOczekujace) {
                            Zdarzenia.wyzwolZdarzenie(scanner, budzet, hospitalizowani);
                            zdarzenieOczekujace = false;
                        } else {
                            System.out.println("Brak zdarzenia do obsłużenia.");
                        }
                        break;
                    case "4":
                        cont = true;
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja.");
                }
            }

            day++; // przejście do kolejnego dnia
        }

        // Koniec symulacji – zapis podsumowania
        System.out.println("\nSymulacja zakończona. Ostateczny budżet: " + budzet.ileZostalo());
        String summary = String.format(
            "Podsumowanie:\nDni: %d\nWyleczeni: %d\nZmarli: %d\nOstateczny budżet: %.2f\n",
            day - 1, totalRecovered, totalDeceased, budzet.ileZostalo()
        );

        try {
            Report.saveReport("report.txt", summary);  // zapis podsumowania do pliku
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu raportu: " + e.getMessage());
        }
    }
}
