public class Oseba {
    private String ip;
    private char spol;
    private int starost;

    public Oseba(String ip, char spol, int starost) {
        this.ip = ip;
        this.spol = spol;
        this.starost = starost;
    }

    public String toString() {
        return String.format("%s, %c, %d", this.ip, this.spol, this.starost);
//        return String.format("%s", this.ip);
    }

    public boolean jeStarejsaOd(Oseba os) {
        if (this.starost > os.starost) {
            return true;
        }

        return false;
    }

    public char vrniSpol() {
        return this.spol;
    }

    public int vrniStarost() {
        return this.starost;
    }
}
