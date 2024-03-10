public class Inverz extends Zaporedje {
    private Zaporedje zaporedje;
    private Interval interval;

    public Inverz(Zaporedje zaporedje, Interval interval) {
        this.zaporedje = zaporedje;
        this.interval = interval;
    }

    @Override
    public Integer y(int x) {
        int zacetek = this.interval.vrniZacetek();
        int konec = this.interval.vrniKonec();

        for (int i=zacetek; i<=konec; i++) {
            Integer y = this.zaporedje.y(i);

            if (y != null && y == x) {
                return i;
            }
        }

        return null;
    }
}