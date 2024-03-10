public class Premica {
    private double k;
    private double n;

    public Premica(double k, double n) {
        this.k = k;
        this.n = n;
    }

    public double vrniK() {
        return this.k;
    }

    public double vrniN() {
        return this.n;
    }

    public String toString() {
        return String.format("y = %.2f x + %.2f", k, n);
    }

    public Tocka tockaPriX(double x) {
        return new Tocka(x, this.k * x + n);
    }

    public static Premica skoziTocko(double k, Tocka t) {
        return new Premica(k, t.vrniY() - k * t.vrniX());
    }

    public Premica vzporednica(Tocka t) {
        return new Premica(this.k, t.vrniY() - this.k * t.vrniX());
    }

    public Premica pravokotnica(Tocka t) {
        return new Premica(-1 / this.k, t.vrniY() - (-1 / this.k) * t.vrniX());
    }

    public Tocka presecisce(Premica p, double epsilon) {
        if (Math.abs(this.k - p.k) < epsilon) {
            return null;
        }

        double x = (p.n - this.n) / (this.k - p.k);
        double y = k * x + this.n;

        return new Tocka(x, y);
    }

    public Tocka projekcija(Tocka t) {
        return presecisce(this.pravokotnica(t), 0);
    }

    public double razdalja(Tocka t) {
        return t.razdalja(projekcija(t));
    }

    public double razdaljaOdIzhodisca() {
        return razdalja(Tocka.izhodisce());
    }

    public double razdalja(double n) {
        return Math.abs(n - this.n) * Math.cos(Math.atan(k));
    }
}