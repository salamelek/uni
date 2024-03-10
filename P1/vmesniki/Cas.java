public class Cas implements Comparable<Cas> {
    @Override
    public int compareTo(Cas drugi) {
        /*
        tukaj primerjamo cas this in drugi

        metoda je int, ker vrne:
            0 če sta časa enakovredna
            pozitivno št če je drugi pred this
            negativno št če je this pred drugi

        (nevem ce je res) mislim da to redefinira tudi metodo .equals()
         */
        return 0;
    }
}
