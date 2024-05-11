# include <stdio.h>
# include <stdlib.h>

void napisi(int* cleni, int n) {
	for (int i=0; i<n; i++) {
		if (i > 0) {
			printf("+ ");
		}
		
		printf("%d ", cleni[i]);
	}
	
	printf("\n");
}

int kolikoRek(int vs, int ns, int* cleni, int stClenov) {
	if (vs == 0 && ns > 0) {
		napisi(cleni, stClenov);
		return 1;
	}
	
	if (vs < 0 || ns <= 0) {
		return 0;
	}
	
	
	cleni[stClenov] = ns;
	
	int leva = kolikoRek(vs - ns, ns, cleni, stClenov + 1);
	int desna = kolikoRek(vs, ns - 1, cleni, stClenov);
	
	return leva + desna;
}

int main() {
	int n, k;
	scanf("%d%d", &n, &k);
	
	int* cleni = calloc(n, sizeof(int));
	
	kolikoRek(n, k, cleni, 0);
	return 0;
}
