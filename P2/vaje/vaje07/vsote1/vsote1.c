# include <stdio.h>

int memo[401][401];	// v globalni tabeli so vedno same 0
int zePogledane[401][401];

// vs: vsota, ns: največji sumand
long kolikoRek(int vs, int ns) {
	if (vs == 0 && ns > 0) {
		return 1;
	}
	
	if (vs < 0 || ns <= 0) {
		return 0;
	}
	
	// pohitrimo
	if (zePogledane[vs][ns] == 1) {
		return memo[vs][ns];
	}
	
	long leva = kolikoRek(vs - ns, ns);
	long desna = kolikoRek(vs, ns - 1);
	
	memo[vs][ns] = leva + desna;
	zePogledane[vs][ns] = 1;
	
	return leva + desna;
}

int main() {
	int n, k;
	scanf("%d%d", &n, &k);
	
	printf("%ld\n", kolikoRek(n, k));
	return 0;
}
