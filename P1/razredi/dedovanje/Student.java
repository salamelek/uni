public class Student {
    // damo protected, če hočemo da do tega dostopajo podrazredi
    // ampak namesto tega rajsi rabimo super() konstruktor
    private String ip;
    private String vpisna;
    private int stroskiBivanja;

    public Student(String ip, String vpisna, int stroskiBivanja) {
        this.ip = ip;
        this.vpisna = vpisna;
        this.stroskiBivanja = stroskiBivanja;
    }

    public String vrniIp() {
        return this.ip;
    }

    public int stroski() {
        return this.stroskiBivanja;
    }
}