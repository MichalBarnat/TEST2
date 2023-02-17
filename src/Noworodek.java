import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Noworodek {
    private final int id;
    private final char plec; //c-corka, s-syn
    private String imie;
    private final LocalDate dataUrodzenia;
    private int waga;
    private int wzrost;
    private final int idMatki;


    private static List<Noworodek> ekstensja = new ArrayList<>();

    public Noworodek(int id, char plec, String imie, LocalDate dataUrodzenia, int waga, int wzrost, int idMatki) {
        this.id = id;
        this.plec = plec;
        this.imie = imie;
        this.dataUrodzenia = dataUrodzenia;
        this.waga = waga;
        this.wzrost = wzrost;
        this.idMatki = idMatki;
        ekstensja.add(this);
    }

    public static void wczytajNoworodkiZPliku(String nazwaPliku) {
        File spisMatek = new File(nazwaPliku);
        try{
            Scanner input = new Scanner(spisMatek);
            input.useDelimiter("\n");
            while(input.hasNext()) {
                int id = input.nextInt();
                char plec = input.next().charAt(0);
                String imie = input.next();
                LocalDate data = LocalDate.parse(input.next());
                int waga = input.nextInt();
                int wzrost = input.nextInt();
                int idMatki = input.nextInt();

                Noworodek noworodek = new Noworodek(id, plec, imie, data, waga, wzrost, idMatki);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public int getWzrost() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public int getId() {
        return id;
    }

    public char getPlec() {
        return plec;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public int getIdMatki() {
        return idMatki;
    }

    public static List<Noworodek> getEkstensja() {
        return ekstensja;
    }
}
