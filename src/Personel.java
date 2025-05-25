package po_pr_kod_g15;

public class Personel extends Osoba {
    protected enum rola{
        Lekarz, Pielegniarka;
    }
    boolean dostepnosc;
    
    public Personel() {
        super();
    }

    @Override
    public String toString() {
        return "Personel - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko;
    }
}


