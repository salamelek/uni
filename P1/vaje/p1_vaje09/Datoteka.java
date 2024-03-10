public abstract class Datoteka {
    private String ime;

    public Datoteka(String ime) {
        this.ime = ime;
    }

    public abstract int velikost();

    public abstract String podatki();

    public String toString() {
        return String.format("%s [%s]", this.ime, this.podatki());
    }

    public String vrniIme() {
        return this.ime;
    }
}