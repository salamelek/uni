import java.lang.Math;

public class Tocka {
    private double x;
    private double y;

    public Tocka(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double vrniX() {
        return this.x;
    }

    public double vrniY() {
        return this.y;
    }

    public String toString() {
        return String.format("(%.2f, %.2f)", this.x, this.y);
    }

    public double razdalja(Tocka t) {
        return Math.sqrt(Math.pow((t.x - this.x), 2) + Math.pow((t.y - this.y), 2));
    }

    public static Tocka izhodisce() {
        return new Tocka(0, 0);
    }

    public double razdaljaOdIzhodisca() {
        return this.razdalja(izhodisce());
    }
}