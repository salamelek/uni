import java.util.Scanner;

public class Romanje {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int dolzinaPoti = myScanner.nextInt();
        int prehojeneEnote = myScanner.nextInt();
        int dekrementEnot = myScanner.nextInt();

        int i = 1;

        while (dolzinaPoti > 0 && prehojeneEnote > 0) {
            System.out.println(i + ": " + dolzinaPoti + " -> " + Math.max((dolzinaPoti - prehojeneEnote), 0));

            dolzinaPoti -= prehojeneEnote;
            prehojeneEnote -= dekrementEnot;

            i++;
        }
    }
}