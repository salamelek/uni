public class Par {
	
	private Transakcija a;
	private Transakcija b;
	
	public Par(Transakcija a, Transakcija b) {
		this.a = a;
		this.b = b;
	}
	
	public Transakcija getA() {
		return this.a;
	}
	
	public Transakcija getB() {
		return this.b;
	}
}