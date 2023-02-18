import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ForkJoinWorkerThread;

public class Main {
    public static void main(String[] args) {
        //Wczytywanie Matek z pliku
        Matka.wczytajMatkiZPliku("src/matki.txt");

        //Wczytywanie Noworodkow z pliku
        Noworodek.wczytajNoworodkiZPliku("src/noworodki.txt");

        //1Podaj imię i wzrost najwyższego chłopca oraz imię i wzrost najwyższej dziewczynki.
        System.out.print("Najwyzszy chlopiec: ");
        Noworodek najwyzszyChlopiec = Noworodek.najwyzszyChlopiec(Noworodek.getEkstensja());
        System.out.println(najwyzszyChlopiec.getImie()+", "+najwyzszyChlopiec.getWzrost()+"cm");
        System.out.print("Najwyzsza dziewczynka: ");
        Noworodek najwyzszaDziewczynka = Noworodek.najwyzszaDziewczynka(Noworodek.getEkstensja());
        System.out.println(najwyzszaDziewczynka.getImie()+", "+najwyzszaDziewczynka.getWzrost()+"cm");
        //2W którym dniu urodziło się najwięcej dzieci? Podaj datę i liczbę dzieci.
        LocalDate najwiecejDzieci = Noworodek.urodziloSieNajwiecejDzieci(Noworodek.getIleDzieciWJakimDniu());
        System.out.print("Najwiecej dzieci urodzilo sie: ");
        System.out.println(najwiecejDzieci+" liczba dzieci: "+Noworodek.getIleDzieciWJakimDniu().get(najwiecejDzieci));
        //3Podaj imiona kobiet w wieku poniżej 25 lat, które urodziły dzieci o wadze powyżej 4000 g.
        List<Matka> ponizej25Urodzilypowyzej4kg = Matka.kobietyPonizej25ZdziecmiPowyzej4kg(Matka.getEkstensja());
        System.out.println("Imiona kobiet w wieku ponizej 25lat, ktore urodzily dzieci o wadze powyzej 4kg: ");
        for (Matka matka : ponizej25Urodzilypowyzej4kg) {
            System.out.print(matka.getImie()+", ");
        }
        System.out.println();
        //4Podaj imiona i daty urodzenia dziewczynek, które odziedziczyły imię po matce.
        System.out.println("Imiona i daty urodzenia dziewczynek, ktore dziedzicza imie po mamie:");
        List<Noworodek> dziedziczaPoMamie = Noworodek.dziewczynkiDziedziczaceImie(Noworodek.getEkstensja());
        for (Noworodek noworodek : dziedziczaPoMamie) {
            System.out.print(noworodek.getImie()+" "+noworodek.getDataUrodzenia()+" ");
        }
        System.out.println();
        //5Podaj daty, w których urodziły się bliźnięta.
        System.out.println("Daty w ktorych urodzily sie bliznieta: ");
        Set<LocalDate> daty = Noworodek.datyBlizniat(Noworodek.getEkstensja());
        for (LocalDate localDate : daty) {
            System.out.println(localDate);
        }

    }
}