/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


// Budzet.java
package po_pr_kod_g15;

public class Budzet {
    private double sumaStartowa;
    private double aktualnyBudzet;

    public Budzet(double sumaStartowa) {
        this.sumaStartowa = sumaStartowa;
        this.aktualnyBudzet = sumaStartowa;
    }

    public void dodaj(double kwota) {
        aktualnyBudzet += kwota;
    }

    public boolean odejmij(double kwota) {
        if (kwota <= aktualnyBudzet) {
            aktualnyBudzet -= kwota;
            return true;
        } else {
            System.out.println("Brak wystarczaj\u0105cych \u015brodk\u00f3w w bud\u017cecie.");
            return false;
        }
    }

    public double getAktualnyBudzet() {
        return aktualnyBudzet;
    }

    public double getSumaStartowa() {
        return sumaStartowa;
    }

    @Override
    public String toString() {
        return String.format("Bud\u017cet: %.2f / %.2f", aktualnyBudzet, sumaStartowa);
    }
}
