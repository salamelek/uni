public class IzredniStudent extends Student {
    // atributi ip, vpisa in stroskiBivanja se podedujejo
    // tudi public metode se podedujejo
    // konstruktor pa se ne podeduje

    // tukaj pa lahko dodamo nov atribut
    private int solnina;

    // zdaj pa ustvarimo konstruktor, ki postavi vse 4 atribute. ne samo 3
    public IzredniStudent(String ip, String vpisna, int stroskiBivanja, int solnina) {
        // pokličemo konstruktor razreda Student, ki ima 3 parametre
        // posredujemo mu želene argumente
        super(ip, vpisna, stroskiBivanja);

        // seveda pa solnina je en parameter od podrazreda, torej damo v ta konstruktor
        this.solnina = solnina;
    }

    // redefiniramo metodo stroski() za podrazred
    @Override
    public int stroski() {
        // to ne dela, ker stroskibivanja so private
        // return this.stroskiBivanja + this.solnina;

        // torej je treba rabit super()
        // super.stroski požene metodo stroski() v razredu Student
        return super.stroski() + this.solnina;
    }
}