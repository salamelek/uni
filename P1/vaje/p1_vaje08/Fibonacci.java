public class Fibonacci {
    private int a;
    private int b;

    public Fibonacci(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int naslednji() {
        int naslednji = this.a + this.b;

        this.a = b;
        this.b = naslednji;

        return naslednji;
    }
}