/*
 * Prevajanje in zagon testnega programa testXY.c:
 *
 * gcc -D=test testXY.c naloga2.c
 * ./a.out
 *
 * Zagon testne skripte ("sele potem, ko ste prepri"cani, da program deluje!):
 *
 * export name=naloga2
 * make test
 *
 * Javni testni primeri:
 *
 * 01..03: n = 3, d = 1
 * 04..06: d = 1
 * 07..10: splo"sni
 * 01, 04, 07: majhni, ro"cno izdelani primeri
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "naloga2.h"

int vrniSivino(int **slika, int a, int b, int d) {
	int tot = 0;
	
	for (int i=0; i<2*d + 1; i++) {
		for (int j=0; j<2*d + 1; j++) {
			tot += slika[i+a][j+b];
		}	
	}
	
	int povprecje = tot / ((2*d + 1) * (2*d + 1));

	return povprecje;
}


int** zmehcaj(int** slika, int n, int m, int d) {
	int noviN = n - 2 * d;
	int noviM = m - 2 * d;
	
	int **novaSlika = (int **) malloc(noviN * sizeof(int *));
	
	for (int i=0; i<noviN; i++) {
		int *novaVrsta = (int *) malloc(noviM * sizeof(int));
	
		for (int j=0; j<noviM; j++) {
			novaVrsta[j] = vrniSivino(slika, i, j, d);
		}
		
		novaSlika[i] = novaVrsta;
	}
	
    return novaSlika;
}

#ifndef test

int* SLIKA[] = {
    (int[]) {70, 50, 80, 10, 20, 60},
    (int[]) { 0, 40, 30, 10, 90, 20},
    (int[]) {80, 40,  0, 30, 70, 50},
};

int N = 3;
int M = 6;
int D = 1;

int main() {
    int** nova = zmehcaj(SLIKA, N, M, D);
    int noviN = N - 2 * D;
    int noviM = M - 2 * D;

    for (int i = 0; i < noviN; i++) {
        for (int j = 0; j < noviM; j++) {
            printf("%5d", nova[i][j]);
        }
        printf("\n");
    }

    for (int i = 0; i < noviN; i++) {
        free(nova[i]);
    }
    
    free(nova);

    return 0;
}

#endif
