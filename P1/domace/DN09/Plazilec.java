public class Plazilec extends Zival {
    private int steviloHranjenj;
    private int steviloPreganjanj;

    public Plazilec() {
        super();

        this.steviloHranjenj = 0;
        this.steviloPreganjanj = 0;
    }

    @Override
    public void seHrani() {
        super.seHrani();
        this.steviloHranjenj++;
        System.out.print("Plazilec reče nomnom");
        System.out.println(this.steviloHranjenj);
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
