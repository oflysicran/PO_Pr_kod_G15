package po_pr_kod_g15;

import java.util.*;

/**
 * Zarządza losowymi zdarzeniami w SOR, takimi jak wypadki.
 * Użytkownik może zaakceptować lub odrzucić zdarzenie, co wpływa na budżet i liczbę pacjentów.
 */
public class Zdarzenia {
    private static final Random rand = new Random();
    private static final List<String> nazwyZdarzen = List.of(
        "Wypadek drogowy", "Pożar budynku", "Zatrucie grupowe", "Katastrofa kolejowa"
    );
    private static final Map<String, Integer> kosztZdarzenia = Map.of(
        "Wypadek drogowy", 5000,
        "Pożar budynku", 8000,
        "Zatrucie grupowe", 3000,
        "Katastrofa kolejowa", 10000
    );

    /**
     * Losuje zdarzenie i pyta użytkownika o akceptację.
     * @param scanner - źródło wejścia
     * @param budzet - budżet SOR
     * @param hospitalizowani - lista hospitalizowanych pacjentów
     */
    public static void wyzwolZdarzenie(Scanner scanner, Budzet budzet, List<Pacjent> hospitalizowani) {
        String zdarzenie = nazwyZdarzen.get(rand.nextInt(nazwyZdarzen.size()));
        int koszt = kosztZdarzenia.get(zdarzenie);
        int dodatkowiPacjenci = 2 + rand.nextInt(4); // 2-5 nowych pacjentów
        System.out.println("Uwaga! Zdarzenie: " + zdarzenie + ". Koszt: " + koszt + ", pacjentów: " + dodatkowiPacjenci);
        System.out.print("Czy akceptujesz zdarzenie? (tak/nie): ");
        String odp = scanner.nextLine().trim().toLowerCase();
        if (odp.equals("tak")) {
            budzet.dodaj(-koszt);
            for (int i = 0; i < dodatkowiPacjenci; i++) {
                List<Objawy> objawy = StanPacjenta.generujLosoweObjawy();
                StanPacjenta.Stan stan = StanPacjenta.przypiszStan(objawy);
                hospitalizowani.add(new Pacjent(
                    Osoba.losujImie(), Osoba.losujNazwisko(), stan, objawy
                ));
            }
            System.out.println("Zdarzenie zaakceptowane. Hospitalizowanych +" + dodatkowiPacjenci + ", budżet -" + koszt);
        } else {
            System.out.println("Zdarzenie odrzucone. Brak zmian.");
        }
    }
}
