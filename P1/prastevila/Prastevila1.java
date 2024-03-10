// 1 ni praštevilo!
// rabi 21 sekund za 100000

import java.util.Scanner;

public class Prastevila1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int zgornjaMeja = sc.nextInt();

        for (int kandidat=2; kandidat <=zgornjaMeja; kandidat++) {
            // izračunaj št. deliteljev
            int stDeliteljev = 0;
            for (int i=1; i<=kandidat; i++) {
                if (kandidat % i == 0) {
                    stDeliteljev++;
                }
            }

            if (stDeliteljev == 2) {
                System.out.println(kandidat);
            }
        }
    }
}