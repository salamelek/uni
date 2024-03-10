import java.util.*;

public class Main {
    public static void main(String[] args) {
        Oseba ana = new Oseba("Ana", "Ledinek", 2003, 'Z', true);
        Oseba sam = new Oseba("Samuel", "Sluga", 2004, 'M', true);
        Oseba sam2 = new Oseba("Samuel", "Sluga", 2004, 'Z', false);
        Oseba vic = new Oseba("Vittoria", "Vascotto", 2004, 'Z', false);

        List<Oseba> sez = new ArrayList<>();

        sez.add(vic);
        sez.add(sam);
        sez.add(ana);
        sez.add(sam2);

//        System.out.println(sez);
        sez.sort(null);
//        System.out.println(sez);


        Parabola par = new Parabola();

        System.out.println(par.f(2.0));
    }

    public static class Parabola implements Funkcija<Double, Double> {
        @Override
        public Double f(Double a) {
            return a * a;
        }
    }
}