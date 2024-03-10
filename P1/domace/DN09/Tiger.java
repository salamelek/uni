public class Tiger extends Macka {
    private int steviloHranjenj;
    private int steviloPreganjanj;

    public Tiger() {
        super();

        this.steviloHranjenj = 0;
        this.steviloPreganjanj = 0;
    }

    @Override
    public void seHrani() {
        this.steviloHranjenj++;
        super.seHrani();
    }

    @Override
    public void preganja(Zival druga) {
        this.steviloPreganjanj++;
        super.preganja(druga);
    }

    @Override
    public int steviloHranjenj() {
        return this.steviloHranjenj;
    }

    @Override
    public int steviloPreganjanj(Zival druga) {
        return this.steviloPreganjanj;
    }
}
