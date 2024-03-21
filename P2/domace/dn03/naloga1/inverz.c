/*

Prevajanje in poganjanje:

gcc -o test01 test01.c inverz.c -lm
./test01

*/

#include <stdio.h>
#include <stdbool.h>

#include "inverz.h"

long inverz(long x, long a, long b) {
	// neumna rešitev
	/*
	for (int i=a; i<=b; i++) {
		// gremo skozi vsa števila v intervalu dokler ne dobimo inverza
		if (f(i) == x) {
			return i;
		}
	}
	*/
	
	// rešitev z bisekcijo
	while (true) {
		// polovična vrednost intervala
		long pol = ((b - a) / 2) + a;
		long fPol = f(pol);
		
		// printf("a: %ld\n", a);
		// printf("b: %ld\n", b);
		// printf("polovica: %ld\n", pol);
	
		// smo zadeli
		if (fPol == x) {
			return pol;
		}
		
		// moramo pogledat spodnjo polovico intervala
		else if (fPol > x) {
			b = pol;
		}
		
		// moramo pogledat zgornjo polovico intervala
		else if (fPol < x) {
			a = pol;
		}
	}
	
    return -1;
}
