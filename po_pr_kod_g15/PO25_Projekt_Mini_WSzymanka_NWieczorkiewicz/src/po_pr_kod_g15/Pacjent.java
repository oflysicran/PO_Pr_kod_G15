package po_pr_kod_g15;

import java.util.List;
import java.util.ArrayList;

public class Pacjent extends Osoba {
    protected StanPacjenta stan;
    protected final List<Objawy> objawy;

    /**
     * Konstruktor Pacjent tworzy nową osobę typu PACJENT z listą objawów.
     * @param imie  - imię pacjenta
     * @param nazwisko - nazwisko pacjenta
     * @param stan - stan pacjenta (enum StanPacjenta)
     * @param objawy - lista objawów (Objawy)
     */
    public Pacjent(String imie, String nazwisko, StanPacjenta stan, List<Objawy> objawy) {
        super(imie, nazwisko, TypOsoby.PACJENT);
        this.stan = stan;
        this.objawy = new ArrayList<>(objawy);
    }

    /**
     * Wyświetla listę objawów pacjenta
     */
    public void pokazObjawy() {
        System.out.println("Objawy pacjenta " + imie + " " + nazwisko + ":");
        for (Objawy o : objawy) {
            System.out.println(" - " + o.ciezkoscKategoria() + ": " + o.ciezkoscOpis() + " (" + o.ciezkoscWartosc() + ")");
        }
    }

    @Override
    public String toString() {
        return "Pacjent - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko + " (stan: " + stan + ")";
    }
}
