package po_pr_kod_g15;

import java.util.*;

/**
 * Klasa odpowiada za losowe zdarzenia w SOR-ze (np. wypadki, pożary).
 * Gracz (użytkownik) decyduje, czy zgadza się na przyjęcie pacjentów ze zdarzenia - traci konkretną kwoe ale może zyskać na wyleczonych pacjentach
 */
public class Zdarzenia {
    //losowanie zdarzeń
    private static final Random rand = new Random();

    // Lista nazw możliwych zdarzeń 
    private static final List<String> nazwyZdarzen = List.of(
        "Wypadek drogowy", "Pożar budynku", "Zatrucie grupowe", "Katastrofa kolejowa"
    );

    // Mapowanie zdarzeń na ich koszt
    private static final Map<String, Integer> kosztZdarzenia = Map.of(
        "Wypadek drogowy", 5000,
        "Pożar budynku", 8000,
        "Zatrucie grupowe", 3000,
        "Katastrofa kolejowa", 10000
    );

    /**
     * Metoda wywołuje losowe zdarzenie i pyta użytkownika, czy je zaakceptować.

     * @param scanner - do wczytania odpowiedzi użytkownika
     * @param budzet - aktualny budżet szpitala
     * @param hospitalizowani - lista aktualnie przyjętych pacjentów
     */
    public static void wyzwolZdarzenie(Scanner scanner, Budzet budzet, List<Pacjent> hospitalizowani) {
        // Losujemy jedno zdarzenie z listy
        String zdarzenie = nazwyZdarzen.get(rand.nextInt(nazwyZdarzen.size()));
        int koszt = kosztZdarzenia.get(zdarzenie);
        int dodatkowiPacjenci = 2 + rand.nextInt(6); // będą 2 do 7 nowych pacjentów

        // Informujemy użytkownika co się wydarzyło
        System.out.println("Uwaga! Zdarzenie: " + zdarzenie + ". Koszt: " + koszt + ", pacjentów: " + dodatkowiPacjenci);
        System.out.print("Czy akceptujesz zdarzenie? (tak/nie): ");

        // Wczytujemy odpowiedź
        String odp = scanner.nextLine().trim().toLowerCase();

        // Jak użytkownik się zgadza, to odliczamy koszty i dodajemy nowych pacjentów
        if (odp.equals("tak")) {
            budzet.dodaj(-koszt); // minus bo wydatek
            for (int i = 0; i < dodatkowiPacjenci; i++) {
                List<Objawy> objawy = StanPacjenta.generujLosoweObjawy(); // losujemy objawy
                StanPacjenta.Stan stan = StanPacjenta.przypiszStan(objawy); // przypisujemy stan
                // Tworzymy nowego pacjenta z losowymi danymi i dodajemy do listy
                hospitalizowani.add(new Pacjent(
                    Osoba.losujImie(), Osoba.losujNazwisko(), stan, objawy
                ));
            }
            System.out.println("Zdarzenie zaakceptowane. Hospitalizowanych +" + dodatkowiPacjenci + ", budżet -" + koszt);
        } else {
            // Jak użytkownik odmówił – to nic się nie dzieje
            System.out.println("Zdarzenie odrzucone. Brak zmian.");
        }
    }
}
