package po_pr_kod_g15;

import java.util.*;

public class Sor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj liczbę tur: ");
        int liczbaTur = scanner.nextInt();
        scanner.nextLine();

        Budzet budzet = new Budzet(10000);
        Karetka karetka = new Karetka();

        // Generujemy początkowy personel
        List<Personel> personelList = generujPersonel(5);
        List<Pacjent> hospitalizowani = new ArrayList<>();

        for (int tura = 1; tura <= liczbaTur; tura++) {
            System.out.println("\n=== Tura " + tura + " ===");

            // 1) Przyjazd karetek lub samodzielna wizyta
            karetka.nowaTura(tura);
            List<Pacjent> nowiPacjenci = karetka.nowiPacjenci();
            if (!nowiPacjenci.isEmpty()) {
                hospitalizowani.addAll(nowiPacjenci);
                System.out.println("Przyjechała karetka z pacjentami:");
                nowiPacjenci.forEach(p ->
                    System.out.println(" - " + p.sformatowaneId() + " " + p.imie + " " + p.nazwisko)
                );
            } else {
                // Możliwość samodzielnego przyjścia - 20% szansy
                
                    System.out.println("Brak nowych pacjentów w tej turze.");
            }
            

            // 2) Zmiany stanu pacjentów
            List<Pacjent> doZwolnienia = new ArrayList<>();
            List<Pacjent> zmarli = new ArrayList<>();
            Random rand = new Random();
            for (Pacjent p : new ArrayList<>(hospitalizowani)) {
                int los = rand.nextInt(100);
                if (los < 10) { // 10% na śmierć
                    zmarli.add(p);
                } else if (los < 30) { // 20% na wyleczenie
                    doZwolnienia.add(p);
                }
            }
            hospitalizowani.removeAll(zmarli);
            hospitalizowani.removeAll(doZwolnienia);

            // Wypis minimalnych informacji
            if (!zmarli.isEmpty()) {
                System.out.println("Pacjenci zmarli:");
                zmarli.forEach(p -> System.out.println(" - " + p.imie + " " + p.nazwisko));
            }
            if (!doZwolnienia.isEmpty()) {
                System.out.println("Pacjenci wyleczeni:");
                doZwolnienia.forEach(p -> System.out.println(" - " + p.imie + " " + p.nazwisko));
            }
            System.out.println("Obecnie w SOR: " + hospitalizowani.size());

            // 3) Interaktywne menu
            boolean dalej = false;
            while (!dalej) {
                System.out.println("\nWybierz opcję:");
                System.out.println("1. Pokaż objawy konkretnego pacjenta");
                System.out.println("2. Lista wszystkich pacjentów (ID, imię, nazwisko, stan)");
                System.out.println("3. Podgląd personelu");
                System.out.println("4. Kontynuuj");
                System.out.print("> ");
                String wybor = scanner.nextLine();
                switch (wybor) {
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
                            System.out.println(" - " 
                                + p.sformatowaneId() + " " 
                                + p.imie + " " 
                                + p.nazwisko + " (" 
                                + p.stan + ")")
                        );
                        break;
                    case "3":
                        long lek = personelList.stream()
                            .filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ).count();
                        long lekD = personelList.stream()
                            .filter(p -> p.rolaPersonelu == Personel.Rola.LEKARZ && p.dostepnosc).count();
                        long piel = personelList.stream()
                            .filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA).count();
                        long pielD = personelList.stream()
                            .filter(p -> p.rolaPersonelu == Personel.Rola.PIELĘGNIARKA && p.dostepnosc).count();
                        System.out.println("Personel: Lekarze " + lek + " (" + lekD + " dostępnych), " +
                                           "Pielęgniarki " + piel + " (" + pielD + " dostępnych)");
                        break;
                    case "4":
                        dalej = true;
                        break;
                    default:
                        System.out.println("Nieprawidłowa opcja.");
                }
            }
        }
        System.out.println("\nSymulacja zakończona.");
    }

    private static List<Personel> generujPersonel(int liczba) {
        List<Personel> lista = new ArrayList<>();
        for (int i = 0; i < liczba; i++) {
            String imie = Osoba.losujImie();
            String nazwisko = Osoba.losujNazwisko();
            Personel.Rola rola = (i % 2 == 0)
                ? Personel.Rola.LEKARZ
                : Personel.Rola.PIELĘGNIARKA;
            boolean dostepnosc = Personel.losujDostepnosc();
            lista.add(new Personel(imie, nazwisko, rola, dostepnosc));
        }
        return lista;
    }
}
