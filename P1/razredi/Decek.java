class Decek {
    private String ime;
    private String priimek;
    private int starost;
    private String fakulteta;

    public Decek(String imePar, String priimek, int starost, String fakulteta) {
        this.ime = imePar;
        this.priimek = priimek;
        this.starost = starost;
        this.fakulteta = fakulteta;
    }

    public String getInfo() {
        return String.format("Ime: %s, Priimek: %s, Starost: %d, Fakulteta: %s", this.ime, this.priimek, this.starost, this.fakulteta);
    }

    public static String getInfoStatic(Decek nasDecek) {
        return String.format("Ime: %s, Priimek: %s, Starost: %d, Fakulteta: %s", nasDecek.ime, nasDecek.priimek, nasDecek.starost, nasDecek.fakulteta);
    }
}