/*

Prevajanje in poganjanje:

gcc -o test01 test01.c tranzitivnost.c -lm
./test01

*/

#include <stdio.h>
#include <stdbool.h>

#include "tranzitivnost.h"

int tranzitivnost(int a, int b) {
	bool tranzitivna = true;
	bool antitranzitivna = true;
	bool netranzitivna = true;

	for (int x=a; x<=b; x++) {
		for (int y=a; y<=b; y++) {
			for (int z=a; z<=b; z++) {
				// tranzitivnost
				if (!(!(f(x,y) == 1 && f(y,z) == 1) || f(x, z) == 1)) {
					tranzitivna = false;
				}
				
				// antitranzitivnost
				if (!(!(f(x, y) == 1 && f(y, z) == 1) || f(x, z) == 0)) {
					antitranzitivna = false;
				}
				
				// netranzitivnost
				if (!(f(x, y) == 1 && f(y, z) == 1 && f(x, z) == 0)) {
					netranzitivna = false;
				}
				
				if (!antitranzitivna && !tranzitivna && !netranzitivna) {	
					goto end;
				}
			}
		}
	}
	end:
	
	if (tranzitivna && antitranzitivna) {
		return 1;
	} else if (tranzitivna) {
		return 2;
	} else if (antitranzitivna) {
		return 3;
	} else if (netranzitivna) {
		return 4;
	}
	
    return -1;
}
