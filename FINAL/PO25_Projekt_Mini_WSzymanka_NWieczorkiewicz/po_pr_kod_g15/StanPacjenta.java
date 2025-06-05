package po_pr_kod_g15;

import java.util.*;

public class StanPacjenta {
    // Enum definiujący możliwe stany pacjenta
    public enum Stan { CZERWONY, ZOLTY, NIEBIESKI }

    // Metoda przypisująca stan pacjenta na podstawie sumy ciężkości objawów
    public static Stan przypiszStan(List<Objawy> objawy) {

        int suma = objawy.stream().mapToInt(Objawy::ciezkoscWartosc).sum();

        // Im niższa suma, tym bardziej krytyczny stan (czerwony)
        if (suma <= 8) return Stan.CZERWONY;
        if (suma <= 12) return Stan.ZOLTY;
        return Stan.NIEBIESKI;
    }

    // Generujemy losowe objawy dla pacjenta, po jednym z każdej kategorii
    public static List<Objawy> generujLosoweObjawy() {
        List<Objawy> wynik = new ArrayList<>();
        // Dla każdej kategorii objawów losujemy jeden objaw
        for (Objawy.Kategoria kat : Objawy.Kategoria.values()) {
            wynik.add(Objawy.losowy(kat));
        }
        return wynik;
    }

    // Zwraca procentową szansę na zgon w zależności od stanu
    public static int szansaNaZgon(Stan stan) {
        return switch (stan) {
            case CZERWONY  -> 30; // Czerwony = 30%
            case ZOLTY     -> 15; // Żółty = 15%
            case NIEBIESKI -> 5;  // Niebieski = 5%
        };
    }

    // Zwraca szansę na wypis ze szpitala w zależności od stanu
    public static int szansaNaWypis(Stan stan) {
        return switch (stan) {
            case CZERWONY  -> 10;
            case ZOLTY     -> 30;
            case NIEBIESKI -> 60;
        };
    }
}
