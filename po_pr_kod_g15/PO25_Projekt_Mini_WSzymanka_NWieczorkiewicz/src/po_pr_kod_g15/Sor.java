package po_pr_kod_g15;

import java.util.*;

public class Sor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ustawienia początkowe budżetu i stawek
        System.out.print("Podaj początkowy budżet: ");
        double initialBudget = Double.parseDouble(scanner.nextLine());
        System.out.print("Podaj dniówkę lekarza: ");
        double dailyWageDoctor = Double.parseDouble(scanner.nextLine());
        System.out.print("Podaj dniówkę pielęgniarki: ");
        double dailyWageNurse = Double.parseDouble(scanner.nextLine());

        Budzet budzet = new Budzet(initialBudget);
        Karetka karetka = new Karetka();

        // Generujemy początkowy personel
        List<Personel> personelList = generujPersonel(5);
        List<Pacjent> hospitalizowani = new ArrayList<>();

        int day = 1;
        while (budzet.ileZostalo() > 0) {
            System.out.println("\n=== Dzień " + day + " (Budżet: " + budzet.ileZostalo() + ") ===");

            // 0) Automatyczna wypłata dniówek personelu
            int countDoctors = (int) personelList.stream()
                .filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ).count();
            int countNurses = (int) personelList.stream()
                .filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA).count();
            double costWages = countDoctors * dailyWageDoctor + countNurses * dailyWageNurse;
            if (costWages > budzet.ileZostalo()) {
                System.out.println("Brak środków na wypłatę dniówek: koszt " + costWages + ", zostało " + budzet.ileZostalo());
                break;
            }
            budzet.dodaj(-costWages);

            // 1) Przyjazd karetek i koszt
            karetka.nowaTura(day);
            List<Pacjent> nowiPacjenci = karetka.nowiPacjenci();
            if (!nowiPacjenci.isEmpty()) {
                hospitalizowani.addAll(nowiPacjenci);
                System.out.println("Przyjechała karetka z pacjentami:");
                nowiPacjenci.forEach(p ->
                    System.out.println(" - " + p.sformatowaneId() + " " + p.imie + " " + p.nazwisko)
                );
                double ambulanceCost = nowiPacjenci.size() * 300;
                if (ambulanceCost > budzet.ileZostalo()) {
                    System.out.println("Brak środków na opłacenie karetki (" + ambulanceCost + ")");
                    break;
                }
                budzet.dodaj(-ambulanceCost);
            } else {
                System.out.println("Brak nowych pacjentów w tej turze.");
            }

            // 2) Zmiany stanu pacjentów i przychody
            List<Pacjent> discharged = new ArrayList<>();
            List<Pacjent> deceased = new ArrayList<>();
            Random rand = new Random();
            for (Pacjent p : new ArrayList<>(hospitalizowani)) {
                int roll = rand.nextInt(100);
                if (roll < 10) {
                    deceased.add(p);
                } else if (roll < 30) {
                    discharged.add(p);
                }
            }
            hospitalizowani.removeAll(deceased);
            hospitalizowani.removeAll(discharged);

            if (!deceased.isEmpty()) {
                System.out.println("Pacjenci zmarli:");
                deceased.forEach(p -> System.out.println(" - " + p.imie + " " + p.nazwisko));
            }
            if (!discharged.isEmpty()) {
                System.out.println("Pacjenci wyleczeni:");
                discharged.forEach(p -> System.out.println(" - " + p.imie + " " + p.nazwisko));
                double revenuePerPatient = 2000;
                budzet.nagrodaZaWyleczenie(discharged.size() * revenuePerPatient);
            }

            System.out.println("Obecnie w SOR: " + hospitalizowani.size());

            // 3) Interaktywne menu
            boolean cont = false;
            while (!cont) {
                System.out.println("\nWybierz opcję:");
                System.out.println("1. Pokaż objawy konkretnego pacjenta");
                System.out.println("2. Lista wszystkich pacjentów (ID, imię, nazwisko, stan)");
                System.out.println("3. Podgląd personelu");
                System.out.println("4. Kontynuuj");
                System.out.print("> ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("Podaj ID pacjenta: ");
                        String id = scanner.nextLine();
                        hospitalizowani.stream()
                            .filter(p -> p.sformatowaneId().equals(id))
                            .findFirst()
                            .ifPresentOrElse(
                                p -> p.pokazObjawy(),
                                () -> System.out.println("Nie znaleziono pacjenta o ID " + id)
                            );
                        break;
                    case "2":
                        System.out.println("Lista pacjentów:");
                        hospitalizowani.forEach(p ->
                            System.out.println(" - " + p.sformatowaneId() + " " + p.imie + " " + p.nazwisko + " (" + p.stan + ")")
                        );
                        break;
                    case "3":
                        long docs = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ).count();
                        long docsAvail = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ && p.dostepnosc).count();
                        long nurses = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA).count();
                        long nursesAvail = personelList.stream().filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA && p.dostepnosc).count();
                        System.out.println("Personel: Lekarze " + docs + " (" + docsAvail + " dostępnych), " +
                                           "Pielęgniarki " + nurses + " (" + nursesAvail + " dostępnych)");
                        break;
                    case "4":
                        cont = true;
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja.");
                }
            }

            day++;
        }

        System.out.println("\nSymulacja zakończona. Ostateczny budżet: " + budzet.ileZostalo());
    }

    private static List<Personel> generujPersonel(int liczba) {
        List<Personel> lista = new ArrayList<>();
        for (int i = 0; i < liczba; i++) {
            String imie = Osoba.losujImie();
            String nazwisko = Osoba.losujNazwisko();
            Personel.Rola rola = (i % 2 == 0) ? Personel.Rola.LEKARZ : Personel.Rola.PIELĘGNIARKA;
            boolean dostepnosc = Personel.losujDostepnosc();
            lista.add(new Personel(imie, nazwisko, rola, dostepnosc));
        }
        return lista;
    }
}
