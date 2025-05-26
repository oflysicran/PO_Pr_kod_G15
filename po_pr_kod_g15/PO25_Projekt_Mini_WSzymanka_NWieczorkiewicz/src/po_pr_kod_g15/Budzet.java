package po_pr_kod_g15;

public class Budzet {
    // rekomendowany maksymalny budżet startowy, aby liczba tur nie była zbyt duża
    public static final double REKOMENDOWANY_MAX = 1_000_000.0;

    private final double sumaStartowa;
    private double aktualnyBudzet;

    /**
     * @param sumaStartowa – początkowy budżet; zalecane <= REKOMENDOWANY_MAX
     */
    public Budzet(double sumaStartowa) {
        if (sumaStartowa > REKOMENDOWANY_MAX) {
            System.out.println("Uwaga: zadeklarowany budżet przekracza rekomendowany poziom (" 
                               + REKOMENDOWANY_MAX + "). Symulacja może trwać bardzo długo.");
        }
        this.sumaStartowa = sumaStartowa;
        this.aktualnyBudzet = sumaStartowa;
    }

    /**
     * Odejmij kwotę z budżetu (np. dniówki personelu).
     * @param kwota – dodatnia wartość zostanie odjęta z aktualnego budżetu.
     */
    public void dodaj(double kwota) {
        this.aktualnyBudzet += kwota;
    }

    /**
     * Dodaj nagrodę za wyleczenie pacjenta
     * @param przychodZaPacjenta – kwota, którą otrzymujesz za jednego wyleczonego
     */
    public void nagrodaZaWyleczenie(double przychodZaPacjenta) {
        aktualnyBudzet += przychodZaPacjenta;
    }

    /**
     * @return ile zostało środków w budżecie.
     */
    public double ileZostalo() {
        return aktualnyBudzet;
    }

    @Override
    public String toString() {
        return String.format("Budżet: %.2f / %.2f", aktualnyBudzet, sumaStartowa);
    }
}
