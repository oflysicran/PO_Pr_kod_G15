
// Personel.java
package po_pr_kod_g15;

import java.util.*;

public class Personel extends Osoba {
    public enum Rola { LEKARZ, PIELĘGNIARKA }

    protected boolean dostepnosc;
    protected final Rola rolaPersonelu;

    public Personel(String imie, String nazwisko, Rola rolaPersonelu, boolean dostepnosc) {
        super(imie, nazwisko, TypOsoby.PERSONEL);
        this.rolaPersonelu = rolaPersonelu;
        this.dostepnosc = dostepnosc;
    }

    @Override
    public String toString() {
        return String.format("Personel[ID=%s, %s %s, rola=%s, dostepnosc=%b]",
            sformatowaneId(), imie, nazwisko, rolaPersonelu, dostepnosc);
    }

    public void lecz(Pacjent pacjent) {
        if (dostepnosc) {
            System.out.println(rolaPersonelu + " leczy pacjenta o ID " + pacjent.sformatowaneId() + " (stan: " + pacjent.stan + ")");
        } else {
            System.out.println(rolaPersonelu + " nie jest dostępny do leczenia.");
        }
    }

    public void ustawDostepnosc(boolean status) {
        this.dostepnosc = status;
    }

    public static boolean losujDostepnosc() {
        return new Random().nextBoolean();
    }

    /**
     * Generuje listę personelu o podanych liczbach lekarzy i pielęgniarek.
     */
    public static List<Personel> generujPersonel(int docs, int nurses) {
        List<Personel> lista = new ArrayList<>();
        for (int i = 0; i < docs; i++) {
            lista.add(new Personel(Osoba.losujImie(), Osoba.losujNazwisko(), Rola.LEKARZ, losujDostepnosc()));
        }
        for (int i = 0; i < nurses; i++) {
            lista.add(new Personel(Osoba.losujImie(), Osoba.losujNazwisko(), Rola.PIELĘGNIARKA, losujDostepnosc()));
        }
        return lista;
    }
}
