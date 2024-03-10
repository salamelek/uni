public class GenIskanje {
//    System.out.println(poisci());
//    System.out.println(poisci());
//    System.out.println(poisci());
//
//    System.out.println(poisci());

    int[][] t2 = {{1, 2}, {2, 4}, {3, 5}};
    System.out.println(Iskanje.poisci(t2, new int[] {7, 8}));

    public static <T> int poisci(T[] tabela, T iskani) {
        for (int i=0; i<tabela.length; i++) {
            if (tabela[i].equals(iskani)) {
                return i;
            }
        }
    }
}