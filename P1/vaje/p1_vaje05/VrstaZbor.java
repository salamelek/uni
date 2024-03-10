import java.util.Scanner;

public class VrstaZbor {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int n = myScanner.nextInt();
        int vojaki[] = new int[n];

        for (int i=0; i<n; i++) {
            vojaki[i] = myScanner.nextInt();
        }

        int counter = 0;
        for (int i=0; i<n; i++) {
            if (i == 0) {
                if (vojaki[i] <= vojaki[i + 1]) {
                    System.out.println(i);
                    counter++;
                }
                continue;
            }

            else if (i == n - 1) {
                if (vojaki[i - 1] <= vojaki[i]) {
                    System.out.println(i);
                    counter++;
                }
                continue;
            }

            else if (vojaki[i - 1] <= vojaki[i] && vojaki[i] <= vojaki[i + 1]) {
                System.out.println(i);
                counter++;
            }
        }

        if (counter == 0) {
            System.out.println("NOBEDEN");
        }
    }
}