package po_pr_kod_g15;
import java.util.HashSet; // do przechowywania już użytych ID, 
import java.util.Set;     // żeby każde nowe ID było unikalne.
import java.util.Random;

public abstract class Osoba {
    static final Set<Integer> usedIds = new HashSet<>();
    static final Random random = new Random();

    public final int id;

    public Osoba() {
        this.id = generateUniqueId();
    }

    private int generateUniqueId() {
        if (usedIds.size() >= 9999) {
            throw new RuntimeException("Wszystkie możliwe ID zostały wykorzystane.");
        }

        int newId;
        do {
            newId = 1 + random.nextInt(9999); // liczba z zakresu 0001–9999
        } while (usedIds.contains(newId));

        usedIds.add(newId);
        return newId;
    }

    @Override
    public String toString() {
        return "ID: " + String.format("%04d", id);
    }
}
