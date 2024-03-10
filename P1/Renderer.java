import java.util.Scanner;

public class Renderer {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int width = 70;
        int height = 35;

        printScreen(width, height);
    }

    public static void printLine(int n, char znak) {
        System.out.print("|");

        for (int i=0; i<n; i++) {
            System.out.printf("%c ", znak);
        }

        System.out.println("|");
    }

    public static void printScreen(int w, int h) {
        printLine(w, '=');

        for (int line=0; line<h; line++) {
            printLine(w, '.');
        }

        printLine(w, '=');
    }
}