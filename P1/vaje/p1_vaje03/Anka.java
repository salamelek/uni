import java.util.Scanner;

public class Anka {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        // prva stevilka liha
        int vecjaOd = myScanner.nextInt();
        int deljivaZ = myScanner.nextInt();
        int counter = 0;

        for (int a=1; a<=9; a+=2) {
            for (int b=vecjaOd + 1; b<=9; b++) {
                for (int c=0; c<=9; c += deljivaZ) {
                    System.out.printf("%d-%d-%d%n", a, b, c);
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}