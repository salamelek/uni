#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include "naloga.h"

#include <limits.h>


int vsotaI(Vozlisce* zacetek) {
	Vozlisce* next = zacetek;

	int vsota = 0;
	
	if (zacetek == NULL) {
		return 0;
	}
	
	while (next->naslednje != NULL) {	
		vsota += next->podatek;
		
		next = next->naslednje;
	}
	
	
	vsota += next->podatek;
	
    return vsota;
}


int vsotaR(Vozlisce* zacetek) {
	if (zacetek == NULL) {
		return 0;
	}

	if (zacetek->naslednje == NULL) {
		return zacetek->podatek;
	}
	
    return zacetek->podatek + vsotaR(zacetek->naslednje);
}


Vozlisce* ustvariVozlisce(int element, Vozlisce* naslednje) {
	Vozlisce* novo = malloc(sizeof(Vozlisce));
	novo->podatek = element;
	novo->naslednje = naslednje;
	
	return novo;
}


Vozlisce* vstaviUrejenoI(Vozlisce* zacetek, int element) {
	// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
}


Vozlisce* vstaviUrejenoR(Vozlisce* zacetek, int element) {
	if (zacetek == NULL || zacetek->podatek >= element) {
		return ustvariVozlisce(element, zacetek);
	}
	
	zacetek->naslednje = vstaviUrejenoR(zacetek->naslednje, element);
	
	return zacetek;
}


#ifndef test

char NIZ[1000];

Vozlisce* zgradi(int* t) {
    Vozlisce* zacetek = NULL;
    Vozlisce* prejsnje = NULL;
    int* p = t;

    while (*p != INT_MAX) {
        Vozlisce* novo = malloc(sizeof(Vozlisce));
        novo->podatek = *p;
        novo->naslednje = NULL;
        if (p == t) {
            zacetek = novo;
        } else {
            prejsnje->naslednje = novo;
        }
        prejsnje = novo;
        p++;
    }
    return zacetek;
}

void pocisti(Vozlisce* v) {
    if (v != NULL) {
        pocisti(v->naslednje);
        free(v);
    }
}

char* vNiz(Vozlisce* zacetek) {
    char* pNiz = NIZ;
    *pNiz++ = '[';
    Vozlisce* v = zacetek;
    while (v != NULL) {
        if (v != zacetek) {
            pNiz += sprintf(pNiz, ", ");
        }
        pNiz += sprintf(pNiz, "%d", v->podatek);
        v = v->naslednje;
    }
    sprintf(pNiz, "]");
    return NIZ;
}

void testiraj(Vozlisce* v, int element) {
    printf("%s", vNiz(v));
    v = vstaviUrejenoI(v, element);
    printf(" + %d -> %s\n", element, vNiz(v));
    pocisti(v);
}

int main() {
    testiraj(zgradi((int[]) {6, 10, 15, INT_MAX}), 8);
    testiraj(zgradi((int[]) {6, 10, 15, INT_MAX}), 12);

    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 8);
    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 13);
    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 17);
    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 25);
    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 34);
    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 40);
    testiraj(zgradi((int[]) {5, 13, 20, 34, 48, 60, INT_MAX}), 50);

    testiraj(zgradi((int[]) {-30, -20, -10, INT_MAX}), -25);
    testiraj(zgradi((int[]) {-30, -20, -10, INT_MAX}), -15);
    testiraj(zgradi((int[]) {-30, -20, -10, INT_MAX}), -10);

    testiraj(zgradi((int[]) {-10, 10, INT_MAX}), 0);

    return 0;
}

#endif
