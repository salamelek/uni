import java.util.Scanner;

class Stevke {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        long a = myScanner.nextLong();

        while (a > 0) {
            System.out.println(a % 10);
            a -= a % 10;
            a /= 10;
        }
    }
}