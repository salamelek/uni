public class Knjiznica {
    private int stClanov;
    private int stNaslovov;
    private int stIzvodovNaNaslov;
    private int nasloviNaVoljo[];
    private boolean nasloviClana[][];
    private int kolikoKratIzposojena[];

    public Knjiznica(int stClanov, int stNaslovov, int stIzvodovNaNaslov) {
        this.stClanov = stClanov;
        this.stNaslovov = stNaslovov;
        this.stIzvodovNaNaslov = stIzvodovNaNaslov;

        this.nasloviNaVoljo = new int[stNaslovov];
        this.nasloviClana = new boolean[stClanov][stNaslovov];
        this.kolikoKratIzposojena = new int[stNaslovov];
    }

    public boolean posodi(int clan, int naslov) {
        // preveri ce je dovolj izvoov

        if (this.nasloviNaVoljo[naslov] <= -this.stIzvodovNaNaslov) {
            return false;
        }

        // preveri če član ima že ta naslov
        if (this.nasloviClana[clan][naslov]) {
            return false;
        }

        // napisi naslov v tabelo
        this.nasloviClana[clan][naslov] = true;

        // zapisi v stats
        this.kolikoKratIzposojena[naslov] += 1;

        // odstej od naslovov na voljo
        this.nasloviNaVoljo[naslov] -= 1;

        return true;
    }

    public void clanVrne(int clan) {
        for (int i=0; i<this.stNaslovov; i++) {
            if (this.nasloviClana[clan][i]) {
                this.nasloviNaVoljo[i] += 1;
                this.nasloviClana[clan][i] = false;
            }
        }
    }

    public int posojeni(int naslov) {
        return this.stIzvodovNaNaslov - this.nasloviNaVoljo[naslov] - this.stIzvodovNaNaslov;
    }

    public int priClanu(int clan) {
        int counter = 0;

        for (int i=0; i<this.stNaslovov; i++) {
            if (this.nasloviClana[clan][i]) {
                counter++;
            }
        }

        return counter;
    }

    public int najNaslov() {
        int stIzposojenih = 0;
        int najNaslov = 0;

        for (int i=0; i<this.stNaslovov; i++) {
            if (this.kolikoKratIzposojena[i] > stIzposojenih) {
                stIzposojenih = this.kolikoKratIzposojena[i];
                najNaslov = i;
            }
        }

        return najNaslov;
    }
}