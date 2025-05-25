package po_pr_kod_g15;

public class Personel extends Osoba {

    protected enum rola{
        LEKARZ, PIELĘGNIARKA;
    }
    boolean dostepnosc;
    protected rola rolaPersonelu;
    
    public Personel(int id, String imie, String nazwisko, rola rolaPersonelu, boolean dostepnosc) {
        super(id, imie, nazwisko, TypOsoby.PERSONEL);
        this.rolaPersonelu = rolaPersonelu;
        this.dostepnosc = dostepnosc;
    }
    
    public void lecz(Pacjent pacjent) {
        if (dostepnosc) {
            System.out.println(rolaPersonelu + " leczy pacjenta o ID " + pacjent.id + " (stan: " + pacjent.stan + ")");
        } else {
            System.out.println(rolaPersonelu + " nie jest dostępny do leczenia.");
        }
    }

    @Override
    public String toString() {
        return "Personel - ID: " + sformatowaneId() + " - " + imie + " " + nazwisko;
    }
}





