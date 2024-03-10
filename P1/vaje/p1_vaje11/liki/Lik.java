
import java.util.*;

public abstract class Lik implements Comparable<Lik> {

    @Override
    public int compareTo(Lik drugi) {
        return this.ploscina() - drugi.ploscina();
    }

    public static Comparator<Lik> poObsegu() {
        /*
        return new Comparator<Lik>() {
            @Override
            public int compare(Lik lik1, Lik lik2) {
                return lik1.obseg() - lik2.obseg();
            }
        };
        */

        // return (o1, o2) -> o1.obseg() - o2.obseg();

        // return Comparator.comparingInt(Lik::obseg);

         return new PrimerjalnikPoObsegu();
    }

    private static class PrimerjalnikPoObsegu implements Comparator<Lik> {
        @Override
        public int compare(Lik lik1, Lik lik2) {
            return lik1.obseg() - lik2.obseg();
        }
    }

    public static Comparator<Lik> poTipu() {
//        return (lik1, lik2) -> lik1.vrniSteviloTipa() - lik2.vrniSteviloTipa();
        return new PrimerjalnikPoTipu();
    }

    public static class PrimerjalnikPoTipu implements Comparator<Lik> {
        @Override
        public int compare(Lik lik1, Lik lik2) {
            return lik1.vrniSteviloTipa() - lik2.vrniSteviloTipa();
        }
    }

    public static void urediPoTipuInObsegu(Vektor<Lik> vektor) {
        Skupno.uredi(vektor, Skupno.kompozitum(poTipu(), poObsegu()));
    }

    public abstract int ploscina();

    public abstract int vrniSteviloTipa();

    public abstract int obseg();

    public String toString() {
        return String.format("%s [%s]", this.vrsta(), this.podatki());
    }

    // Vrne vrsto lika <this> (npr. "pravokotnik").
    public abstract String vrsta();

    // Vrne niz s podatki o liku <this> 
    // (npr. "širina = 3.0, višina = 4.0").
    public abstract String podatki();

    public static void izpisi(Vektor<Lik> vektor) {
        int stElementov = vektor.steviloElementov();
        for (int i = 0;  i < stElementov;  i++) {
            Lik lik = vektor.vrni(i);
            System.out.printf("%s | p = %d | o = %d%n",
                    lik.toString(), lik.ploscina(), lik.obseg());
        }
    }
}
