public class Zanke {
	public static void main(String[] args) {
		outer:
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (j == 5 && i == 5) {
					System.out.printf("i: %d, j: %d%n", i, j);
					break outer;
				}
				System.out.printf("i: %d, j: %d%n", i, j);
			}
		}
	}
}
