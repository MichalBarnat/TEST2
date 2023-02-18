import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class Noworodek {
    private final int id;
    private final char plec; //c-corka, s-syn
    private String imie;
    private final LocalDate dataUrodzenia;
    private int waga;
    private int wzrost;
    private final int idMatki;


    private Matka matka;

    private static Map<LocalDate, Integer> ileDzieciWJakimDniu = new HashMap<>();


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
        //przypisujemy matke do dziecka po id
        matka = Matka.getMatkaById(Matka.getEkstensja(), idMatki);
        //dodajemy matce dziecko do listy
        matka.getDzieci().add(this);
        //ile dzieci w jakim dniu:
        if(ileDzieciWJakimDniu.containsKey(dataUrodzenia)) {
            int current = ileDzieciWJakimDniu.get(dataUrodzenia);
            ileDzieciWJakimDniu.put(dataUrodzenia, current + 1);
        } else {
            ileDzieciWJakimDniu.put(dataUrodzenia, 1);
        }


    }

    public static void wczytajNoworodkiZPliku(String nazwaPliku) {
        File spisNoworodkow = new File(nazwaPliku);
        try{
            Scanner input = new Scanner(spisNoworodkow);
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

    //1Podaj imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.
    private boolean jestChlopcem() {
        return plec == 's';
    }

    public static Noworodek najwyzszyChlopiec(List<Noworodek> list) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Nie ma noworodkow!");
        }
        Noworodek result = list.get(0);
        for (Noworodek noworodek : list) {
            if(noworodek.jestChlopcem() && noworodek.getWzrost() > result.getWzrost()) {
                result = noworodek;
            }
        }
        return result;
    }

    public static Noworodek najwyzszaDziewczynka(List<Noworodek> list) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Nie ma noworodkow!");
        }
        Noworodek result = list.get(0);
        for (Noworodek noworodek : list) {
            if(!noworodek.jestChlopcem() && noworodek.getWzrost() > result.getWzrost()) {
                result = noworodek;
            }
        }
        return result;
    }

    //2W którym dniu urodziło się najwięcej dzieci? Podaj datę i liczbę dzieci.
    public static LocalDate urodziloSieNajwiecejDzieci(Map<LocalDate, Integer> map) {
        if(map.isEmpty()) {
            throw new IllegalArgumentException("Brak danych!");
        }
        LocalDate result = LocalDate.now();
        int howMuch = 0;
        for (Map.Entry<LocalDate, Integer> values : map.entrySet()) {
            LocalDate date = values.getKey();
            int i = values.getValue();
            if(i > howMuch) {
                result = date;
                howMuch = i;
            }
        }
        return  result;
    }

    //4Podaj imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.
    private boolean dziedziczyImie() {
        return getImie().equals(getMatka().getImie());
    }

    public static List<Noworodek> dziewczynkiDziedziczaceImie(List<Noworodek> list) {
        if(list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Brak noworodkow!");
        }
        List<Noworodek> result = new ArrayList<>();
        for (Noworodek noworodek : list) {
            if(noworodek.dziedziczyImie()) {
                result.add(noworodek);
            }
        }
        return result;
    }

    //5Podaj daty, w których urodziły się bliźnięta.
    public static Set<LocalDate> datyBlizniat(List<Noworodek> list) {
        Set<LocalDate> daty = new HashSet<>();
        List<Noworodek> bliznieta = new ArrayList<>();
        //szukamy par blizniat
        for (Noworodek n1 : list) {
            for (Noworodek n2 : list) {
                if (n1 != n2 && n1.getIdMatki() == n2.getIdMatki() && n1.getDataUrodzenia().compareTo(n2.getDataUrodzenia()) == 0) {
                    bliznieta.add(n1);
                }
            }
        }
        //tworzymy set dat z listy blizniat
        for (Noworodek noworodek : bliznieta) {
            daty.add(noworodek.getDataUrodzenia());
        }
        return daty;
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

    public Matka getMatka() {
        return matka;
    }

    public static Map<LocalDate, Integer> getIleDzieciWJakimDniu() {
        return ileDzieciWJakimDniu;
    }


    @Override
    public String toString() {
        return "Noworodek{" +
                "id=" + id +
                ", plec=" + plec +
                ", imie='" + imie + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", waga=" + waga +
                ", wzrost=" + wzrost +
                ", idMatki=" + idMatki +
                ", matka=" + matka.getImie() +
                '}';
    }
}
