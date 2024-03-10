
import java.util.*;

public class Liga {

    private Collection<String> klubi;
    private Collection<Tekma> tekme;

    public Liga(Collection<String> klubi, Collection<Tekma> tekme) {
        this.klubi = klubi;
        this.tekme = tekme;
    }

    public int steviloTock(String klub) {
        int stTock = 0;

        /*
        for (Tekma tekma: this.tekme) {
            stTock += tekma.steviloTock(klub);
        }
        */

        /*
        Iterator<Tekma> it = tekme.iterator();

        while (it.hasNext()) {
            Tekma tekma = it.next();
            stTock += tekma.steviloTock(klub);
        }
        */

        for (Iterator<Tekma> it = tekme.iterator(); it.hasNext(); ) {
            Tekma tekma = it.next();
            stTock += tekma.steviloTock(klub);
        }

        return stTock;
    }

    public Slovar<String, Integer> klub2tocke() {
        Slovar<String, Integer> slovar = new Slovar<>();

        for (String klub: this.klubi) {
            slovar.shrani(klub, this.steviloTock(klub));
        }

        return slovar;
    }

    public List<String> lestvica() {
        Slovar<String, Integer> slovar = klub2tocke();
        List<String> lestvica = new ArrayList<>(this.klubi);

        lestvica.sort(new PrimerjalnikPoTockah(slovar));

        return lestvica;
    }

    public static class PrimerjalnikPoTockah implements Comparator<String> {
        private Slovar<String, Integer> slovar;

        public PrimerjalnikPoTockah(Slovar<String, Integer> slovar) {
            this.slovar = slovar;
        }

        @Override
        public int compare(String klub1, String klub2) {
            int diff = this.slovar.vrni(klub2) - this.slovar.vrni(klub1);

            if (diff != 0) {
                return diff;
            } else {
                return klub1.compareTo(klub2);
            }
        }
    }

    public Iterator<Tekma> poTekmah(int minGR) {

        return null;
    }

    public static class IteratorPoTekmah implements Iterator<Tekma> {
        private int minGR;
        private Iterator<Tekma> iterator;
        private Tekma nextMatch;

        public IteratorPoTekmah(Collection<Tekma> tekme, int minGR) {
            this.iterator = tekme.iterator();
            this.minGR = minGR;
            this.nextMatch = poisciNaslednjoTekmo();
        }

        @Override
        public boolean hasNext() {
            return this.nextMatch != null;
        }

        @Override
        public Tekma next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            // TODO nevem ce to je to

            return this.nextMatch;
        }

        private Tekma poisciNaslednjoTekmo() {
            while (this.iterator.hasNext()) {
                Tekma tekma = iterator.next();
                if (tekma.golRazlika() > this.minGR) {
                    return tekma;
                }
            }

            return null;
        }
    }
}
