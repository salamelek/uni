#include <stdio.h>
#include <stdlib.h>

#include "naloga.h"


int vsota(int* zac, int* kon) {
    int vsota = 0;
    
    for (int i=0; i<kon-zac; i++) {
    	vsota += *(zac + i);
    }
    
    return vsota;
}

void indeksInKazalec(int* t, int* indeks, int** kazalec) {
	if (*indeks == -1) {
		*indeks = *kazalec - t;
	} else {
		*kazalec = t + *indeks;
	}
}

void frekvenceCrk(char* niz, int** frekvence) {
	// calloc ustvari tabelo z 0
	// malloc ustvari tabelo z ?
    int* tabela = calloc(26, sizeof(int));
    char* mojNiz = niz;
    
    
	// za vsako črko v besedi
	while (*mojNiz != '\0') {
		char crka = *mojNiz;
		int indeksCrke = -1;
		
		if (crka >= 65 && crka <=90) {
			// velike črek
			indeksCrke = crka - 'A';
			*(tabela + indeksCrke) += 1;
		} else if (crka >= 97 && crka <=122) {
			// male črke
			indeksCrke = crka - 'a';
			*(tabela + indeksCrke) += 1;
		}
	
		mojNiz++;
	}
    
    *frekvence = tabela;
}

#ifndef test
int main() {
    int* frekvence;
    frekvenceCrk("baNanA", &frekvence);
    for (int c = 'A';  c <= 'Z';  c++) {
        if (frekvence[c - 'A'] > 0) {
            printf("%c -> %d\n", c, frekvence[c - 'A']);
        }
    }

    free(frekvence);

    exit(0);
    return 0;
}
#endif
