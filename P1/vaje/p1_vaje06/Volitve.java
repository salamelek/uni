import java.util.Arrays;

public class Volitve {
    /*
    int[][][] je velikosti L * S * V , kjer je
        L število let,
        S število strank,
        V pa število volišč
    (velja L > 0, S > 0 in V > 0)
     */


    public static int steviloGlasov(int[][][] t, int leto, int stranka) {
        // Vrne skupno število glasov (preko vseh volišč), ki jih je v podanem letu prejela podana
        // stranka.
        int skupno = 0;

        for (int volisce=0; volisce<t[leto][stranka].length; volisce++) {
            skupno += t[leto][stranka][volisce];
        }

        return skupno;
    }

    public static int[][] glasovi(int[][][] t){
        // Za vsako leto in za vsako stranko izračuna število glasov, ki jih je stranka prejela v
        // tistem letu, in rezultate vrne v obliki tabele velikosti L * S.

        int[][] rezultat = new int[t.length][t[0].length];

        for (int leto=0; leto<t.length; leto++) {
            for (int stranka=0; stranka<t[leto].length; stranka++) {
                for (int volisce=0; volisce<t[leto][stranka].length; volisce++) {
                    rezultat[leto][stranka] += t[leto][stranka][volisce];
                }
            }
        }

        return rezultat;
    }

    public static int najVolisce(int[][][] t, int stranka){
        // Vrne indeks volišča, na katerem je podana stranka v skupnem seštevku preko vseh
        // let prejela največ glasov.Če je takih volišč več, naj metoda vrne prvo od njih.

        int[] volisca = new int[t[0][0].length];

        for (int volisce=0; volisce<t[0][0].length; volisce++) {
            for (int leto=0; leto<t.length; leto++) {
                volisca[volisce] += t[leto][stranka][volisce];
            }
        }

        int najVolisceIndex = 0;
        int najVolisce = 0;

        for (int i=0; i<volisca.length; i++) {
            if (najVolisce < volisca[i]) {
                najVolisce = volisca[i];
                najVolisceIndex = i;
            }
        }

        return najVolisceIndex;
    }

    public static int vsotaUvrstitev(int[][][] t, int stranka, int volisce){
        // Za podano stranko vrne vsoto uvrstitev, ki jih je v posameznih letih dosegla na
        // podanem volišču.Na primer, če je L = 3 in je stranka na podanem volišču v letu
        // 0 dosegla tretje mesto, v letu 1 četrto, v letu 2 pa prvo mesto, je rezultat enak
        // 3 + 4 + 1 = 8. Uvrstitev stranke v podanem letu na podanem volišču je enaka
        // (1 + n>), kjer je n> število strank, ki so v tistem letu na tistem volišču prejele strogo
        // več glasov.

        // izračunaj mesta za stranke vsako leto
        int sumUvrstitev = 0;
        for (int leto=0; leto<t.length; leto++) {
            // zadnje mesto je stevilo strank
            int mesto = t[0].length;

            for (int stranka2=0; stranka2<t[0].length; stranka2++) {
                if (t[leto][stranka2][volisce] <= t[leto][stranka][volisce] && stranka2 != stranka) {
                    mesto--;
                }
            }
//            System.out.println(mesto);
            sumUvrstitev += mesto;
        }

        // sum([mesto, mesto, mesto])

        return sumUvrstitev;
    }
}