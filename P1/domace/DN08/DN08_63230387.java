import java.util.Scanner;
import java.util.Arrays;

public class DN08_63230387 {
    private static abstract class Objava implements Comparable<Objava>  {
        private String zaposleni;
        private String naslov;
        private String[] avtorji;

        public Objava(String zaposleni, String naslov, String[] avtorji) {
            this.zaposleni = zaposleni;
            this.naslov = naslov;
            this.avtorji = avtorji;
        }

        public abstract int vrniTockovanoOsnovo();

        public abstract String toString();

        @Override
        public int compareTo(Objava druga) {
//            return Double.compare(druga.vrniTocke(), this.vrniTocke());
            return ((Double) druga.vrniTocke()).compareTo((Double) this.vrniTocke());
        }

        public double vrniTocke() {
            return (double) this.vrniTockovanoOsnovo() / this.avtorji.length;
        }

        public String vrniTockeNiz() {
            double tocke = this.vrniTocke();

            if (tocke % 1 == 0) {
                return String.format("%.0f", tocke);
            } else {
                int u = this.vrniTockovanoOsnovo() / this.avtorji.length;
                int v = this.vrniTockovanoOsnovo() % this.avtorji.length;
                int w = this.avtorji.length;

                // a se to upošteva kot grozota?
                outer:
                for (int maxLoops = 0; maxLoops<1000; maxLoops++) {
                    for (int i=2; i<=v; i++) {
                        if (v % i == 0 && w % i == 0) {
                            w /= i;
                            v /= i;
                            continue outer;
                        }
                    }

                    break;
                }

                return String.format("%d+%d/%d", u, v, w);
            }
        }

        public String vrniZacetniNiz() {
            // še ne znam rabiti stringBuilder-jev
            String avtorjiNiz = "";

            for (int i=0; i<this.avtorji.length; i++) {
                if (avtorji[i].equals("#")) {
                    avtorjiNiz += this.zaposleni;
                } else {
                    avtorjiNiz += avtorji[i];
                }

                if (i != this.avtorji.length - 1) {
                    avtorjiNiz += ", ";
                }
            }

            return String.format("%s: %s", avtorjiNiz, this.naslov);
        }
    }
    private static class Monografija extends Objava {
        private String zalozba;
        private int letoIzdaje;
        private String isbn;

        public Monografija(String zaposleni, String naslov, String[] avtorji,
                           String zalozba, int letoIzdaje, String isbn) {

            super(zaposleni, naslov, avtorji);

            this.zalozba = zalozba;
            this.letoIzdaje = letoIzdaje;
            this.isbn = isbn;
        }

        @Override
        public int vrniTockovanoOsnovo() {
            return 10;
        }

        @Override
        public String toString() {
            return String.format("%s. %s %d, ISBN %s | %s",
                    this.vrniZacetniNiz(), this.zalozba, this.letoIzdaje, this.isbn, this.vrniTockeNiz());
        }
    }

    private static class Referat extends Objava {
        private String naziv;
        private boolean vrsta;
        private int zacetnaStran;
        private int koncnaStran;

        public Referat(String zaposleni, String naslov, String[] avtorji, String naziv,
                       boolean vrsta, int zacetnaStran, int koncnaStran) {

            super(zaposleni, naslov, avtorji);

            this.naziv = naziv;
            this.vrsta = vrsta;
            this.zacetnaStran = zacetnaStran;
            this.koncnaStran = koncnaStran;
        }

        @Override
        public int vrniTockovanoOsnovo() {
            if (this.vrsta) {
                return 3;
            } else {
                return 1;
            }
        }

        @Override
        public String toString() {
            return String.format("%s. %s: %d-%d | %s",
                    this.vrniZacetniNiz(), this.naziv, this.zacetnaStran, this.koncnaStran, this.vrniTockeNiz());
        }
    }

    private static class Clanek extends Objava {
        private String naziv;
        private int letnik;
        private int stevilka;
        private int letoIzdaje;
        private int mestoNaLestvici;
        private int dolzinaLestvice;
        private int zacetnaStran;
        private int koncnaStran;

        public Clanek(String zaposleni, String naslov, String[] avtorji,
                      String naziv, int letnik, int stevilka, int letoIzdaje,
                      int mestoNaLestvici, int dolzinaLestvice, int zacetnaStran, int koncnaStran) {

            super(zaposleni, naslov, avtorji);

            this.naziv = naziv;
            this.letnik = letnik;
            this.stevilka = stevilka;
            this.letoIzdaje = letoIzdaje;
            this.mestoNaLestvici = mestoNaLestvici;
            this.dolzinaLestvice = dolzinaLestvice;
            this.zacetnaStran = zacetnaStran;
            this.koncnaStran = koncnaStran;
        }

        @Override
        public int vrniTockovanoOsnovo() {
            double p = (double) this.mestoNaLestvici / this.dolzinaLestvice;

            // pripravite se na grozoto!
            if (0 < p && p <= 1/4.) {
                return 10;
            } else if (1/4. < p && p <= 2/4.) {
                return 8;
            } else if (2/4. < p && p <= 3/4.) {
                return 6;
            } else if (3/4. < p && p <= 4/4.) {
                return 4;
            } else {
                return 2;
            }
            // Konec grozote. Lahko se spet umirite.
        }

        @Override
        public String toString() {
            return String.format("%s. %s %d(%d): %d-%d (%d) | %s",
                    this.vrniZacetniNiz(), this.naziv, this.letnik, this.stevilka, this.zacetnaStran,
                    this.koncnaStran, this.letoIzdaje, this.vrniTockeNiz()
            );
        }
    }

    public static void obravnajObjavo(String vrstaObjave, Objava[] objave, String zaposleni,
                                      String naslov, String[] avtorji, Scanner sc, int i) {
        switch (vrstaObjave) {
            case "monografija":
                objave[i] = new Monografija(
                        zaposleni,
                        naslov,
                        avtorji,
                        sc.next(),
                        sc.nextInt(),
                        sc.next()
                );
                break;

            case "referat":
                objave[i] = new Referat(
                        zaposleni,
                        naslov,
                        avtorji,
                        sc.next(),
                        sc.nextBoolean(),
                        sc.nextInt(),
                        sc.nextInt()
                );
                break;

            case "clanek":
                objave[i] = new Clanek(
                        zaposleni,
                        naslov,
                        avtorji,
                        sc.next(),
                        sc.nextInt(),
                        sc.nextInt(),
                        sc.nextInt(),
                        sc.nextInt(),
                        sc.nextInt(),
                        sc.nextInt(),
                        sc.nextInt()
                );
                break;

            default:
                System.out.println("Svet bo padel!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String zaposleni = sc.next();
        int stObjav = sc.nextInt();
        Objava[] objave = new Objava[stObjav];

        for (int i=0; i<stObjav; i++) {
            String vrstaObjave = sc.next();
            int stAvtorjev = sc.nextInt();
            String[] avtorji = new String[stAvtorjev];

            for (int j=0; j<stAvtorjev; j++) {
                String avtor = sc.next();
                avtorji[j] = avtor;
            }

            String naslov = sc.next();

            obravnajObjavo(vrstaObjave, objave, zaposleni, naslov, avtorji, sc, i);
        }

        Arrays.sort(objave);
        for (Objava o: objave) {
            System.out.println(o);
        }
    }
}