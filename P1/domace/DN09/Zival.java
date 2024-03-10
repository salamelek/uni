public class Zival {
    private int steviloHranjenjZival;
    private int steviloPreganjanjZival;

    public Zival() {
        this.steviloHranjenjZival = 0;
        this.steviloPreganjanjZival = 0;
    }

    public void seHrani() {
        this.steviloHranjenjZival++;
    }

    public void preganja(Zival druga) {
        this.steviloPreganjanjZival++;
    }

    public int steviloHranjenj() {
        return this.steviloHranjenjZival;
    }

    public int steviloPreganjanj(Zival druga) {
        return this.steviloPreganjanjZival;
    }
}
