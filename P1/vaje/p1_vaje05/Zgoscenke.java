import java.util.Scanner;
import java.util.Arrays;

public class Zgoscenke {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int n = myScanner.nextInt();
        int k = myScanner.nextInt();

        int zgoscenke[] = new int[n];

        while (myScanner.hasNextInt()) {
            int velikost = myScanner.nextInt();
            int najProst = stZgoscenke(zgoscenke, k, velikost);

            if (najProst == -1) {
                break;
            }

            zgoscenke[najProst] += velikost;

            System.out.printf("%d EP -> zgoscenka %d %s%n", velikost, najProst + 1, Arrays.toString(zgoscenke));
        }
    }

    public static int stZgoscenke(int[] zgoscenke, int maxVelikost, int velikost) {
        int najboljProstorna = 0;
        double najmanjsaDat = 10e9;

        for (int i=0; i<zgoscenke.length; i++) {
            if (zgoscenke[i] < najmanjsaDat) {
                najboljProstorna = i;
                najmanjsaDat = zgoscenke[i];
            }
        }

        if (zgoscenke[najboljProstorna] + velikost <= maxVelikost) {
            return najboljProstorna;
        } else {
            return -1;
        }

    }
}