package po_pr_kod_g15;
import java.util.*;

public class Karetka{
  private final List<Pacjent> pacjenci = new ArrayList<>();
  private final Random los = new Random();

  public void nowaTura(int turaId){
    int szansa = los.nextInt(100);
    if (szansa < 30) {
      przyjazd(turaId);
    }
  }

private void przyjazd(int turaId){
  int liczbaPacjentow = 1 + los.nextInt(xxx);
  for (int i = 1; i < liczbaPacjentow; i++){
    int id = ;// nie znam metody
    List<Objawy> objawy = Objawy.generujLosowe();
    StanPacjenta stan = Objawy.przypiszStan(objawy);

    /*if (stan == StanPacjenta.Czerwony){
    }*/
  }
  public List<Pacjent> nowiPacjenci(){
    List<Pacjent> nowi = new ArrayList<>(pacjenci);
    pacjenci.clear();
    return nowi;
}
