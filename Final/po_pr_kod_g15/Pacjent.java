package po_pr_kod_g15;

import java.util.List;
import java.util.ArrayList;

/**
 * Klasa Pacjent dziedziczy po Osoba – reprezentuje pacjenta w systemie
 * Przechowuje jego stan zdrowia i listę objawów
 */
public class Pacjent extends Osoba {

    // Stan zdrowia pacjenta 
    protected StanPacjenta.Stan stan;

    // Lista objawów, które pacjent ma 
    protected final List<Objawy> objawy;

 
    public Pacjent(String imie, String nazwisko, StanPacjenta.Stan stan, List<Objawy> objawy) {
        super(imie, nazwisko, TypOsoby.PACJENT);
        this.stan = stan;
        this.objawy = new ArrayList<>(objawy); // tworzy kopię, żeby nie modyfikować oryginału
    }

    
    //Pokazuje wszystkie objawy pacjenta
    
    public void pokazObjawy() {
        System.out.println("Objawy pacjenta " + imie + " " + nazwisko + ":");
        for (Objawy o : objawy) {
            System.out.println(" - " + o.ciezkoscKategoria() + ": " + o.ciezkoscOpis() + " (" + o.ciezkoscWartosc() + ")");
        }
    }

    
     //Zwraca opis pacjenta jako tekst – zawiera ID, imię, nazwisko i jego stan
     
    @Override
    public String toString() {
        return "Pacjent - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko + " (stan: " + stan + ")";
    }
}
