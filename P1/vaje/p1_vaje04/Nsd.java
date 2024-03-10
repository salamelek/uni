import java.util.Scanner;

public class Nsd {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int n = myScanner.nextInt();
        int g = myScanner.nextInt();

        for (int i=g; i<=n; i+=g) {
            for (int j=i; j<=n; j+=g) {
                System.out.printf("(%d, %d)%n", i, j);
            }
        }
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}