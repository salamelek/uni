import java.util.Scanner;

public class Smrekce {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int visina = myScanner.nextInt();

        for (int i=0; i<visina; i++) {
            pir(i + 1, visina - i);
        }
    }

    public static void pir(int visina, int zamik) {
        for (int i=0; i<visina; i++) {
            print(zamik, ' ');
            print(visina - i - 1, ' ');
            print(2*i + 1, '*');
            System.out.println();
        }
    }

    public static void print(int n, char znak) {
        for (int i=0; i<n; i++) {
            System.out.printf("%c ", znak);
        }
    }
}