import java.util.Scanner;

public class Zgoscenke {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int steviloZgoscenk = myScanner.nextInt();
        int maxPodatkov = myScanner.nextInt();

        int stevilkaZgoscenke = 1;
        int preostaliProstor = maxPodatkov;

        do {
            int velikostDatoteke = myScanner.nextInt();

            if (velikostDatoteke <= preostaliProstor) {
                preostaliProstor -= velikostDatoteke;
                int trenutniPodatki = maxPodatkov - preostaliProstor;
                System.out.printf("%d EP -> zgoscenka %d (%d EP)%n", velikostDatoteke, stevilkaZgoscenke, trenutniPodatki);
            } else {
                if (stevilkaZgoscenke == steviloZgoscenk) {
                    break;
                }
                stevilkaZgoscenke++;
                preostaliProstor = maxPodatkov;
                preostaliProstor -= velikostDatoteke;
                int trenutniPodatki = maxPodatkov - preostaliProstor;
                System.out.printf("%d EP -> zgoscenka %d (%d EP)%n", velikostDatoteke, stevilkaZgoscenke, trenutniPodatki);
            }
        } while (myScanner.hasNextInt());
    }
}

//4 700
//500
//200
//300