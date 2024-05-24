# include <stdio.h>
# include <stdlib.h>
# include <string.h>


int steviloInKta(char* niz, int k, int* indeks) {
	int i = 0;
	int couter = 0;
	
	*indeks = -1;
	
	while (niz[i] != '\0') {
		char crka = niz[i];
		
		if (crka >= 65 && crka <= 90) {
			couter++;
		}
		
		if (couter == k && *indeks == -1) {
			*indeks = i;
		}
	
		i++;
	}
	
	return couter;
}


void indeksi(char* niz, int** t) {
	int indeksCrke = 0;
	int stCrk = steviloInKta(niz, 1, &indeksCrke);

	int* indeksiCrk = calloc(stCrk + 1, sizeof(int));
	indeksiCrk[stCrk] = -1;
	
	for (int i=0; i<stCrk; i++) {
		int _ = steviloInKta(niz, stCrk - i, &indeksCrke);
		
		indeksiCrk[i] = indeksCrke;
		
		_++;	// just so i get now warning
	}
	
	*t = indeksiCrk;
}


char** zadnje(char** nizi) {
	// koliko nizov je?
	int i = 0;
	int stNizov = 0;
	while (nizi[i] != NULL) {
		stNizov++;
		i++;
	}

	char** tabela = calloc(stNizov, sizeof(char*));
	
	printf("%d\n", i);
	
	for (int i=0; i<stNizov; i++) {
		int* t;
		indeksi(nizi[i], &t);
		
		int zadnjaCrkaIndeks = t[0];
		
		if (zadnjaCrkaIndeks != -1) {
			char* zadnjaCrkaPointer = &nizi[i][zadnjaCrkaIndeks];
			tabela[i] = zadnjaCrkaPointer;
		} else {
			tabela[i] = NULL;
		}
	}

	return tabela;
}


int main() {
	char* niz1 = "";
	char* niz2 = "B";
	char* niz3 = "A";
	char* niz4 = "aABCa";
	
	char** nizi = calloc(4, sizeof(char*));
	
	nizi[0] = niz1;
	nizi[1] = niz2;
	nizi[2] = niz3;
	nizi[3] = NULL;
	
	char** tab = zadnje(nizi);
	
	for (int i=0; i<4; i++) {
		printf("%p\n", tab[i]);
		if (tab[i] == NULL) {
			printf("NULL\n");
		} else {
			printf("%c\n", *tab[i]);
		}
	}
	
	return 0;
}
