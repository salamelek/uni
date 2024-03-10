import java.util.Scanner;

public class DN04_63230387 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int n = myScanner.nextInt();
        int k = myScanner.nextInt();

        // ustvarimo tabelo kjer bomo šteli koliko krat se število n ponovi.
        // število števil n dobimo na indexu n-1
        int[] stStevil = new int[1000];

        // zapišisi koliko vsakih števil je
        for (int i=0; i<n; i++) {
            stStevil[myScanner.nextInt() - 1] += 1;
        }

        // če je k > 1001, 1 ne bo imel komplementarnega števila, ker 1 + 1000 (max števila) = 1001
        // zatorej, če je k > 1001 ni treba da upoštevamo vse i, ki so manjši od k - 1000
        int startnaTocka = 1;
        if (k > 1001) {
            startnaTocka = k - 1000;
        }

        long stParov = 0;

        for (int i=startnaTocka; i<=k/2; i++) {
            int stSedParov = (stStevil[i - 1] * stStevil[k - i - 1]);

            // vse rešitve so dvojne, razen pari ki so sestavljeni iz dvakrat istega števila (tj. iz števila, ki je enak k/2)
            // ni pa mi všeč, da to preverjamo za vsak par. Idealno bi bilo to upoštevati kasneje...
            // vsekakor pa mi ni uspelo skuhati nič boljšega, torej bom jedel kar to... Dober tek!
            if (i != (double) k / 2) {
                stSedParov *= 2;
            }

            stParov += stSedParov;
        }

        System.out.println(stParov);
    }
}