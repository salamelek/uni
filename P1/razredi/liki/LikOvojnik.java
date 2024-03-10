public class LikOvojnik<T extends Lik> {
    private T a;
    public LikOvojnik(T a) {
        this.a = a;
    }

    public T vrni() {
        return this.a;
    }

    public boolean imaVecjoPloscinoKot(LikOvojnik<T> drugi) {
        return this.a.ploscina() > drugi.a.ploscina();
    }
}