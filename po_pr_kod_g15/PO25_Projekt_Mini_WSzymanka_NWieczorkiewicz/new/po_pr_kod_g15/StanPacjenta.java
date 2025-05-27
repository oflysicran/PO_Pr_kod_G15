package po_pr_kod_g15;

import java.util.*;

public class StanPacjenta {
    public enum Stan { CZERWONY, ZOLTY, NIEBIESKI }

    public static Stan przypiszStan(List<Objawy> objawy) {
        int suma = objawy.stream().mapToInt(Objawy::ciezkoscWartosc).sum();
        if (suma <= 8) return Stan.CZERWONY;
        if (suma <= 12) return Stan.ZOLTY;
        return Stan.NIEBIESKI;
    }

    public static List<Objawy> generujLosoweObjawy() {
        List<Objawy> wynik = new ArrayList<>();
        for (Objawy.Kategoria kat : Objawy.Kategoria.values()) {
            wynik.add(Objawy.losowy(kat));
        }
        return wynik;
    }

    public static int szansaNaZgon(Stan stan) {
        return switch (stan) {
            case CZERWONY  -> 50;
            case ZOLTY     -> 20;
            case NIEBIESKI -> 5;
        };
    }

    public static int szansaNaWypis(Stan stan) {
        return switch (stan) {
            case CZERWONY  -> 10;
            case ZOLTY     -> 30;
            case NIEBIESKI -> 60;
        };
    }
}
