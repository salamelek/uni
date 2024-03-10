
import java.util.*;

public class Clanek {
    private List<Avtor> avtorji;
    private String naslov;
    private int leto;

    public Clanek(List<Avtor> avtorji, String naslov, int leto) {
        this.avtorji = avtorji;
        this.naslov = naslov;
        this.leto = leto;
    }

    @Override
    public String toString() {
        String strAvtorji = this.avtorji.toString();
        return String.format("%s: %s (%d)",
                strAvtorji.substring(1, strAvtorji.length() - 1), this.naslov, this.leto);
    }

    public List<Avtor> vrniAvtorje() {
        return this.avtorji;
    }

    public static Comparator<Clanek> poLetuInNaslovu() {
        return (clanek1, clanek2) -> {
            if (clanek1.leto != clanek2.leto) {
                return clanek2.leto - clanek1.leto;
            }

            return clanek1.naslov.compareTo(clanek2.naslov);
        };
    }
}
