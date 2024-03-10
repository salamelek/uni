public class Vektor<T> {
    private T[] elementi;
    private int stElementov;
    private static final int ZACETNA_KAPACITETA = 10;

    public Vektor() {
        this(ZACETNA_KAPACITETA);
    }

    // TODO koncaj file

    @SuppressWarnings("unchecked")
    private void poPotrebiPovecaj() {
        if (this.stElementov == this.elementi.length) {
            T[] stariElementi = this.elementi;
            this.elementi = (T[]) new Object[2 * this.stElementov];
            for (int i=0; i<this.stElementov; i++) {
                this.elementi[i] = stariElementi[i];
            }
        }
    }
}