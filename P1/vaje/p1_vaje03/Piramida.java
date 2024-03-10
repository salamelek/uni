import java.util.Scanner;

public class Piramida {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int visina = myScanner.nextInt();

        for (int i=0; i<visina; i++) {
            for (int j=0; j<visina - 1 - i; j++) {
                System.out.print(" ");
            }
            for (int k=0; k<(2 * i + 1) ; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}