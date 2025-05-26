package po_pr_kod_g15;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Osoba {
    public enum TypOsoby {
        PERSONEL, PACJENT
    }

    // Zestaw użytych ID, aby zapewnić unikalność
    private static final Set<Integer> usedIds = new HashSet<>();
    private static final Random random = new Random();

    // Dane osoby
    protected final int id;
    protected final String imie;
    protected final String nazwisko;
    protected final TypOsoby typ;

    // Listy imion i nazwisk do losowania (można później udostępnić publicznie)
    protected static final String[] IMIONA = {
        "Anna", "Jan", "Maria", "Piotr", "Katarzyna", "Tomasz", "Agnieszka", "Paweł",
        "Monika", "Michał", "Zofia", "Andrzej", "Aleksandra", "Krzysztof", "Barbara", "Marek",
        "Elżbieta", "Wojciech", "Natalia", "Grzegorz", "Joanna", "Łukasz", "Małgorzata", "Adam",
        "Weronika", "Damian", "Julia", "Szymon", "Helena", "Mateusz", "Emilia", "Rafał",
        "Karolina", "Marcin", "Ewa", "Sebastian", "Magdalena", "Jakub", "Dorota", "Patryk",
        "Iwona", "Dariusz", "Izabela", "Daniel", "Beata", "Oliwia", "Konrad", "Renata", "Jacek", "Justyna"
    };

    protected static final String[] NAZWISKA = {
        "Rower", "Pokój", "Mur", "Kwiat", "Kamień", "Topór", "Zegar", "Łyżka", "Wąż", "Dym",
        "Cień", "Lot", "Klon", "Trawa", "Deszcz", "Wilk", "Lis", "Jeż", "Kruk", "Miód",
        "Kos", "Grom", "Głos", "Wicher", "Szept", "Nurt", "Szum", "Mróz", "Wazon", "Dzwon",
        "Ogień", "Pień", "Smok", "Szlak", "Koral", "Rytm", "Szczyt", "Pajęk", "Żuraw", "Szkło",
        "Brzeg", "Żar", "Obłok", "Świt", "Gaj", "Strumyk", "Błysk", "Kieł", "Bąk", "Skok"
    };

    // Konstruktor generuje unikalne ID automatycznie
    public Osoba(String imie, String nazwisko, TypOsoby typ) {
        this.id = generateUniqueId();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.typ = dodajRole(typ);
    }

    // Metoda generująca unikalne ID w zakresie 1-9999
    private int generateUniqueId() {
        if (usedIds.size() >= 9999) {
            throw new RuntimeException("Wszystkie możliwe ID zostały wykorzystane.");
        }
        int newId;
        do {
            newId = 1 + random.nextInt(9999);
        } while (usedIds.contains(newId));
        usedIds.add(newId);
        return newId;
    }

    // Zwraca ID w formacie 4-cyfrowym z wiodącymi zerami
    protected String sformatowaneId() {
        return String.format("%04d", id);
    }

    // Opcjonalne metody do losowania imienia i nazwiska
    public static String losujImie() {
        return IMIONA[random.nextInt(IMIONA.length)];
    }

    public static String losujNazwisko() {
        return NAZWISKA[random.nextInt(NAZWISKA.length)];
    }
    
    public static TypOsoby dodajRole(TypOsoby typ){
        return typ;
    }
    
    @Override
    public abstract String toString();
}
