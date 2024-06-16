/*
 * Prevajanje in zagon testnega programa testXY.c:
 *
 * gcc -D=test testXY.c naloga3.c
 * ./a.out
 *
 * Zagon testne skripte ("sele potem, ko ste prepri"cani, da program deluje!):
 *
 * export name=naloga3
 * make test
 *
 * Testni primeri:
 * 02, 03: h = 1
 * 04, 05, 06: mesto = 0
 * 01, 07--10: splo"sni
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#include "naloga3.h"

#define _VISINA 3
#define _SIRINA 4

// po potrebi dopolnite ...

Vozlisce *novo(int vsebina, Vozlisce *desno, Vozlisce *dol) {
	Vozlisce *novo = calloc(1, sizeof(Vozlisce));
	
	novo->vsebina = vsebina;
	novo->desno = desno;
	novo->dol = dol;
	
	return novo;
}


Vozlisce* vstaviStolpec(Vozlisce* start, int mesto, int vsebina) {	
	if (mesto == 0) {
		Vozlisce *novStart = novo(vsebina, start, NULL);
		
		Vozlisce *tmp = start;
		int count = 1;
		
		while (tmp->dol != NULL) {
			novStart->dol = novo(vsebina+count, tmp->dol, NULL);
			
			tmp = tmp->dol;			
			count++;
		}
		
		return novStart;
	}
    
    return start;
}








//=============================================================================

#ifndef test

Vozlisce* _MATRIKA[_VISINA][_SIRINA];

const int _VSEBINA[_VISINA][_SIRINA] = {
    {1, 2, 3, 4},
    {5, 6, 7, 8},
    {9, 10, 11, 12},
};

void _napolni() {
    for (int i = 0; i < _VISINA; i++) {
        for (int j = 0; j < _SIRINA; j++) {
            _MATRIKA[i][j] = calloc(1, sizeof(Vozlisce));
            _MATRIKA[i][j]->vsebina = _VSEBINA[i][j];
        }
    }
    for (int i = 0; i < _VISINA; i++) {
        for (int j = 0; j < _SIRINA; j++) {
            if (j < _SIRINA - 1) {
                _MATRIKA[i][j]->desno = _MATRIKA[i][j + 1];
            }
            if (i < _VISINA - 1) {
                _MATRIKA[i][j]->dol = _MATRIKA[i + 1][j];
            }
        }
    }
}

void _assert(bool b) {
    static bool ze = false;
    if (!b && !ze) {
        printf("\nNapaka -- izdelava kopije!\n");
        ze = true;
    }
}

void _preveri(Vozlisce* v0, int mesto) {
    if (mesto == 0) {
        Vozlisce* v = v0;
        for (int i = 0; i < _VISINA; i++) {
            _assert(v->desno == _MATRIKA[i][0]);
            v = v->dol;
        }
    } else {
        _assert(v0 == _MATRIKA[0][0]);
    }
    for (int i = 0; i < _VISINA; i++) {
        for (int j = 0; j < _SIRINA; j++) {
            _assert(_MATRIKA[i][j]->dol == ((i == _VISINA - 1) ? (NULL) : (_MATRIKA[i + 1][j])));
            if (j != mesto - 1) {
                _assert(_MATRIKA[i][j]->desno == ((j == _SIRINA - 1) ? (NULL) : (_MATRIKA[i][j + 1])));
            }
        }
    }
}

void _izpisi(Vozlisce* v0) {
    printf("Po vrsticah:\n");
    Vozlisce* v = v0;
    int i = 0;
    while (v != NULL) {
        printf("%d: [", i);
        Vozlisce* w = v;
        while (w != NULL) {
            if (w != v) {
                printf(", ");
            }
            printf("%d", w->vsebina);
            w = w->desno;
        }
        printf("]\n");
        v = v->dol;
        i++;
    }

    printf("\n");
    printf("Po stolpcih:\n");
    v = v0;
    i = 0;
    while (v != NULL) {
        printf("%d: [", i);
        Vozlisce* w = v;
        while (w != NULL) {
            if (w != v) {
                printf(", ");
            }
            printf("%d", w->vsebina);
            w = w->dol;
        }
        printf("]\n");
        v = v->desno;
        i++;
    }
}

void _pocisti(Vozlisce* v0) {
    Vozlisce* v = v0;
    while (v != NULL) {
        Vozlisce* w = v;
        while (w != NULL) {
            Vozlisce* temp = w->desno;
            free(w);
            w = temp;
        }
        v = v->dol;
    }
}

int main() {
    _napolni();
    int mesto = 0;
    int vsebina = 100;
    Vozlisce* v = vstaviStolpec(_MATRIKA[0][0], mesto, vsebina);

    _izpisi(v);
    _preveri(v, mesto);
    _pocisti(v);

    return 0;
}

#endif
