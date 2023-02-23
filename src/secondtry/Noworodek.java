package secondtry;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Noworodek {
    private int id;
    private char plec; //c - corka, s-syn
    private String imie;
    private LocalDate dataUrodzenia;
    private int waga;
    private int wzrost;
    private Matka matka;

    private static List<Noworodek> ekstensja = new ArrayList<>();

    public Noworodek(int id, char plec, String imie, LocalDate dataUrodzenia, int waga, int wzrost, int idMatki) {
        this.id = id;
        this.plec = plec;
        this.imie = imie;
        this.dataUrodzenia = dataUrodzenia;
        this.waga = waga;
        this.wzrost = wzrost;
        matka = Matka.getMatkaById(Matka.getEkstensja(), idMatki);
        ekstensja.add(this);
        //dodajemy matce dziecko do listy
        matka.getDzieci().add(this);
    }

    public static void wczytajNoworodki(String nazwaPliku) throws IOException {
        try(
                FileReader fileReader = new FileReader(nazwaPliku);
                Scanner scanner = new Scanner(fileReader);
                ) {
            while(scanner.hasNext()) {
                int id = scanner.nextInt();
                char plec = scanner.next().charAt(0);
                String imie = scanner.next();
                LocalDate data = LocalDate.parse(scanner.next());
                int waga = scanner.nextInt();
                int wzrost = scanner.nextInt();
                int idMatki = scanner.nextInt();

                Noworodek noworodek = new Noworodek(id, plec, imie, data, waga, wzrost, idMatki);
            }
        }
    }

    //1 Podaj imie i wzrost najwyzszego chlopca i dziewczynki [1 metoda]
    private static Noworodek najwyzszyZplci(List<Noworodek> list, char plec) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak noworodkow");
        }
        Noworodek result = list.get(0);
        for (Noworodek noworodek : list) {
            if(noworodek.getPlec() == plec && noworodek.getWzrost() > result.getWzrost()) {
                result = noworodek;
            }
        }
        return result;
    }
    public static List<Noworodek> najwyzsi(List<Noworodek> list) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak noworodkow");
        }
        List<Noworodek> result = new ArrayList<>();
        result.add(najwyzszyZplci(Noworodek.getEkstensja(), 's'));
        result.add(najwyzszyZplci(Noworodek.getEkstensja(), 'c'));
        return result;
    }

    //2 W ktorym dniu urodzilo sie najwiecej dzieci? Podaj date i liczbe dzieci
    public static Map<LocalDate, Integer> najwiecejDzieciWdniu(List<Noworodek> list) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak noworodkow");
        }
        //tworzymy mape w ktorym dniu ile urodzilo sie dzieci
        Map<LocalDate, Integer> map = new HashMap<>();
        for (Noworodek noworodek : list) {
            if(map.containsKey(noworodek.getDataUrodzenia())) {
                int current = map.get(noworodek.getDataUrodzenia());
                map.put(noworodek.getDataUrodzenia(), current + 1);
            } else {
                map.put(noworodek.getDataUrodzenia(), 1);
            }
        }
        LocalDate datee = LocalDate.now();
        int highest = 0;
        //szukamy pary z najwiekszym intem
        for (Map.Entry<LocalDate, Integer> values : map.entrySet()) {
            LocalDate date = values.getKey();
            int num = values.getValue();
            if(num > highest) {
                highest = num;
                datee = date;
            }
        }
        Map<LocalDate, Integer> result = new HashMap<>();
        result.put(datee, highest);
        return result;
    }

    //4 Podaj imiona i daty urodzenia dziewczynek które odziedziczyły imie po matce
    private boolean imiePoMatce() {
        return imie.equals(matka.getImie());
    }

    public static List<Noworodek> odziedziczylyImiePoMatce(List<Noworodek> list) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak noworodkow");
        }
        List<Noworodek> result = new ArrayList<>();
        for (Noworodek noworodek : list) {
            if(noworodek.imiePoMatce() && noworodek.getPlec() == 'c') {
                result.add(noworodek);
            }
        }
        return result;
    }

    ////5 Bliznieta maja ta sama date urodzenia oraz idMatki, Podaj daty w ktorych urodzily sie bliznieta
    private static List<Noworodek> bliznieta(List<Noworodek> list) {
        List<Noworodek> result = new ArrayList<>();
        for (Noworodek noworodek : list) {
            for (Noworodek noworodek1 : list) {
                if(noworodek != noworodek1
                        && noworodek.getMatka().getId() == noworodek1.getMatka().getId()
                        && noworodek.getDataUrodzenia().isEqual(noworodek1.getDataUrodzenia())
                ) {
                    result.add(noworodek);
                }
            }
        }
        return result;
    }

    public static Set<LocalDate> datyUrodzeniaBlizniat() {
        Set<LocalDate> result = new HashSet<>();
        List<Noworodek> bliznieta = bliznieta(Noworodek.getEkstensja());
        for (Noworodek noworodek : bliznieta) {
            result.add(noworodek.getDataUrodzenia());
        }
        return result;
    }






    //GETTERS AND SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getPlec() {
        return plec;
    }

    public void setPlec(char plec) {
        this.plec = plec;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
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

    public Matka getMatka() {
        return matka;
    }

    public void setMatka(Matka matka) {
        this.matka = matka;
    }

    public static List<Noworodek> getEkstensja() {
        return ekstensja;
    }
}
