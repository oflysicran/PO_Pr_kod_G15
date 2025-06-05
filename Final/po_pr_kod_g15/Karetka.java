package po_pr_kod_g15;

import java.util.*;

/**
 * Klasa reprezentuje karetkę, która może przywozić pacjentów w trakcie symulacji
 */
public class Karetka {
    // lista pacjentów, którzy przyjechali w tej turze
    protected final List<Pacjent> pacjenci = new ArrayList<>();
    
    // obiekt do losowania różnych rzeczy (np. czy karetka przyjedzie)
    private final Random los = new Random();

    /**
     * Metoda wywoływana na początku każdej tury.
     * Z prawdopodobieństwem 60% dodaje pacjentów przywiezionych przez karetkę
     *
     * @param turaId – numer aktualnej tury (na razie nieużywany, ale może się przydać później)
     */
    public void nowaTura(int turaId) {
        int szansa = los.nextInt(100);
        if (szansa < 60) {
            dodajPacjent(turaId);
        }   
    }

    /**
     * Dodaje 2 lub 3 pacjentów. Każdy ma losowe objawy, stan i imię z nazwiskiem
     *
     * @param turaId – numer tury, tylko przekazywany dalej (obecnie bez użycia)
     */
    private void dodajPacjent(int turaId) {
        int liczbaPacjentow = 2 + los.nextInt(2); // losuje 2 albo 3

        for (int i = 0; i < liczbaPacjentow; i++) {
            // generuje losowe objawy
            List<Objawy> objawy = StanPacjenta.generujLosoweObjawy();
            
            // przypisuje stan pacjenta na podstawie objawów
            StanPacjenta.Stan stan = StanPacjenta.przypiszStan(objawy);

            // losuje imię i nazwisko pacjenta
            String imie = Osoba.losujImie();
            String nazwisko = Osoba.losujNazwisko();

            // tworzy nowego pacjenta i dodaje go do listy
            Pacjent pacjent = new Pacjent(imie, nazwisko, stan, objawy);
            pacjenci.add(pacjent);
        }   
    }

    /**
     * Zwraca pacjentów przywiezionych w tej turze, a potem czyści listę
     * Dzięki temu nie wrócą w kolejnej turze
     *
     * @return lista pacjentów z tej tury
     */
    public List<Pacjent> nowiPacjenci() {
        List<Pacjent> nowi = new ArrayList<>(pacjenci);
        pacjenci.clear();
        return nowi;
    }
}
