import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transakcija {

	private int clan;
	private int naslov;
	private VrstaTransakcije vrstaTransakcije;
	private GregorianCalendar datum;
	private Status status;

	private Transakcija(int clan, int naslov, VrstaTransakcije vrsta, GregorianCalendar gc) {
		this(clan, naslov, vrsta, gc, Status.OK);
	}
	
	private Transakcija(int clan, int naslov, VrstaTransakcije vrsta, GregorianCalendar gc, Status status) {
		this.clan = clan;
		this.naslov = naslov;
		this.vrstaTransakcije = vrsta;
		this.datum = gc;
		this.status = status;
	}	
	
	public int getClan() {
		return this.clan;
	}
	
	public int getNaslov() {
		return this.naslov;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public String datum() {
		return String.format("%d.%d.%d", datum.get(Calendar.DAY_OF_MONTH), datum.get(Calendar.MONTH) + 1, datum.get(Calendar.YEAR));
	}	
	
	public static Transakcija createTransakcija(int clan, int naslov, VrstaTransakcije vrsta, GregorianCalendar gc, Status status) {
		if (clan < 0 || naslov < 0 || gc == null || vrsta == null)
			return null;
		return new Transakcija(clan, naslov, vrsta, gc, status);
	}
	
	public VrstaTransakcije getVrsta() {
		return this.vrstaTransakcije;
	}
	
	public int razlikaDni(Transakcija t) {	
		long diffDays = (t.datum.getTimeInMillis() - this.datum.getTimeInMillis()) / (1000L * 60 * 60 * 24);
		return (int) diffDays;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("C = %d, K = %d, [", clan, naslov));
		sb.append(vrstaTransakcije.getOznaka());
		sb.append("], ");
		sb.append(String.format("%d.%d.%d, ", datum.get(Calendar.DAY_OF_MONTH), datum.get(Calendar.MONTH) + 1, datum.get(Calendar.YEAR)));
		sb.append(String.format("[%s]", status.getValue()));
		return sb.toString();
	}
}


