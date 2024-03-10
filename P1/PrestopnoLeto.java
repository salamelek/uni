import java.util.Scanner;

public class PrestopnoLeto {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int leto = myScanner.nextInt();
        boolean prestopno = false;

        if (leto % 400 == 0 || leto % 4 == 0 && leto % 100 != 0) {
            prestopno = true;
        }

        if (prestopno) {
            System.out.println("Leto je prestopno!");
        } else {
            System.out.println("Leto ni prestopno!");
        }
    }
}