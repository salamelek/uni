import java.util.*;

public class Oseba implements Comparable<Oseba> {
    private String ime;
    private String priimek;
    private int leto;
    private char spol;
    private boolean invalid;

    public Oseba(String ime, String priimek, int leto, char spol, boolean invalid) {
        this.ime = ime;
        this.priimek = priimek;
        this.leto = leto;
        this.spol = spol;
        this.invalid = invalid;
    }

    @Override
    public String toString() {
        return String.format("%s %s|%d|%c|%b", ime, priimek, leto, spol, invalid);
    }

    @Override
    public int compareTo(Oseba oseba) {
        if (!(this.priimek.equals(oseba.priimek))) {
            return this.priimek.compareTo(oseba.priimek);
        }

        if (!(this.ime.equals(oseba.ime))) {
            return this.ime.compareTo(oseba.ime);
        }

        if (this.leto != oseba.leto) {
            return this.leto - oseba.leto;
        }

        if (this.spol != oseba.spol) {
            if (this.spol == 'M') {
                return 1;
            } else {
                return -1;
            }
        }

        return 0;
    }

    public static class PrimerjalnikInvalidov implements Comparator<Oseba> {
        @Override
        public int compare(Oseba o1, Oseba o2) {
            if (o1.invalid != o2.invalid) {
                if (o1.invalid) {
                    return -1;
                } else {
                    return 1;
                }
            }

            else return 0;
        }
    }

    public static Comparator<Oseba> prim() {
        return (o1, o2) -> {
            return o1.priimek.compareTo(o2.priimek);
        };
    }
}
