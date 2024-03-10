public class Pravokotnik extends Lik {
    private double sirina;
    private double visina;

    public Pravokotnik(double sirina, double visina) {
        this.sirina = sirina;
        this.visina = visina;
    }

    // razred, ki je podrazred abstraktenga razreda ali
    // definira vse abstraktne metode ali pa postane abstrakten tui sam

    // treba je napisat @Override, ker spet definiramo metode
    @Override
    public double ploscina() {
        return this.sirina * this.visina;
    }

    @Override
    public double obseg() {
        return this.sirina * 2 + this.visina * 2;
    }

    @Override
    public String vrsta() {
        return "pravokotnik";
    }

    @Override
    public String podatki() {
        return String.format("širina = %.1f, višina = %.1f", this.sirina, this.visina);
    }

    public double vrniSirino() {
        return this.sirina;
    }
}