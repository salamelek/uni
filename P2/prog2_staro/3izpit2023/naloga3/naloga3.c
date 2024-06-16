/*
 * Zagon testne skripte ("sele potem, ko ste prepri"cani, da program deluje!):
 *
 * export name=naloga3
 * make test
 *
 * Javni testni primeri:
 *
 * 01: iz besedila naloge
 * 02..04: znaki v nizu so padajo"ce urejeni
 * 01, 05..10: splo"sni
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


int vrniIndexNajvecjega(char *niz) {
	if (strlen(niz) == 1) {
		return 0;
	}

	int najIndex = 0;
	int najVrednost = niz[najIndex];

	for (int i=1; i<strlen(niz); i++) {
		if (niz[i] > najVrednost) {
			najVrednost = niz[i];
			najIndex = i;
		}
	}
	
	return najIndex;
}


void izpisi(char *niz, char* odmik, int i) {
	if (niz == NULL || strlen(niz) == 0) {
		return;
	}
	
	if (strlen(niz) == 1) {
		printf("%s%s\n", odmik, niz);
		return;
	}
	
	// izpis zacetnega niza
	printf("%s%s\n", odmik, niz);
	
	// izpis pred m
	int m = vrniIndexNajvecjega(niz);
	char *predM = niz;
	
	char charM = niz[m];
	predM[m] = '\0';
	izpisi(predM, odmik, i);
	
	// izpisi m
	printf("%s%c\n", odmik, charM);
	
	// izpis po m
	odmik[i] = ' ';
	i++;
	odmik[i] = '\0';
	char *preostanek = &niz[m+1];
	izpisi(preostanek, odmik, i);
}


int main() {
    char *niz = (char *) calloc(200, sizeof(char));
    scanf("%s", niz);
    
    char *odmik = (char *) calloc(200, sizeof(char));
    odmik[0] = '\0';
    
    izpisi(niz, odmik, 0);

    return 0;
}
