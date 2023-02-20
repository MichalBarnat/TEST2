package secondTry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matka {
    private int id;
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

    public static void wczytajMatki(String nazwaPliku) throws IOException {
//        File file = new File(nazwaPliku);
//        try(Scanner scanner = new Scanner(file)) {
//            while(scanner.hasNext()) {
//                int id = scanner.nextInt();
//                String imie = scanner.next();
//                int wiek = scanner.nextInt();
//            }
//        }

        try (
                FileReader fileReader = new FileReader(nazwaPliku);
                Scanner scanner = new Scanner(fileReader);
        ) {
            while (scanner.hasNext()) {
                int id = scanner.nextInt();
                String imie = scanner.next();
                int wiek = scanner.nextInt();

                Matka matka = new Matka(id, imie, wiek);
            }
        }
    }

    //3 Podaj imiona kobiet wiek < 25, ktore urodzily dzieci powyzej 4000g
    private boolean urodzilaDzieckoPowyzejWagi(int waga) {
        for (Noworodek noworodek : dzieci) {
            if(noworodek.getWaga() > waga) {
                return true;
            }
        }
        return false;
    }
    public static List<Matka> mniejNizxLatUrodzilyPowyzejWagi(List<Matka> list,int wiek, int waga) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak matek");
        }
        List<Matka> result = new ArrayList<>();
        for (Matka matka : list) {
            if(matka.getWiek() < wiek && matka.urodzilaDzieckoPowyzejWagi(waga)) {
                result.add(matka);
            }
        }
        return result;
    }

    public static Matka getMatkaById(List<Matka> list, int id) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak matek");
        }
        Matka result = list.get(0);
        for (Matka matka : list) {
            if(matka.getId() == id) {
                result = matka;
            }
        }
        if(result.getId() != id) {
            throw new IllegalArgumentException("Nie ma matki z takim id!");
        }
        return result;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static List<Matka> getEkstensja() {
        return ekstensja;
    }

    public List<Noworodek> getDzieci() {
        return dzieci;
    }
}
