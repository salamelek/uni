public class Macka extends Sesalec {
    private int steviloHranjenjMacka;
    private int steviloPreganjanjMacka;

    @Override
    public void seHrani() {
        this.steviloHranjenjMacka++;
        super.seHrani();
    }

    @Override
    public void preganja(Zival druga) {
        this.steviloPreganjanjMacka++;
        super.preganja(druga);
    }

    @Override
    public int steviloHranjenj() {
        return this.steviloHranjenjMacka;
    }

    @Override
    public int steviloPreganjanj(Zival druga) {
        return this.steviloPreganjanjMacka;
    }
}
