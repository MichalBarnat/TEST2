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

    private List<Noworodek> dzieci = new ArrayList<>();

    public Matka(int id, String imie, int wiek) {
        this.id = id;
        this.imie = imie;
        this.wiek = wiek;
        ekstensja.add(this);
    }


    public static void wczytajMatkiZPliku(String nazwaPliku) {
        File spisMatek = new File(nazwaPliku);
        try{
            Scanner input = new Scanner(spisMatek);
            while(input.hasNext()) {
                int id = input.nextInt();
                String imie = input.next();
                int wiek = input.nextInt();

                Matka matka = new Matka(id, imie, wiek);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Matka getMatkaById(List<Matka> list, int id) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak matek!");
        }
        Matka m = list.get(0);
        for (Matka matka : list) {
            if(matka.getId() == id) {
                m = matka;
            }
        }
        if(m.getId() != id) {
            throw new IllegalArgumentException("Nie mozna znalezc matki o takim id!");
        }
        return m;
    }

    //3Podaj imiona kobiet w wieku poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g.
    private boolean ponizej25Lat() {
        return wiek < 25;
    }

    private boolean urodzilaDzieckoPowyzej4kg() {
        for (Noworodek noworodek : dzieci) {
            if(noworodek.getWaga() > 4000) {
                return true;
            }
        }
        return false;
    }

    public static List<Matka> kobietyPonizej25ZdziecmiPowyzej4kg(List<Matka> list){
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak matek!");
        }
        List<Matka> result = new ArrayList<>();
        for (Matka matka : list) {
            if(matka.ponizej25Lat() && matka.urodzilaDzieckoPowyzej4kg()) {
                result.add(matka);
            }
        }
        return result;
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

    public List<Noworodek> getDzieci() {
        return dzieci;
    }

    public String printDzieci() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Noworodek noworodek : dzieci) {
            stringBuilder.append(noworodek.getImie());
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Matka{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", wiek=" + wiek;
    }
}
