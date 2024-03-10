import java.util.Scanner;
import java.util.*;

public class Program1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Vnesite prvo število: ");
        int a = sc.nextInt();

        sc.nextLine();

        System.out.print("\nIzberite operacijo računanja: ");
        String op = sc.nextLine();

        System.out.println("\nVnesite drugo število:");

        int b = sc.nextInt();

        Integer r = null;

        char[] opr = op.toCharArray();

        char o = opr[0];

        if(o == '+'){
            r = a + b;

        } else if(o == '-'){
            r = a - b;

        } else if(o == '*'){
            r = a * b;

        } else if(o == '/'){
            r = a / b;

        } else{
            System.out.println("Error during imput.");

        } if(r != null){
            System.out.println("Rezultat je: " + r + ".");
        }
    }
}