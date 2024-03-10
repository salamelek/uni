public enum Status {

	OK(0, "OK"), FAIL(1, "Fail");
	
	private final int code;
	private final String value;

	private Status(int code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public int getCode() {
		return code;
	}	
	
	public String getValue() {
		return value;
	}
}