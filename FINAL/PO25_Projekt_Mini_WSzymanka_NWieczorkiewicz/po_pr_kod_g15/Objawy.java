package po_pr_kod_g15;

import java.util.*;

/**
 * Klasa opisująca pojedynczy objaw pacjenta – jego nazwę, kategorię i stopień ciężkości
 */
public class Objawy {

    // Typ wyliczeniowy – 3 możliwe kategorie objawów
    public enum Kategoria {
        oczy, werbalna, ruchowa
    }

    // Kategoria objawu
    protected final Kategoria kategoria;
    
    // Liczbowy stopień ciężkości (im wyższszy tym, lepszy stan zdrowia)
    protected final int ciezkosc;
    
    // Opis objawu
    protected final String nazwa;

    /**
     * Konstruktor objawu – tworzy nowy objaw z daną kategorią, wartością i opisem.
     *
     * @param kategoria – do jakiej grupy należy objaw
     * @param wartosc – liczba określająca poziom objawu
     * @param opis – tekstowy opis objawu
     */
    public Objawy(Kategoria kategoria, int wartosc, String opis) {
        this.kategoria = kategoria;
        this.ciezkosc = wartosc;
        this.nazwa = opis;
    }

    
     //Zwraca wartość liczbową ciężkości (do obliczania stanu pacjenta)
    
    public int ciezkoscWartosc() {
        return this.ciezkosc;
    }

    
     //Zwraca opis objawu
     
    public String ciezkoscOpis() {
        return this.nazwa;
    }

    //Zwraca kategorię objawu
     
    public Kategoria ciezkoscKategoria() {
        return this.kategoria;
    }

    /**
     * Zwraca losowy objaw z podanej kategorii.
     * Przydatne do generowania nowych pacjentów w symulacji.
     *
     * @param kat – kategoria, z której losujemy objaw
     * @return losowo wybrany objaw z danej grupy
     */
    public static Objawy losowy(Kategoria kat) {
        List<Objawy> lista = new ArrayList<>();

        // W zależności od kategorii wrzucamy do listy odpowiednie objawy
        switch (kat) {
            case oczy -> {
                lista.add(new Objawy(kat, 4, "Oczy otwarte"));
                lista.add(new Objawy(kat, 3, "Otwiera na polecenie"));
                lista.add(new Objawy(kat, 2, "Otwiera na ból"));
                lista.add(new Objawy(kat, 1, "Oczy zamknięte"));
            }
            case werbalna -> {
                lista.add(new Objawy(kat, 5, "Pełna orientacja"));
                lista.add(new Objawy(kat, 4, "Zdezorientowany"));
                lista.add(new Objawy(kat, 3, "Mowa niezrozumiała"));
                lista.add(new Objawy(kat, 2, "Dźwięki"));
                lista.add(new Objawy(kat, 1, "Brak dźwięków"));
            }
            case ruchowa -> {
                lista.add(new Objawy(kat, 6, "Zgodne z poleceniem"));
                lista.add(new Objawy(kat, 5, "Celowe cofnięcie"));
                lista.add(new Objawy(kat, 4, "Odruch cofnięcia"));
                lista.add(new Objawy(kat, 3, "Sztywność zgięciowa"));
                lista.add(new Objawy(kat, 2, "Sztywność wyprostna"));
                lista.add(new Objawy(kat, 1, "Brak ruchu"));
            }
        }

        // Losujemy jeden objaw z listy i zwracamy
        return lista.get(new Random().nextInt(lista.size()));
    }
}
