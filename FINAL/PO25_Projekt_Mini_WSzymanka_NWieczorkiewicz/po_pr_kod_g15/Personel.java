// Personel.java
package po_pr_kod_g15;

import java.util.*;

public class Personel extends Osoba {

    // Enum określający rolę danej osoby w personelu
    public enum Rola { LEKARZ, PIELĘGNIARKA }

    // Czy dana osoba jest dostępna do pracy 
    protected boolean dostepnosc;

    // Rola przypisana danej osobie (lekarz albo pielęgniarka)
    protected final Rola rolaPersonelu;

    
     // Konstruktor tworzy nowego pracownika szpitala (personel)
     
    public Personel(String imie, String nazwisko, Rola rolaPersonelu, boolean dostepnosc) {
        super(imie, nazwisko, TypOsoby.PERSONEL);
        this.rolaPersonelu = rolaPersonelu;
        this.dostepnosc = dostepnosc;
    }

    
    //Zwraca informacje o osobie w formacie tekstowym (ID, imię, nazwisko, rola, dostępność)
    
    @Override
    public String toString() {
        return String.format("Personel[ID=%s, %s %s, rola=%s, dostepnosc=%b]",
            sformatowaneId(), imie, nazwisko, rolaPersonelu, dostepnosc);
    }

    
     //Próbuje leczyć pacjenta – tylko jeśli personel jest dostępny
    public void lecz(Pacjent pacjent) {
        if (dostepnosc) {
            System.out.println(rolaPersonelu + " leczy pacjenta o ID " + pacjent.sformatowaneId() + " (stan: " + pacjent.stan + ")");
        } else {
            System.out.println(rolaPersonelu + " nie jest dostępny do leczenia.");
        }
    }


    
     //Zmienia status dostępności personelu 

    public void ustawDostepnosc(boolean status) {
        this.dostepnosc = status;
    }

    /**
     * Losuje dostępność personelu (true/false)
     * @return losowy boolean – dostępny albo nie
     */
    public static boolean losujDostepnosc() {
        return new Random().nextBoolean();
    }

    /**
     * Tworzy listę pracowników – określona liczba lekarzy i pielęgniarek
     * @param lekarz – ilu lekarzy wygenerować
     * @param pielegniarka – ile pielęgniarek wygenerować
     * @return lista personelu gotowego do pracy
     */
    public static List<Personel> generujPersonel(int lekarz, int pielegniarka) {
        List<Personel> lista = new ArrayList<>();
        for (int i = 0; i < lekarz; i++) {
            lista.add(new Personel(Osoba.losujImie(), Osoba.losujNazwisko(), Rola.LEKARZ, losujDostepnosc()));
        }
        for (int i = 0; i < pielegniarka; i++) {
            lista.add(new Personel(Osoba.losujImie(), Osoba.losujNazwisko(), Rola.PIELĘGNIARKA, losujDostepnosc()));
        }
        return lista;
    }
}
