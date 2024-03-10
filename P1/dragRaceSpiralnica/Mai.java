import java.util.Scanner;

public class Mai {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tipkovnica = sc.nextInt();
        int velikost = sc.nextInt();
        int stPremikov = sc.nextInt();

        int[] premiki = new int[stPremikov];

        for (int i = 0; i < stPremikov; i++)
            premiki[i] = sc.nextInt();

        int dolzina = doPremiki(tipkovnica, premiki, velikost);

        System.out.println(dolzina);
    }

    static int doPremiki(int tipkovnicaType, int[] premiki, int tipkovnicaVelikost) {
        int length = 0;

        switch (tipkovnicaType) {
            case 1:
                length = loopLine(premiki, tipkovnicaVelikost);
                break;
            case 2:
                length = loopSquare(premiki, tipkovnicaVelikost);
                break;
            case 3:
                length = loopPiramida(premiki, tipkovnicaVelikost);
                break;
            case 4:
                length = loopSpirala(premiki, tipkovnicaVelikost);
                break;
        }

        return length;

    }

    static int loopLine(int[] premiki, int tipkovnicaVelikost) {
        int length = 0;

        for (int i = 1; i < premiki.length; i++) {
            length += getDistanceLine(premiki[i - 1], premiki[i]);
        }

        return length;
    }

    static int loopSquare(int[] premiki, int tipkovnicaVelikost) {
        int length = 0;

        for (int i = 1; i < premiki.length; i++) {
            length += getDistance(getSquareTab(tipkovnicaVelikost), premiki[i - 1], premiki[i]);
        }

        return length;
    }

    static int loopPiramida(int[] premiki, int tipkovnicaVelikost) {
        int length = 0;

        for (int i = 1; i < premiki.length; i++) {
            length += getDistance(getPiramidaTab(tipkovnicaVelikost), premiki[i - 1], premiki[i]);
        }

        return length;
    }

    static int loopSpirala(int[] premiki, int tipkovnicaVelikost) {
        int length = 0;

        for (int i = 1; i < premiki.length; i++) {
            length += getDistance(getSipralaTab(tipkovnicaVelikost), premiki[i - 1], premiki[i]);
        }

        return length;
    }

    static int[][] getPiramidaTab(int size) {
        int[][] tab = new int[size][size * 2 - 1];
        int n = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (j >= size - i - 1 && j <= size + i - 1) {
                    tab[i][j] = n;
                    n++;
                } else
                    tab[i][j] = -1;
            }
        }
        return tab;
    }

    static int[][] getSquareTab(int size) {
        int[][] tab = new int[size][size];
        int n = 0;
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = n;
                n++;
            }
        }
        return tab;
    }

    static int getDistanceLine(int start, int end) {
        int distance = Math.abs(start - end);
        return distance;
    }

    static int getDistance(int[][] tab, int start, int end) {
        int[] startPos = getPos(tab, start);
        int[] endPos = getPos(tab, end);

        int distance = Math.abs(startPos[0] - endPos[0]) + Math.abs(startPos[1] - endPos[1]);
        return distance;
    }

    static int[] getPos(int[][] tab, int n) {
        int[] pos = new int[2];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] == n) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return pos;
    }

    static int[][] getSipralaTab(int velikost) {
        int[][] tab = new int[velikost][velikost];

        int x = 0;
        int y = 0;
        x = (velikost - 1) / 2;
        y = (velikost - 1) / 2;

        for (int n = 0; n < velikost * velikost;) {
            tab[y][x] = n;
            n++;

            // izpisiTab(tab);
            if (y + 1 < velikost && x == y - 1 && tab[y + 1][x] != 0 || n == 1) {
                if (n != 1)
                    y--;

                y--;
                x--;
                continue;
            }
            if (x >= y && x < velikost - y - 1) {
                x++;
            } else if (x > y && x >= velikost - y - 1) {
                y++;
            } else if (x <= y && x > velikost - y - 1) {
                x--;
            } else if (x < y && x <= velikost - y - 1) {
                y--;
            }

        }

        return tab;
    }

    static void izpisiTab(int[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                System.out.printf("%3d", tab[i][j]);
            }
            System.out.println();
        }
        System.out.println("");
    }

}
