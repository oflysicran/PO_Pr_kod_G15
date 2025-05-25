package po_pr_kod_g15;
import java.util.HashSet; // do przechowywania już użytych ID, 
import java.util.Set;     // żeby każde nowe ID było unikalne.
import java.util.Random;

public abstract class Osoba {
    static final Set<Integer> usedIds = new HashSet<>();
    static final Random random = new Random();

    public final int id;
    public final String imie;
    public final String nazwisko;
    
    private static final String[] IMIONA = {
    "Anna", "Jan", "Maria", "Piotr", "Katarzyna", "Tomasz", "Agnieszka", "Paweł",
    "Monika", "Michał", "Zofia", "Andrzej", "Aleksandra", "Krzysztof", "Barbara", "Marek",
    "Elżbieta", "Wojciech", "Natalia", "Grzegorz", "Joanna", "Łukasz", "Małgorzata", "Adam",
    "Weronika", "Damian", "Julia", "Szymon", "Helena", "Mateusz"
};
    
    private static final String[] NAZWISKA = {
    "Rower", "Mostek", "Lipiec", "Wicher", "Pokój", "Zegar", "Burza", "Topór",
    "Lód", "Klon", "Ptak", "Koral", "Wazon", "Świt", "Róg", "Brzeg",
    "Cień", "Głos", "Nurt", "Rytm", "Szczyt", "Szlak", "Lot", "Szum",
    "Kadr", "Mur", "Wąwóz", "Pień", "Szkło"
};
    

    public Osoba() {
        this.id = generateUniqueId();
        this.imie = IMIONA[random.nextInt(IMIONA.length)];
        this.nazwisko = NAZWISKA[random.nextInt(NAZWISKA.length)];
    }

    private int generateUniqueId() {
        if (usedIds.size() >= 9999) {
            throw new RuntimeException("Wszystkie możliwe ID zostały wykorzystane.");
        }

        int newId;
        do {
            newId = 1 + random.nextInt(9999); // 0001–9999 jako 1–9999
        } while (usedIds.contains(newId));

        usedIds.add(newId);
        return newId;
    }

    protected String sformatowaneId() {
        return String.format("%04d", id);
    }
}
