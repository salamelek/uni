public class Par <T, U> implements Comparable<Par<T, U>> {
    private T a;
    private U b;

    public Par(T a, U b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Par drugi) {
        return 0;
    }

    public T vrniA() {
        return this.a;
    }

    public U vrnib() {
        return this.b;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.a, this.b);
    }

    @Override
    public boolean equals(Object drugi) {
        if (drugi == null) {
            return false;
        }

        if (!(drugi instanceof Par)) {
            return false;
        }

        Par<?, ?> drugiPar = (Par<?, ?>) drugi;

        return this.a.equals(drugiPar.a) && this.b.equals(drugiPar.b);
    }
}
