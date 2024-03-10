import java.util.Scanner;

public class DN05_63230387 {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int n = myScanner.nextInt();

        // tabela za shranjevanje višine vsakega stolpca
        // velika je 3 stolpce več, tako da lahko sprejema tudi najširši kos
        int visinaVrstic[] = new int[2004];

        // tabela za višino kosov
        /*
        PAZI! sledeča tabela ne upošteva kje bo kos pristajal.
        To bom rabil samo za določitev višine stolpcev ko bom že vedel, kje mora pristati kos!
         */
        int visinaLikov[][] = {
                {1, 1, 1, 1},
                {4, 0, 0, 0},
                {2, 2, 0, 0},
                {1, 2, 1, 0},
                {2, 3, 0, 0},
                {2, 2, 2, 0},
                {3, 2, 0, 0},
                {3, 1, 0, 0},
                {1, 1, 2, 0},
                {3, 3, 0, 0},
                {2, 2, 2, 0},
                {1, 3, 0, 0},
                {2, 2, 2, 0},
                {3, 3, 0, 0},
                {2, 1, 1, 0},
                {2, 2, 1, 0},
                {2, 3, 0, 0},
                {1, 2, 0, 0},
                {3, 2, 0, 0}
        };

        for (int i=0; i<n; i++) {
            int l = myScanner.nextInt();
            int x = myScanner.nextInt() + 1000;

            // dobi y pristanka
            int y = getYPristanka(l, visinaVrstic, x);

            // preračunaj nove višine vrstic
            for (int j=0; j<4; j++) {
                if (visinaLikov[l][j] == 0) {
                    break;
                }

                // visina vrstic = in ne +=, ker moramo upoštevat viseče kose
                visinaVrstic[x + j] = y + visinaLikov[l][j];
            }
        }

        for (int i=0; i<visinaVrstic.length; i++) {
            if (visinaVrstic[i] > 0) {
                System.out.printf("%d: %d%n", i - 1000, visinaVrstic[i]);
            }
        }
    }

    public static int getYPristanka(int kos, int[] tabela, int xCoord) {
        // visino liko stejem od spodnjega kota levo, torej liki, ki so tam prazni bodo v pozicij -1 (?)

        int njizinaLikov[][] = {
                {0,  0,  0,  0},    // 0
                {0, -1, -1, -1},    // 1
                {0,  0, -1, -1},    // 2
                {0,  0,  0, -1},    // 3
                {1,  0, -1, -1},    // 4
                {1,  0,  1, -1},    // 5
                {0,  1, -1, -1},    // 6
                {0,  0, -1, -1},    // 7
                {0,  0,  0, -1},    // 8
                {2,  0, -1, -1},    // 9
                {0,  1,  1, -1},    // 10
                {0,  0, -1, -1},    // 11
                {1,  1,  0, -1},    // 12
                {1,  2, -1, -1},    // 13
                {0,  0,  0, -1},    // 14
                {1,  0,  0, -1},    // 15
                {0,  1, -1, -1},    // 16
                {0,  0,  1, -1},    // 17
                {1,  0, -1, -1}     // 18
        };

        // izračunaj stično točko
        int sticnaTocka = -5;
        for (int i=0; i<4; i++) {
            // nocemo upostevat -1
            if (njizinaLikov[kos][i] < 0) {
                break;
            }

            int razlika = tabela[xCoord + i] - njizinaLikov[kos][i];
            if (razlika > sticnaTocka) {
                sticnaTocka = razlika;
            }
        }

        return sticnaTocka;
    }
}