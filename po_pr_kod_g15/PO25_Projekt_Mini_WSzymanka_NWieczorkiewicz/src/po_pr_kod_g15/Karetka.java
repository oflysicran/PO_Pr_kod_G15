package po_pr_kod_g15;

import java.util.*;

public class Karetka {
    protected final List<Pacjent> pacjenci = new ArrayList<>();
    private final Random los = new Random();

    /**
     * Rozpoczyna nową turę - z szansą 60% na przyjazd karetki.
     */
    public void nowaTura(int turaId) {
        int szansa = los.nextInt(100);
        if (szansa < 60) {
            przyjazd(turaId);
        }
    }

    /**
     * Generuje przyjazd karetki z 2-3 pacjentami, tworzy ich objawy i stan.
     */
    private void przyjazd(int turaId) {
        int liczbaPacjentow = 2 + los.nextInt(2); // 2–3 pacjentów

        for (int i = 0; i < liczbaPacjentow; i++) {
            List<Objawy> objawy = StanPacjenta.generujLosowe();
            StanPacjenta stan = StanPacjenta.przypiszStan(objawy);

            // losowe imię i nazwisko pacjenta
            String imie = Osoba.losujImie();
            String nazwisko = Osoba.losujNazwisko();

            Pacjent pacjent = new Pacjent(imie, nazwisko, stan, objawy);

            pacjenci.add(pacjent);
        }
    }

    /**
     * Zwraca listę nowych pacjentów i czyści listę wewnętrzną.
     */
    public List<Pacjent> nowiPacjenci() {
        List<Pacjent> nowi = new ArrayList<>(pacjenci);
        pacjenci.clear();
        return nowi;
    }
}
