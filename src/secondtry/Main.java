package secondtry;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args)  {
       try {
           Matka.wczytajMatki("src/matki.txt");
           Noworodek.wczytajNoworodki("src/noworodki.txt");
       } catch (IOException e) {
           e.printStackTrace();
       }

        //1 Podaj imie i wzrost najwyzszego chlopca i dziewczynki [1 metoda]
        //2 W ktorym dniu urodzilo sie najwiecej dzieci? Podaj date i liczbe dzieci
        //3 Podaj imiona kobiet wiek < 25, ktore urodzily dzieci powyzej 4000g
        //4 Podaj imiona i daty urodzenia dziewczynek które odziedziczyły imie po matce
        //5 Bliznieta maja ta sama date urodzenia oraz idMatki, Podaj daty w ktorych urodzily sie bliznieta

        //1
        List<Noworodek> listaNajwyzszych = Noworodek.najwyzsi(Noworodek.getEkstensja());
        System.out.print("Najwyzszy chlopiec i dziewczynka: ");
        for (Noworodek n : listaNajwyzszych) {
            System.out.print(n.getImie()+" "+n.getWzrost()+" ");
        }
        System.out.println();

        //2
        Map<LocalDate, Integer> najwiecejDzieciWdniu = Noworodek.najwiecejDzieciWdniu(Noworodek.getEkstensja());
        System.out.print("Najwiecej urodzonych dzieci: ");
        for (Map.Entry<LocalDate, Integer> values : najwiecejDzieciWdniu.entrySet()) {
            System.out.print("Data: "+values.getKey()+" ilość dzieci: "+values.getValue());
        }
        System.out.println();

        //3
        List<Matka> kobietyMniejNiz25UrodzilyPowyzej4kg = Matka.mniejNizxLatUrodzilyPowyzejWagi(Matka.getEkstensja(), 25, 4000);
        System.out.print("Kobiety mniej niz 25 ktore urodzily dziecko powyzej 4kg: ");
        for (Matka matka : kobietyMniejNiz25UrodzilyPowyzej4kg) {
            System.out.print(matka.getImie()+" ");
        }
        System.out.println();

        //4
        List<Noworodek> imiePoMatce = Noworodek.odziedziczylyImiePoMatce(Noworodek.getEkstensja());
        System.out.print("Imiona i daty urodzenia dziewczynek ktore odziedziczyly imie po matce: ");
        for (Noworodek noworodek : imiePoMatce) {
            System.out.print(noworodek.getImie()+" "+noworodek.getDataUrodzenia()+" ");
        }

        //5
        Set<LocalDate> datyBlizniat = Noworodek.datyUrodzeniaBlizniat();
        System.out.println("Daty urodzenia blizniat: ");
        for (LocalDate localDate : datyBlizniat) {
            System.out.println(localDate);
        }

    }
}
