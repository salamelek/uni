# include<stdio.h>

int tabela[1000000];

int main() {
	int n;
	scanf("%d", &n);
	
	int st;
	for (int i=0; i<n; i++) {
		scanf("%d", &st);
		tabela[i] = st;
	}
	
	int najVsota = tabela[0];
	int vsota = 0;
	
	for (int i=0; i<n; i++) {
		vsota += tabela[i];
		
		if (vsota > najVsota) {
			najVsota = vsota;
		}
		
		if (vsota < 0) {
			vsota = 0;
		}
	}
	
	printf("%d\n", najVsota);
	
	return 0;
}
