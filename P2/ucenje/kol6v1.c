# include <stdio.h>
# include <stdlib.h>

int main() {
	int n = 0;
	scanf("%d", &n);
	
	int d = 0;
	
	int* staraStevila = calloc(n, sizeof(int));
	
	int* dvojnaStevila = calloc(n, sizeof(int));
	int dvojnaStevilaLen = 0;
	
	for (int i=0; i<n; i++) {
		int st = 0;
		scanf("%d", &st);
		
		// zapisemo stevilo v tabelo
		// *(staraStevila + i) = st;
		staraStevila[i] = st;
		
		
	}
	
	// izpiši
	printf("%d\n", d);
	
	if (d > 0) {
		for (int i=0; i<dvojnaStevilaLen; i++) {
			// printf("%d\n", *(dvojnaStevila + i));
			printf("%d\n", dvojnaStevila[i]);
		}
	}

	return 0;
}
