import java.util.Scanner;
import java.lang.Math;

public class Tipkovnica {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int oblikaTipkovnice = myScanner.nextInt();
        int velikostTipkovnice = myScanner.nextInt();
        int dolzinaZaporedja = myScanner.nextInt();

        int razdalja = 0;
        int tipka1 = myScanner.nextInt();

        for (int i = 0; i < dolzinaZaporedja - 1; i++) {
            int tipka2 = myScanner.nextInt();

            switch (oblikaTipkovnice) {
                case 1:
                    razdalja += Math.abs(tipka1 - tipka2);
                    break;
                case 2:
                    razdalja += razdaljaKvadratnica(tipka1, tipka2, velikostTipkovnice);
                    break;
                case 3:
                    razdalja += razdaljaPiramidnica(tipka1, tipka2);
                    break;
                case 4:
                    razdalja += razdaljaSpiralnica(tipka1, tipka2);
                    break;
                default:
                    System.out.println("To se sploh ne bi smelo zgoditi! Svet bo padel!");
                    return;
            }

            tipka1 = tipka2;
        }
        System.out.println(razdalja);
    }

    public static int razdaljaKvadratnica(int tipka1, int tipka2, int velikost) {
        // morda je lažje če računam s koordinatami
        int x1 = tipka1 / velikost;     // seveda bo rezultat int, ker delim dva int
        int y1 = tipka1 % velikost;

        int x2 = tipka2 / velikost;
        int y2 = tipka2 % velikost;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static int razdaljaPiramidnica(int tipka1, int tipka2) {
        /*
        tukaj pa bom moral razbrati na katerem "nadstropju" sem in koliko oddlajen od centra (+-)
        "konica" piramide je nadstropje 0

        nadstropje je celi del korena števila
        oddaljenost od sredine dobimo kot razliko tipke in središčne vrednosti nadstropja
         */

        int nadstropje1 = (int) Math.sqrt(tipka1);
        int oddaljenost1 = tipka1 - (nadstropje1 * (nadstropje1 + 1));

        int nadstropje2 = (int) Math.sqrt(tipka2);
        int oddaljenost2 = tipka2 - (nadstropje2 * (nadstropje2 + 1));

        return Math.abs(nadstropje1 - nadstropje2) + Math.abs(oddaljenost1 - oddaljenost2);
    }

    public static int razdaljaSpiralnica(int tipka1, int tipka2) {
        /*
        Tudi tukaj bomo rabili en pristop s x in y (oboje +-)
        Za x štejemo razdaljo od sredine (vodoravne), za y pa od sredine (vertikalne)

        obroč 0 je 0-0, obroč 1 je 1-8, ...
        int stObroc1 = (int) Math.sqrt(tipka1) - ((int) Math.sqrt(tipka1) / 2);

        x=0 nekega obroča lahko dobimo tako:
        (n-to liho število)² + n
        npr. dobimo x=0 za število 46:
            1) dobimo število obroča k, v katerem je 46
            2) dobimo n-to liho število za tisti obroč: n = k * 2 - 1       <-- ne dela za k=0, ampak je dovolj da sprejmemo le pozitivne rezultate
            3) zgornji x=0 za k-ti obroč je n*n + k         (x01)
               spodnji x=0 za k-ti obroč je n*n + k + k*4   (x02)

        Tako pa dobimo x in y:
            x = min(min(tipka - x01, k), max(x02 - tipka, -k))
            y = min(max(min(y01 - tipka, k), max(tipka - y02), -k)), 3)
        */

        // izračunamo x1
        int stObroc1 = (int) Math.sqrt(tipka1) - ((int) Math.sqrt(tipka1) / 2);
        int n1 = Math.max((stObroc1 * 2 - 1), 0);   // za obroč 0 je n tudi 0, ne -1

        // računanje dodatnih spremenljivk
        int x11 = n1 * n1 + stObroc1;
        int x12 = x11 + 4 * stObroc1;
        int x1 = Math.min(Math.min(tipka1 - x11, stObroc1), Math.max(x12 - tipka1, -stObroc1));

        // izračunamo y1
        int y11 = n1 * n1 + stObroc1 + n1 + 1;
        int y12 = y11 + 4 * stObroc1;
        int y1 = Math.min(Math.max(Math.min(y11 - tipka1, stObroc1), Math.max(tipka1 - y12, -stObroc1)), stObroc1);


        // izračunamo x2
        int stObroc2 = (int) Math.sqrt(tipka2) - ((int) Math.sqrt(tipka2) / 2);
        int n2 = Math.max((stObroc2 * 2 - 1), 0);   // za obroč 0 je n tudi 0, ne -1

        // računanje dodatnih spremenljivk
        int x21 = n2 * n2 + stObroc2;
        int x22 = n2 * n2 + stObroc2 * 5;
        int x2 = Math.min(Math.min(tipka2 - x21, stObroc2), Math.max(x22 - tipka2, -stObroc2));

        // izračunamo y2
        int y21 = n2 * n2 + stObroc2 + n2 + 1;
        //region
        int y22 = y21 + 4 * stObroc2;
        //endregion
        int y2 = Math.min(Math.max(Math.min(y21 - tipka2, stObroc2), Math.max(tipka2 - y22, -stObroc2)), stObroc2);

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}