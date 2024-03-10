import java.util.Scanner;

class Kvadrati {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int a = myScanner.nextInt();
        int b = myScanner.nextInt();

        for (int i=a; i<=b; i++) {
            System.out.println(i * i);
        }
    }
}