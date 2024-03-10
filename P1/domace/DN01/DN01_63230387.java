import java.util.Scanner;

public class DN01_63230387 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        // subtract 1 since we're working with points and not lengths (and i don't like that)
        int a = myScanner.nextInt() - 1;
        int b = myScanner.nextInt() - 1;

        // Find the shortest side s
        int s = a;

        if (a > b) {
            s = b;
        }

        // calculate the number of squares
        int numSquares = 0;

        for (int i=0; i<s; i++) {
            numSquares += ((a - i) * (b - i));
        }

        System.out.println(numSquares);
    }
}