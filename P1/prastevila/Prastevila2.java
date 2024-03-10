import java.util.Scanner;

public class Prastevila2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int zgornjaMeja = sc.nextInt();

        System.out.println(2);

        // preverimo samo liha števila
        for (int kandidat=3; kandidat <= zgornjaMeja; kandidat += 2) {
            // izračunaj št. deliteljev
            int stDeliteljev = 0;

            // preveri samo do korena kandidata
            int koren = (int) Math.ceil(Math.sqrt(kandidat));
            boolean jePrastevilo = true;
            for (int i=3; i<=koren; i += 2) {
                if (kandidat % i == 0) {
                    jePrastevilo = false;
                }
            }

            if (jePrastevilo) {
                System.out.println(kandidat);
            }
        }
    }
}