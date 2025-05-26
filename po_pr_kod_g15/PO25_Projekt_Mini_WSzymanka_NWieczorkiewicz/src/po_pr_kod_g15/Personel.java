package po_pr_kod_g15;

import java.util.Random;

public class Personel extends Osoba {

    protected enum Rola {
        LEKARZ,
        PIELĘGNIARKA
    }

    protected boolean dostepnosc;
    protected final Rola rolaPersonelu;


    public Personel(String imie, String nazwisko, Rola rolaPersonelu, boolean dostepnosc) {
        super(imie, nazwisko, TypOsoby.PERSONEL);
        this.rolaPersonelu = rolaPersonelu;
        this.dostepnosc = dostepnosc;
    }

    /**
     * Personel leczy pacjenta, jeśli jest dostępny.
     */
    public void lecz(Pacjent pacjent) {
        if (dostepnosc) {
            System.out.println(rolaPersonelu + " leczy pacjenta o ID " + pacjent.sformatowaneId() + " (stan: " + pacjent.stan + ")");
        } else {
            System.out.println(rolaPersonelu + " nie jest dostępny do leczenia.");
        }
    }

    /**
     * Zwraca string reprezentujący personel
     */
    @Override
    public String toString() {
        return "Personel - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko + " (rola: " + rolaPersonelu + ", dostępny: " + dostepnosc + ")";
    }

    /**
     * Zmienia status dostępności personelu
     */
    public void ustawDostepnosc(boolean status) {
        this.dostepnosc = status;
    }

    /**
     * Losuje dostępność personelu (np. przy generacji)
     */
    public static boolean losujDostepnosc() {
        return new Random().nextBoolean();
    }
}
