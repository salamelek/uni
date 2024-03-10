import java.util.Arrays;

public class Stanovanje {
    private Oseba stanovalci[];
    private Stanovanje sosednaStanovanja[];
    private int[] dolzineVSmeri;

    public Stanovanje(Oseba[] stanovalci) {
        this.stanovalci = stanovalci;
        this.sosednaStanovanja = new Stanovanje[4];
        this.dolzineVSmeri = new int[4];
    }

    public int steviloStanovalcev() {
        return this.stanovalci.length;
    }

    public int steviloStarejsihOd(Oseba os) {
        int stStarejsihOd = 0;

        for (Oseba oseba: this.stanovalci) {
            if (oseba.jeStarejsaOd(os)) {
                stStarejsihOd++;
            }
        }

        return stStarejsihOd;
    }

    public int[] mz() {
        int mz[] = new int[2];

        for (Oseba oseba: this.stanovalci) {
            if (oseba.vrniSpol() == 'M') {
                mz[0]++;
            } else {
                mz[1]++;
            }
        }

        return mz;
    }

    public Oseba starosta() {
        Oseba najstarejsaOseba = null;
        int najStarost = 0;

        for (Oseba oseba: this.stanovalci) {
            if (oseba.vrniStarost() > najStarost) {
                najstarejsaOseba = oseba;
                najStarost = oseba.vrniStarost();
            }
        }

        return najstarejsaOseba;
    }

    public void nastaviSosede(Stanovanje levi, Stanovanje zgornji, Stanovanje desni, Stanovanje spodnji) {
        this.sosednaStanovanja[0] = levi;
        this.sosednaStanovanja[1] = zgornji;
        this.sosednaStanovanja[2] = desni;
        this.sosednaStanovanja[3] = spodnji;
    }

    public Oseba starostaSosescine() {
        // kako lepo, da se ni treba ubadati z rekurzijo :D
        Oseba najstarejsaOseba = null;
        int najStarost = 0;

        najstarejsaOseba = this.starosta();
        najStarost = najstarejsaOseba.vrniStarost();

        for (int i=0; i<4; i++) {
            if (this.sosednaStanovanja[i] == null) {
                continue;
            }

            najstarejsaOseba = this.sosednaStanovanja[i].starosta();
            najStarost = najstarejsaOseba.vrniStarost();
        }

        return najstarejsaOseba;
    }

    // metoda je static, ker ne uporablja this..
    public static boolean zeVSeznamu(Stanovanje stanovanje, Stanovanje[] stanovanja) {
        // ce seznama ni ali seznam je prazen
        if (stanovanja == null || stanovanja.length == 0) {
            return false;
        }

        for (Stanovanje stanVSeznamu: stanovanja) {
            if (stanVSeznamu == null && stanovanje != null) {
                continue;
            }

            if (stanVSeznamu == null && stanovanje == null) {
                return true;
            }

            if (stanVSeznamu.equals(stanovanje)) {
                return true;
            }
        }

        return false;
    }

    public Oseba[] sosedjeSosedov() {
        // najprej damo v seznam vsa Stanovanja, ki so sosedja sosedov
        Stanovanje[] stanovanjaSosSos = new Stanovanje[9]; // max sosSos je 9

        // za vsak sosed
        int stanovanjaCounter = 0;
        for (Stanovanje sosednoStanovanje: this.sosednaStanovanja) {
            if (sosednoStanovanje == null) {
                continue;
            }

            for (Stanovanje sosSosStanovanje: sosednoStanovanje.sosednaStanovanja) {
                if (sosSosStanovanje == null) {
                    continue;
                }

                // hočemo samo enkrat upoštevati vsak sosed
                if (zeVSeznamu(sosSosStanovanje, stanovanjaSosSos)) {
                    continue;
                }

                // nočemo upoštevati this-a
                // tokrat pa uporabim hashcode
                if (sosSosStanovanje.hashCode() == this.hashCode()) {
                    continue;
                }

                stanovanjaSosSos[stanovanjaCounter] = sosSosStanovanje;
                stanovanjaCounter++;
            }
        }

        // zdaj pa izračunajmo koliko oseb je v stanovanjih
        int stSosedovSosedov = 0;

        for (Stanovanje sosSosStanovanje: stanovanjaSosSos) {
            if (sosSosStanovanje == null) {
                continue;
            }

            stSosedovSosedov += sosSosStanovanje.steviloStanovalcev();
        }

        Oseba[] sosedjeSosedov = new Oseba[stSosedovSosedov];

        // in še postvavimo vse stanovalce v seznam
        int sosedjeCounter = 0;
        for (Stanovanje sosSosStanovanje: stanovanjaSosSos) {
            if (sosSosStanovanje == null) {
                continue;
            }

            for (Oseba sosedSoseda: sosSosStanovanje.stanovalci) {
                sosedjeSosedov[sosedjeCounter] = sosedSoseda;
                sosedjeCounter++;
            }
        }

        return sosedjeSosedov;
    }

    public static Stanovanje[] dodajVSeznam(Stanovanje[] stanovanja, Stanovanje stanovanje) {
        int dolzinaStaregaSeznama = stanovanja.length;
        Stanovanje novSeznam[] = new Stanovanje[dolzinaStaregaSeznama + 1];

        for (int i=0; i<dolzinaStaregaSeznama; i++) {
            novSeznam[i] = stanovanja[i];
        }

        novSeznam[dolzinaStaregaSeznama] = stanovanje;

        return novSeznam;
    }

    // res mi ni všeč, a hkrati nočem rabit arraylist... nismo še predelali takih stvari :(
    public static Stanovanje[] dodajVSeznamCeNiZe(Stanovanje[] stanovanja, Stanovanje stanovanje) {
        if (zeVSeznamu(stanovanje, stanovanja)) {
            return stanovanja;
        }

        return dodajVSeznam(stanovanja, stanovanje);
    }

    public Stanovanje[] vrniStanovanjaVBloku() {
        // treba bo rabiti rekurzijo D:
        Stanovanje umazanaTabela[] = rekurzVrniStanovanja(new Stanovanje[0]);

        int counter = 0;
        for (Stanovanje s: umazanaTabela) {
            if (s == null) {
                continue;
            }

            counter++;
        }

        Stanovanje cistaTabela[] = new Stanovanje[counter];

        int counter2 = 0;
        for (int i=0; i<umazanaTabela.length; i++) {
            if (umazanaTabela[i] == null) {
                continue;
            }

            cistaTabela[counter2] = umazanaTabela[i];
            counter2++;
        }

        return cistaTabela;
    }

    public static Stanovanje[] sestejSeznameCeNiZe(Stanovanje[] s1, Stanovanje[] s2) {
        int skupnaDolzina = s1.length + s2.length;
        Stanovanje[] novSeznam = new Stanovanje[skupnaDolzina];

        for (int i=0; i<s1.length; i++) {
            novSeznam[i] = s1[i];
        }

        for (int j=s1.length; j<skupnaDolzina; j++) {
            if (s2[j - s1.length] == null) {
                continue;
            }

            if (zeVSeznamu(s2[j - s1.length], novSeznam)) {
                continue;
            }

            novSeznam[j] = s2[j - s1.length];
        }

        return novSeznam;
    }

    public Stanovanje[] rekurzVrniStanovanja(Stanovanje[] stanovanja) {
        // dodaj samega sebe v seznam
        stanovanja = dodajVSeznamCeNiZe(stanovanja, this);

        // za vsako stanovanje sosedno this-u
        for (Stanovanje bliznjeStanovanje: this.sosednaStanovanja) {
            // nas ne zanimajo ne obstoječi sosedje
            if (bliznjeStanovanje == null) {
                continue;
            }

            // nocemo spet gledat tiste ki smo ze
            if (zeVSeznamu(bliznjeStanovanje, stanovanja)) {
                continue;
            }

            stanovanja = sestejSeznameCeNiZe(stanovanja, bliznjeStanovanje.rekurzVrniStanovanja(stanovanja));
        }

        return stanovanja;
    }

    public boolean soSeSosedje(Stanovanje[] sosedje) {
        if (sosedje == null) {
            return false;
        }

        for (Stanovanje s: sosedje) {
            if (s != null) {
                return true;
            }
        }

        return false;
    }

    public Stanovanje[][] dodajObroc(Stanovanje obroci[][], Stanovanje[] obroc) {
        int dolzinaStaregaSeznama = obroci.length;
        Stanovanje noviObroci[][] = new Stanovanje[dolzinaStaregaSeznama + 1][(dolzinaStaregaSeznama) * 4];

        for (int i=0; i<dolzinaStaregaSeznama; i++) {
            noviObroci[i] = obroci[i];
        }

        noviObroci[dolzinaStaregaSeznama] = obroc;

        return noviObroci;
    }

    public boolean smerJeVRedu(int smer, double cetrtina) {
        if (cetrtina == 0 && smer == 2) {
            return false;
        }

        if ((cetrtina > 0 && cetrtina < 1) && (smer == 2 || smer == 3)) {
            return false;
        }


        if (cetrtina == 1 && smer == 3) {
            return false;
        }

        if ((cetrtina > 1 && cetrtina < 2) && (smer == 0 || smer == 3)) {
            return false;
        }


        if (cetrtina == 2 && smer == 0) {
            return false;
        }

        if ((cetrtina > 2 && cetrtina < 3) && (smer == 1 || smer == 0)) {
            return false;
        }


        if (cetrtina == 3 && smer == 1) {
            return false;
        }

        if ((cetrtina > 3) && (smer == 1 || smer == 2)) {
            return false;
        }

        return true;
    }

    public Stanovanje[] dobiNaslednjiObroc(Stanovanje[] stariObroc) {
//        System.out.printf("%nDodajmo en obroč...%nstari obroč: %s%n", Arrays.toString(stariObroc));


        int stStaregaObroca = stariObroc.length / 4;
        Stanovanje[] novObroc = new Stanovanje[0];

        for (int i=0; i<stariObroc.length; i++) {
            Stanovanje staroStanovanje = stariObroc[i];

            if (staroStanovanje == null) {
                continue;
            }

            double cetrtina = (double) i / stStaregaObroca;

            for (int smer=0; smer<4; smer++) {
                novObroc = dodajVSeznam(novObroc, staroStanovanje.sosednaStanovanja[smer]);
            }
        }

//        System.out.printf("nov obroc: %s%n", Arrays.toString(novObroc));

        return novObroc;
    }

    public void razporeditev() {
        // štartamo iz izvirnega stanovanja in pregledamo sosede.
        // si jih zapisemo
        // pogledamo vse sosede sosedov in si jih zapisemo
        // sosede sosedov sosedov -||-
        // itd dokler ni vec sosedov

        // ta tabela bo ržala obroče stanovanj:
        // index 0: središče
        // index 1: sodedje središča, ....
        // obročje imajo sledečo število stanovanj: 1, 4, 8, 12, 16, ... torej +4
        Stanovanje obroci[][] = {{this}};
        Stanovanje[] obroc = this.sosednaStanovanja;

        while (soSeSosedje(obroc)) {
            obroci = dodajObroc(obroci, obroc);
            obroc = dobiNaslednjiObroc(obroc);
            break;
        }

//        for (int a=0; a<obroci.length; a++) {
//            System.out.printf("obroc %d: %s%n", a, Arrays.toString(obroci[a]));
//        }
    }
}
