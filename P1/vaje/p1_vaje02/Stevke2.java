import java.util.Scanner;

public class Stevke2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Vnesi število: ");

        long a = sc.nextLong();
        long b = 0;
        int i = 0;

        while (a > 0) {
            int zadnjaStevka = (int) (a % 10);

            b = b * 10 + zadnjaStevka;
            a = (a - zadnjaStevka) / 10;

            i++;
        }

        while (b > 0) {
            System.out.println(b % 10);
            b = (b - b % 10) / 10;
        }
    }
}