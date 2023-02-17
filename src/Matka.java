import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matka {
    private final int id;
    private String imie;
    private int wiek;


    private static List<Matka> ekstensja = new ArrayList<>();

    public Matka(int id, String imie, int wiek) {
        this.id = id;
        this.imie = imie;
        this.wiek = wiek;
    }



    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public int getId() {
        return id;
    }

    public static List<Matka> getEkstensja() {
        return ekstensja;
    }
}
