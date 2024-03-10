public abstract class Lik {
    // če je razred abstrakten, se ne more ustvariti takega razreda
    // TO NE DELA: Lik lik = new Lik();
    // vsekakor ima lahko en konstruktor, ampak bo klican le od podrazredov s super()
    // lahko imamo abstrakten razred ki nima abstraktnih metod
    private double ploscina;
    private double obseg;

    // abstraktne metode so tukaj samo zaradi zahtevv prevajalnika
    // ploscino in obseg imajo vsi liki, ampak nima smisla si napisat ene "globalne" formule
    // ker itak ima vsak podrazred tako metodo

    // ČE IMA RAZRED VSAJ ENO ABSSTRACT METODO, MORA TUDI RAZRED BITI ABSTRAKTEN
    public abstract double ploscina();

    // ABSTRAKTEN METODIE NIMAJO {}
    public abstract double obseg();

    public abstract String vrsta();

    public abstract String podatki();

    public String toString() {
        return String.format("%s [%s]", this.vrsta(), this.podatki());
    }
}