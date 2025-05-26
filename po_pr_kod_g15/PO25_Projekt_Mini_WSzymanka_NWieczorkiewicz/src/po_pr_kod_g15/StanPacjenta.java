package po_pr_kod_g15;

import java.util.ArrayList;
import java.util.List;

public class StanPacjenta {

    public enum Stan {
        Niebieski,
        Zolty,
        Czerwony;
    }

    public static Stan przypiszStan(List<Objawy> objawy) {
        int suma = objawy.stream().mapToInt(Objawy::ciezkoscWartosc).sum();
        if (suma >= 13) return Stan.Niebieski;
        if (suma >= 9) return Stan.Zolty;
        return Stan.Czerwony;
    }

    public static List<Objawy> generujLosoweObjawy() {
        List<Objawy> wynik = new ArrayList<>();
        wynik.add(Objawy.losowy(Objawy.Kategoria.oczy));
        wynik.add(Objawy.losowy(Objawy.Kategoria.werbalna));
        wynik.add(Objawy.losowy(Objawy.Kategoria.ruchowa));
        return wynik;
    }
}
