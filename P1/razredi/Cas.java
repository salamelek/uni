public class Cas {
    // če je spremenljivka private, se je ne more dobiti kar tako
    // če je atribut "private", bo spremenljivka dostopna samo znotraj razreda
    // to pomeni, da znotraj razreda Cas, so ura in minuta dostopni brez težav
    private int ura;
    private int minuta;

    // konstruktor se imenuje enako kot razred
    // konstruktor nima izhodnega tipa
    // konstruktor je namenjen inicializacij atributov
    public Cas(int h, int min) {
        this.ura = h;
        this.minuta = min;
    }

    // s static
    public static void pristejStatic(Cas cas, int h, int min) {
        // pretvori cas v minute
        int casVMin = 60 * (cas.ura + h) + (cas.minuta + min);
        // pazi na negativen cas in na nove dni
        int noviCas = (casVMin % 1440 + 1440) % 1440;

        cas.ura = noviCas / 60;
        cas.minuta = noviCas % 60;
    }


    public void dodajCas(int h, int min) {
        // pretvori cas v minute
        int casVMin = 60 * (this.ura + h) + (this.minuta + min);
        // pazi na negativen cas in na nove dni
        int noviCas = (casVMin % 1440 + 1440) % 1440;

        // this je kot en parameter v katerega gre vecerja
        this.ura = noviCas / 60;
        this.minuta = noviCas % 60;
    }

    public void printCas() {
        System.out.printf("%d:%d%n", this.ura, this.minuta);
    }

    public int vrniUro() {
        return this.ura;
    }

    public int vrniMinuto() {
        return this.minuta;
    }

    // vrne nov kazalec na objekt, ki predstavlja trenutek,
    // ki je za h ur in m minut oddaljen od trenutka,
    // ki ga predstavlja objekt, na katerega kaže kazalec

    // to se rabi namesto setterja, ker
    // dela vse preko pointerjev in bi pač bila zmeda
    public Cas plus(int h, int m) {
        int noviCas = 60 * (this.ura + h) + this.minuta + m;
        noviCas = (noviCas % 1440 + 1440 % 1440);

        int novaUra = noviCas / 60;
        int novaMinuta = noviCas % 60;

        return new Cas(novaUra, novaMinuta);
    }

    public void getCasString {
        return String.format("%d:%d%n", this.ura, this.minuta);
    }
}