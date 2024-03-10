// To bo izpisalo eno tabelo

import java.util.Scanner;

public class Postevanka {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        System.out.println("-----------------");

        int maxLen = 0;
        int a = (n * n);

        while (a > 0) {
            a -= a % 10;
            a /= 10;
            maxLen += 1;
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (j < i) {
                    for (int k=0; k<maxLen + 1; k++) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(i * j);

                    for (int k=0; k<(maxLen - numLen(i * j) + 1); k++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println();
        }
    }

    static int numLen(int num) {
        int len = 0;

        while (num > 0) {
            num -= num % 10;
            num /= 10;
            len += 1;
        }

        return len;
    }
}
