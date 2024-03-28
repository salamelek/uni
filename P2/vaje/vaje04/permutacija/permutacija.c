# include <stdio.h>

int tabela[1000000];

int imaSt(int st, int range) {
	for (int i=0; i<range; i++) {
		if (tabela[i] == st) {
			return 1;
		}
	}
	
	return 0;
}

int main() {
	int n = 0;
	scanf("%d", &n);
	
	int st;
	for (int i=0; i<n; i++) {
		scanf("%d", &st);
		tabela[i] = st;
	}
	
	int permutacija = 1;
	for (int i=0; i<n; i++) {
		if (imaSt(i, n) == 0) {
			permutacija = 0;
		}
	}
	
	if (permutacija == 1) {
		printf("DA\n");
	} else {
		printf("NE\n");
	}
	
	return 0;
}
