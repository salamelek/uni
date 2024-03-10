public class Oseba {
    private String ime;
    private String priimek;
    private char spol;
    private int letoRojstva;
    private Oseba oce;
    private Oseba mati;

    public Oseba(String ime, String priimek, char spol, int letoRojstva, Oseba oce, Oseba mati) {
        this.ime = ime;
        this.priimek = priimek;
        this.spol = spol;
        this.letoRojstva = letoRojstva;
        this.oce = oce;
        this.mati = mati;
    }

    public String vrniIme() {
        /*
        Vrne ime osebe this (torej osebe, ki jo predstavlja objekt, na katerega kaže kazalec this).
         */

        return this.ime;
    }

    public String toString() {
        /*
        Vrne niz, ki vsebuje podatke o osebi this v sledeči obliki:
        ime␣priimek ␣(spol),␣letoRojstva
        Na primer:
        Jože␣Gorišek␣(M),␣1953
         */

        return String.format("%s %s (%c), %d", this.ime, this.priimek, this.spol, this.letoRojstva);
    }

    public int starost(int leto) {
        /*
        Vrne starost osebe this v podanem letu.
         */

        return leto - this.letoRojstva;
    }

    public boolean jeStarejsaOd(Oseba os) {
        /*
        Vrne true natanko v primeru, če je oseba this starejša od osebe os.
         */

        if (this.letoRojstva < os.letoRojstva) {
            return true;
        } else {
            return false;
        }
    }

    public static Oseba starejsa(Oseba a, Oseba b) {
        /*
        Vrne starejšo izmed podanih oseb oziroma null, če sta osebi enako stari.
         */

        Oseba starejsaOseba = null;

        if (a.letoRojstva < b.letoRojstva) {
            starejsaOseba = a;
        }

        if (b.letoRojstva < a.letoRojstva) {
            starejsaOseba = b;
        }

        return starejsaOseba;
    }

    public String imeOceta() {
        /*
        Vrne ime očeta osebe this oziroma null, če oče ni znan.
         */

        if (this.oce != null) {
            return this.oce.ime;
        } else {
            return null;
        }
    }

    public boolean jeBratAliSestraOd(Oseba os) {
        /*
        Vrne true natanko v primeru, če je oseba this brat ali sestra osebe os.
         */

        // preverimo če nista ista oseba
        if (this == os) {
            return false;
        }

        if (this.oce == os.oce && this.mati == os.mati) {
            return true;
        } else {
            return false;
        }
    }

    public boolean jeSestraOd(Oseba os) {
        /*
        Vrne true natanko v primeru, če je oseba this sestra osebe os.
         */

        // preveri če je ženska
        if (this.spol == 'M') {
            return false;
        }

        if (this.oce == os.oce || this.mati == os.mati) {
            return true;
        } else {
            return false;
        }
    }

    public boolean jeTetaOd(Oseba os) {
        /*
        Vrne true natanko v primeru, če je oseba this teta osebe os.
         */

        if (this.jeSestraOd(os.mati) || this.jeSestraOd(os.oce)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean jeOcetovskiPrednikOd(Oseba os) {
        /*
        Vrne true natanko v primeru, če je oseba this očetovski prednik osebe os. Oseba A
        je očetovski prednik osebe B, če od osebe B do osebe A vodi netrivialna nepretrgana
        veriga očeto
         */

        // preverimo ce ima oceta
        if (os.oce == null) {
            return false;
        }

        if (os.oce == this) {
            return true;
        }

        return jeOcetovskiPrednikOd(os.oce);
    }
}