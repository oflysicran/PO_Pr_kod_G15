import java.util.*;

public class Objawy{
  public enum Kategoria{
    oczy, werbalna, ruchowa
  }

  private final Kategoria kategoria;
  private final int wartosc;
  private final String opis;

  public Objawy(Kategoria kategoria, int wartosc, String opis){
    this.kategoria = kategoria;
    this.wartosc = wartosc;
    this.opis = opis;
  }

  public int ciezkoscWartosc(){
    return this.wartosc;
  }

  public string ciezkoscOpis(){
    return this.opis;
  }

  public Kategoria ciezkoscKategoria(){
    return this.kategoria;
  }

  public static Objawy losowy(Kategoria kat){
    List<Objawy> lista = new ArrayList<>();
    switch (kat){
      case oczy: {
        lista.add(new Objawy(kat, 4, "Oczy otwarte"));
        lista.add(new Objawy(kat, 3, "Otwiera na polecenie"));
        lista.add(new Objawy(kat, 2, "Otwiera na ból"));
        lista.add(new Objawy(kat, 1, "Oczy zamknięte"));









  
