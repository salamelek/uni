public class GenOvojnik<T> {
    private T a;
    public GenOvojnik(T a) {
        this.a = a;
    }

    public T vrni() {
        return this.a;
    }
}