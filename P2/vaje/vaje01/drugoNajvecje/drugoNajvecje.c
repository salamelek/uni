# include <stdio.h>

int main() {
	int n = getchar() - '0';
	getchar();
	
	int najvecje = 0;
	int drugoNajvecje = 0;
	
	for (int i=0; i<n; i++) {
		int current = getchar() - '0';
		getchar();
		
		if (current > najvecje) {
			drugoNajvecje = najvecje;
			najvecje = current;
			continue;
		}
		
		if (current > drugoNajvecje) {
			drugoNajvecje = current;
		}
	}
	
	putchar(drugoNajvecje + '0');
}
