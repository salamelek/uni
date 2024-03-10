import java.util.Scanner;

public class Collatzovo {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int a = myScanner.nextInt();
        int b = myScanner.nextInt();

        int izbranoStevilo = 0;
        int dolzinaZaporedja = 0;

        for (int i=a; i<=b; i++) {
            int trenutnaDolzina = dolzinaZaporedja(i);
            if (trenutnaDolzina > dolzinaZaporedja) {
                dolzinaZaporedja = trenutnaDolzina;
                izbranoStevilo = i;
            }
        }

//        System.out.printf("Najdaljse zaporedje %d-ih številk je s številom %d.%n", dolzinaZaporedja, izbranoStevilo);
        System.out.println(izbranoStevilo);
        System.out.println(dolzinaZaporedja);
    }

    public static int dolzinaZaporedja(int number) {
        int counter = 1;

        while (number != 1) {
//            System.out.printf("number: %d%n", number);
            counter++;
            if (number % 2 == 0) {
                number /= 2;
            } else {
                number = number * 3 + 1;
            }
        }

//        System.out.println("----------");

        return counter;
    }
}