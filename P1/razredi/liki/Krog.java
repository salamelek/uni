// ker je v razredu Lik metoda ploscina() in obseg() abstraktna, moramo definirati ti metodi
import java.lang.Math;

public class Krog extends Lik {
    private double polmer;

    public Krog(double polmer) {
        this.polmer = polmer;
    }

    @Override
    public double ploscina() {
        return this.polmer * this.polmer;// * Math.pi;
    }

    @Override
    public double obseg() {
        return this.polmer * 2;// * Math.pi;
    }

    @Override
    public String vrsta() {
        return "krog";
    }

    @Override
    public String podatki() {
        return String.format("polmer = %.1f", this.polmer);
    }
}