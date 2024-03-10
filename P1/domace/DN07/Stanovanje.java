import java.util.Arrays;

public class Stanovanje {
    private Oseba[] stanovalci;
    private Stanovanje[] sosednaStanovanja;

    public Stanovanje(Oseba[] stanovalci) {
        this.stanovalci = stanovalci;
        this.sosednaStanovanja = new Stanovanje[4];
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

    public Oseba[] sosedjeSosedov() {
        System.out.println(Arrays.deepToString(vrni2DTabelo()));

        return null;
    }

    public Stanovanje[][] dodajStanovanjeV2DTabelo() {
        
    }

    public Stanovanje[][] vrni2DTabelo() {
        Stanovanje mapa[][] = new Stanovanje[][] {{this}};

        for (int smer=0; smer<4; smer++) {

        }

        return mapa;
    }
}