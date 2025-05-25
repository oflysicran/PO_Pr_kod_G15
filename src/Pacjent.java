package po_pr_kod_g15;

public class Pacjent extends Osoba {
    protected enum stan {
        Niebieski, Zolty, Czerwony;
    }
    
    public Pacjent() {
        super();
    }

    @Override
    public String toString() {
        return "Pacjent - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko;
    }
}
