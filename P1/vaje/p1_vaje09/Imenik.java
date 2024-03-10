import java.util.Arrays;

public class Imenik extends Datoteka {
    private Datoteka datoteke[];

    public Imenik(String ime, Datoteka[] datoteke) {
        super(ime);

        this.datoteke = datoteke;
    }

    @Override
    public int velikost() {
        int totVelikost = 0;
        for (int i=0; i<datoteke.length; i++) {
            totVelikost += datoteke[i].velikost();
        }

        return totVelikost + 256;
    }

    @Override
    public String podatki() {
        return String.format("i %d", this.datoteke.length);
    }

    public int steviloVecjihSlik(int prag) {
        int counter = 0;

        for (int i=0; i<this.datoteke.length; i++) {
            Datoteka dat = this.datoteke[i];

            // nas ne brigajo datoteke, ki niso slike
            if (!(dat instanceof SlikovnaDatoteka)) {
                continue;
            }

            // preverimo stranice s typecast
            SlikovnaDatoteka img = (SlikovnaDatoteka) dat;
            if (img.jeVelikaVsaj(prag)) {
                counter++;
            }
        }

        return counter;
    }

    public String poisci(String ime) {
        return poisci(".", ime);
    }

    public String poisci(String pot, String ime) {
        // za vsako datoteko v imeniku
        for (int i=0; i<this.datoteke.length; i++) {
            Datoteka dat = this.datoteke[i];
            String enPath = String.format("%s/%s", pot, dat.vrniIme());

            if (dat.vrniIme().equals(ime)) {
                return enPath;
            }

            if (dat instanceof Imenik) {
                Imenik podImenik = ((Imenik) dat);
                String path = podImenik.poisci(enPath, ime);
                if (path != null) {
                    return path;
                }
            }
        }

        return null;
    }

    public void prikaziDrevo() {
        System.out.println(this.drevo("koren", 1, new char[32]));
    }

    public String vrniZacetekVrstice(int nivo, String spacer, char startChars[]) {
        String prefix = "";

        for (int i=0; i<nivo - 1; i++) {
            prefix = String.format("%s%c   ", prefix, startChars[i]);
        }

        prefix = String.format("%s%s", prefix, spacer);

        return prefix;
    }

    public String drevo(String drevo, int nivo, char startChars[]) {
        int dolzinaImenika = this.datoteke.length;

        for (int i=0; i<dolzinaImenika; i++) {
            Datoteka dat = this.datoteke[i];
            String imeDat = dat.vrniIme();
            String podatkiDat = dat.toString();

            String spacer = "|-- ";

            if (i + 1 == dolzinaImenika) {
                spacer = "\\-- ";
            }

            if (dat instanceof Imenik) {
                drevo = String.format("%s%n%s%s", drevo, vrniZacetekVrstice(nivo, spacer, startChars), imeDat);

                if (i + 1 < dolzinaImenika) {
                    startChars[nivo - 1] = '|';
                }

                drevo = String.format("%s%s", drevo, ((Imenik) dat).drevo("", nivo + 1, startChars));

                startChars[nivo - 1] = ' ';
            } else {
                drevo = String.format("%s%n%s%s", drevo, vrniZacetekVrstice(nivo, spacer, startChars), podatkiDat);
            }
        }

        return drevo;
    }
}