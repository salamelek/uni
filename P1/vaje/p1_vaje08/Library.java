import java.util.Arrays;

public class Library extends Knjiznica {
    private Transakcija transakcije[];

    public Library(int stClanov, int stNaslovov, int stIzvodovNaNaslov, Transakcija[] transakcije) {
        super(stClanov, stNaslovov, stIzvodovNaNaslov);

        this.transakcije = transakcije;
    }

    public Par[] vrniZakljuceneIzposoje() {
        System.out.println(this.transakcije[0].indexOf("|"));
        return null;
    }

    public Transakcija[] vrniOdprteIzposoje() {
        return null;
    }

    public int[] knjizniMolj() {
        return null;
    }

    public String najdaljsaIzposoja() {
        return "";
    }
}