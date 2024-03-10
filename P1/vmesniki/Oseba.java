import java.util.Comparator;

public class Oseba {
    private String ime;
    private String priimek;
    private int starost;

    public Oseba(String ime, String priimek, int starost) {
        this.ime = ime;
        this.priimek = priimek;
        this.starost = starost;
    }

    private static class PrimerjalnikPoStarosti implements Comparator<Oseba> {
        @Override
        public int compare(Oseba a, Oseba b) {
//            return a.priimek.compareTo(b.priimek);
            return a.starost - b.starost;
        }
    }

    public static Comparator<Oseba> primerjalnikPoSpoluInStarosti() {
        return new PrimerjalnikPoStarosti();
    }
}