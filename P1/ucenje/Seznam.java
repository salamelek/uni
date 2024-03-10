import java.util.*;

public class Seznam <T> implements Iterable<T> {
    private T[] seznam;

    @SuppressWarnings("unchecked")
    public Seznam() {
        this.seznam = (T[]) new Object[0];
    }

    @SuppressWarnings("unchecked")
    public void append(T obj) {
        int dolz = this.seznam.length;
        T[] novSeznam = (T[]) new Object[dolz + 1];

        for (int i=0; i<dolz; i++) {
            novSeznam[i] = this.seznam[i];
        }

       novSeznam[dolz] = obj;

        this.seznam = novSeznam;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.seznam);
    }

    @Override
    public Iterator<T> iterator() {
//        return new IteratorCezSeznam<>(this.seznam);

        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return Seznam.this.seznam.length > index;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    public static class IteratorCezSeznam <T> implements Iterator<T> {
        public int index;
        public T[] seznam;

        public IteratorCezSeznam(T[] seznam) {
            this.index = -1;
            this.seznam = seznam;
        }

        @Override
        public boolean hasNext() {
            return this.index < this.seznam.length - 1;
        }

        @Override
        public T next() {
            this.index++;
            return this.seznam[this.index];
        }
    }

    public int len() {
        return this.seznam.length;
    }

    public Object get(int index) {
        if (index < 0) {
            return this.seznam[this.seznam.length + index];
        }

        return this.seznam[index];
    }

    @SuppressWarnings("unchecked")
    public void pop(int indeks) {
        int dolz = this.seznam.length;
        T[] novSeznam = (T[]) new Object[dolz - 1];

        int counter = 0;
        for (int i=0; i<dolz; i++) {
            if (i == indeks) {
                continue;
            }

            novSeznam[counter] = this.seznam[i];

            counter++;
        }

        this.seznam = novSeznam;
    }
}
