import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public abstract class Zaporedje {

    public abstract Integer y(int x);

    public String vNiz(Interval interval) {
        StringBuilder sb = new StringBuilder("[");
        int zacetek = interval.vrniZacetek();
        int konec = interval.vrniKonec();
        boolean prvic = true;
        for (int x = zacetek;  x <= konec;  x++) {
            Integer y = this.y(x);
            if (y != null) {
                if (!prvic) {
                    sb.append(", ");
                }
                prvic = false;
                sb.append(String.format("%d -> %d", x, y));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void narisi(Graphics g, int x0, int y0, double enota, Color color, Interval interval) {
        int zacetek = interval.vrniZacetek();
        int konec = interval.vrniKonec();

        int prevX = 0;
        int prevY = 0;
        boolean prvic = true;

        g.setColor(color);

        for (int x = zacetek; x <= konec; x++) {
            Integer y = this.y(x);

            if (y == null) {
                continue;
            }

            int px = x0 + (int) Math.round(x * enota);
            int py = y0 - (int) Math.round(x * enota);

            if (prvic) {
                g.fillOval(px - 2, py - 2, 4, 4);
                prevX = x0;
                prevY = y0;
                prvic = false;
                continue;
            }

            g.drawLine(prevX, prevY, px, py);
        }
    }

    public Interval minMax(Interval interval) {
        int zacetek = interval.vrniZacetek();
        int konec = interval.vrniKonec();

        Integer max = null;
        Integer min = null;

        for (int x=zacetek; x<=konec; x++) {
            Integer y = y(x);

            if (y == null) {
                continue;
            }

            if (max == null) {
                max = y;
            } else {
                max = Math.max(y, max);
            }

            if (min == null) {
                min = y;
            } else {
                min = Math.min(y, min);
            }
        }

        return new Interval(min, max);
    }

    public boolean jePadajoce(Interval interval, int smer) {
        int zacetek = interval.vrniZacetek();
        int konec = interval.vrniKonec();
        Integer x1 = null;

        for (int x=zacetek; x<=konec; x++) {
            Integer y = y(x);

            if (y == null) {
                continue;
            }

            if (x1 == null) {
                x1 = y;
                continue;
            }

            if (x1.intValue() * smer >= y.intValue() * smer) {
                return false;
            }

            x1 = y;
        }

        return true;
    }

    public boolean jeMonotono(Interval interval) {
        if (this.jePadajoce(interval, 1) || this.jePadajoce(interval, -1)) {
            return true;
        }

        return false;
    }

    public Zaporedje vsota(Zaporedje drugo) {
        return new Vsota(this, drugo);
    }

    public Zaporedje inverz(Interval interval) {
        int zacetek = interval.vrniZacetek();
        int konec = interval.vrniKonec();

        if (!(this.jeMonotono(interval))) {
            return null;
        }

        return new Inverz(this, interval);
    }
}
