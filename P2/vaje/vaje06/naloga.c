#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include "naloga.h"

int steviloZnakov(char* niz, char znak) {
	int count = 0;
	int i=0;
	while (*(niz + i) != '\0') {
		if (*(niz + i) == znak) {
			count++;
		}
		
		i++;
	}

    return count;
}

char* kopirajDoZnaka(char* niz, char znak) {
    int i=0;
	while (*(niz + i) != '\0') {
		if (*(niz + i) == znak) {
			break;
		}
		
		i++;
	}
	
    char* niz2 = calloc(i + 1, sizeof(char));
    
    for (int j=0; j<i; j++) {
    	niz2[j] = *(niz + j); 
    }
    
    niz2[i] = '\0';
    
    return niz2;
}

char** razcleni(char* niz, char locilo, int* stOdsekov) {
	*stOdsekov = steviloZnakov(niz, locilo) + 1;
	char** odseki = calloc(*stOdsekov, sizeof(char*));
	
	int j=0;
	// za vsak odsek
	for (int i=0; i<*stOdsekov; i++) {
		odseki[i] = kopirajDoZnaka(niz+j, locilo);
		
		int tmp = 0;
		while (*(odseki[i] + tmp) != '\0') {
			tmp++;
		}
		
		j += tmp + 1;
		
	}
	
    return odseki;
}

#ifndef test

int main() {
    return 0;
}

#endif
