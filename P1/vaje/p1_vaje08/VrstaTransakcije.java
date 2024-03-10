public enum VrstaTransakcije {

	NAKUP("+", "nakup"), PRODAJA("-", "prodaja"), 
	IZPOSOJA("I", "izposoja"), VRACILO("V", "vracilo");
	
	private final String oznaka;
	private final String opis;

	private VrstaTransakcije(String oznaka, String value) {
		this.oznaka = oznaka;
		this.opis = value;
	}
	
	public String getOznaka() {
		return oznaka;
	}
	
	public String getOpis() {
		return opis;
	}
}