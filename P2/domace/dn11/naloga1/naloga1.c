#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "naloga1.h"


Vozlisce*novoV(int n) {
    if (n <= 0) {
        return NULL;
    }
    
    Vozlisce* v = malloc(sizeof(Vozlisce));
    v->podatek = n;
    v->naslednje = NULL;
    
    return v;
}


Vozlisce* zdesetkaj(Vozlisce* zacetek, int k) {
	int counter = 1;
	Vozlisce *novZacetek = NULL;
	Vozlisce *prejsnji = NULL;

	while (zacetek->naslednje != NULL) {
		if (counter != k) {		
			counter++;
			zacetek = zacetek->naslednje;
			continue;
		}
		
		if (novZacetek == NULL) {
			prejsnji = novoV(zacetek->podatek);
			novZacetek = prejsnji;
			
			counter = 1;
			zacetek = zacetek->naslednje;	
		} else {
			prejsnji->naslednje = novoV(zacetek->podatek);
			
			prejsnji = prejsnji->naslednje;
			
			counter = 1;
			zacetek = zacetek->naslednje;
		}
		
	}
	
	free(zacetek);

    return novZacetek;
}

#ifndef test
/**/
Vozlisce* ustvari(int* t, int n) {
    if (n <= 0) {
        return NULL;
    }
    Vozlisce* v = malloc(sizeof(Vozlisce));
    v->podatek = *t;
    v->naslednje = ustvari(t + 1, n - 1);
    return v;
}

Vozlisce** shrani(int* tabela, Vozlisce* seznam, int n, int k, int* dolzina) {
    *dolzina = n / k;
    Vozlisce** shramba = malloc(*dolzina * sizeof(Vozlisce*));
    Vozlisce* v = seznam;
    Vozlisce** p = shramba;
    int i = 1;
    while (v != NULL) {
        if (i % k == 0) {
            *p++ = v;
        }
        v = v->naslednje;
        i++;
    }
    return shramba;
}

void izpisi(Vozlisce* v) {
    bool prvic = true;
    printf("[");
    while (v != NULL) {
        if (!prvic) {
            printf(", ");
        }
        prvic = false;
        printf("%d", v->podatek);
        v = v->naslednje;
    }
    printf("]\n");
}

void preveri(Vozlisce** tabela, Vozlisce* seznam, int dolzina) {
    int i = 0;
    Vozlisce* v = seznam;
    Vozlisce** t = tabela;
    while (v != NULL && i < dolzina) {
        printf("%d", v == *t);
        v = v->naslednje;
        t++;
        i++;
    }
    printf("\n");
}

void pocisti(Vozlisce* v) {
    if (v != NULL) {
        pocisti(v->naslednje);
        free(v);
    }
}

int main() {
    int tabela[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    int n = sizeof(tabela) / sizeof(int);
    int k = 3;

    Vozlisce* zacetek = ustvari(tabela, n);
    int dolzina = 0;
    Vozlisce** shramba = shrani(tabela, zacetek, n, k, &dolzina);

    zacetek = zdesetkaj(zacetek, k);
    izpisi(zacetek);
    preveri(shramba, zacetek, dolzina);
    pocisti(zacetek);
    free(shramba);

    return 0;
}
/**/
/*
int main() {
	return 0;
}
*/
#endif
