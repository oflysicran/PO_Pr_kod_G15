package po_pr_kod_g15;

import java.util.*;

public class Objawy {
    public enum Kategoria {
        oczy, werbalna, ruchowa
    }

    private final Kategoria kategoria;
    private final int ciezkosc;
    private final String nazwa;

    public Objawy(Kategoria kategoria, int wartosc, String opis) {
        this.kategoria = kategoria;
        this.ciezkosc = wartosc;
        this.nazwa = opis;
    }

    public int ciezkoscWartosc() {
        return this.ciezkosc;
    }

    public String ciezkoscOpis() {
        return this.nazwa;
    }

    public Kategoria ciezkoscKategoria() {
        return this.kategoria;
    }

    public static Objawy losowy(Kategoria kat) {
        List<Objawy> lista = new ArrayList<>();
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
        return lista.get(new Random().nextInt(lista.size()));
    }
}
