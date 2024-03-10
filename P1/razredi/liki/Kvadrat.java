public class Kvadrat extends Pravokotnik {
    public Kvadrat(double stranica) {
        super(stranica, stranica);
        // ne definiramo stranico, ker je waste of space
        // this.stranica = stranica;
    }

    // metodi ploscina in obseg sta ze v redu

    @Override
    public String vrsta() {
        return "kvadrat";
    }

    @Override
    public String podatki() {
        return String.format("stranica = %.1f", this.vrniSirino());
    }
}