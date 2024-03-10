// To bo izpisalo eno tabelo

import java.util.Scanner;

public class PostevankaZares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println("-----------------");

        // dobi max dolzino števila
        int maxLen = getMaxLen(n * n);

        // dobi string format z željeno dolžino
        String f = String.format("%%%dd", maxLen + 1);

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=i-1; j++) {
                for (int k=0; k <maxLen + 1; k++) {
                    System.out.print(" ");
                }
            }

            for (int j=i; j<=n; j++) {
                System.out.printf(f, (i * j));
            }
            System.out.println();
        }
    }

    static int getMaxLen(int n) {
        int len = 0;

        while (n > 0) {
            n -= n % 10;
            n /= 10;
            len += 1;
        }

        return len;
    }
}