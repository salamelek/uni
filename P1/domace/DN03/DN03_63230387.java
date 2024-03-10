import java.util.Scanner;

public class DN03_63230387 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        long h = myScanner.nextInt();
        long w = myScanner.nextInt();
        int k = myScanner.nextInt();

        long ploscaMax;

        switch (k) {
            case 0:
                ploscaMax = 1;
                break;
            default:
                ploscaMax = (long) 2 << k - 1;
                break;
        }

        long stPlosc = 0;
        long stPlosc1 = w * h;

        for (long i=2; i<=ploscaMax; i *= 2) {
            long stPlosc2 = (w / i) * (h / i);
            long razlika = stPlosc1 - (stPlosc2 * 4);

            stPlosc += razlika;
            stPlosc1 = stPlosc2;
        }

        stPlosc += (w / ploscaMax) * (h / ploscaMax);

        System.out.println(stPlosc);
    }
}