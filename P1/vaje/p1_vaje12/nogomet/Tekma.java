
import java.util.*;

public class Tekma {

    private String domaci;
    private String gostje;
    private int goliDomacih;
    private int goliGostov;

    public Tekma(String domaci, String gostje, int goliDomacih, int goliGostov) {
        this.domaci = domaci;
        this.gostje = gostje;
        this.goliDomacih = goliDomacih;
        this.goliGostov = goliGostov;
    }

    public int steviloTock(String klub) {
        if (this.domaci.equals(klub)) {
            if (this.goliDomacih > this.goliGostov) {
                return 3;
            } else if (this.goliDomacih == this.goliGostov) {
                return 1;
            }
        } else if (this.gostje.equals(klub)) {
            if (this.goliGostov > this.goliDomacih) {
                return 3;
            } else if (this.goliDomacih == this.goliGostov) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s %d : %d %s", this.domaci, this.goliDomacih,
                this.goliGostov, this.gostje);
    }

    public int golRazlika() {
        return this.goliDomacih - this.goliGostov;
    }
}
