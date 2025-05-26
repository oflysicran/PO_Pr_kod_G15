package po_pr_kod_g15;

import java.util.ArrayList;
import java.util.List;

public enum StanPacjenta {
    Niebieski,
    Zolty,
    Czerwony;

    public static List<Objawy> generujLosowe() {
        List<Objawy> wynik = new ArrayList<>();
        wynik.add(Objawy.losowy(Objawy.Kategoria.oczy));
        wynik.add(Objawy.losowy(Objawy.Kategoria.werbalna));
        wynik.add(Objawy.losowy(Objawy.Kategoria.ruchowa));
        return wynik;
    }

    public static StanPacjenta przypiszStan(List<Objawy> objawy) {
        int suma = objawy.stream().mapToInt(Objawy::ciezkoscWartosc).sum();
        if (suma >= 13) return StanPacjenta.Niebieski;
        if (suma >= 9) return StanPacjenta.Zolty;
        return StanPacjenta.Czerwony;
    }
}
