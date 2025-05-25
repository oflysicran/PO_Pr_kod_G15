package po_pr_kod_g15;

public class Pacjent extends Osoba {
    public enum StanPacjenta {
        NIEBIESKI, ŻÓŁTY, CZERWONY
    }
    protected StanPacjenta stan;
    
    public Pacjent(int id, String imie, String nazwisko, StanPacjenta stan) {
        super(id, imie, nazwisko, TypOsoby.PACJENT);
        this.stan = stan;
    }

    @Override
    public String toString() {
        return "Pacjent - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko;
    }
}
