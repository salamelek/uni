import java.util.Scanner;

public class Krog {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int r = myScanner.nextInt();
//        int r = 10;
        int treshold = (myScanner.nextInt() * r) / 2;

        // x² + y² = r²
        // * 1.7
        double multiplyBy = 2;

        // printaj " " razen ce si v krogu
        for (int y=0; y<2*r+1; y++) {
            for (int x=0; x<((2*r+1)*multiplyBy); x++) {
//                if (((x - r)*(x - r) + (y - r)*(y - r) > r*r - treshold) && ((x - r)*(x - r) + (y - r)*(y - r) < r*r + treshold)) {
                if ((x/multiplyBy - r)*(x/multiplyBy - r) + (y - r)*(y - r) < r*r + treshold && (x/multiplyBy - r)*(x/multiplyBy - r) + (y - r)*(y - r) > r*r - treshold) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}