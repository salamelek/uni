/*
 * Zagon testne skripte ("sele potem, ko ste prepri"cani, da program deluje!):
 *
 * export name=naloga3
 * make test
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// po potrebi dopolnite ...

int main() {
    int v = 0;
    int s = 0;
    int k = 0;
    
    scanf("%d%d%d", &v, &s, &k);
    
    
    int* A = calloc(k, sizeof(int));
    
    for (int i=0; i<k; i++) {
    	int tmp = 0;
    	scanf("%d", &tmp);
    	
    	A[i] = tmp;
    }
    
	
    int* B = calloc(v, sizeof(int));
    
    for (int i=0; i<v + 1; i++) {
    	int tmp = 0;
    	scanf("%d", &tmp);
    	
    	B[i] = tmp;
    }
    
	
    int* C = calloc(k, sizeof(int));
    
    for (int i=0; i<k; i++) {
    	int tmp = 0;
    	scanf("%d", &tmp);
    	
    	C[i] = tmp;
    }
    
    
    
    int* koncna = calloc(v*s, sizeof(int));
    
    
    int indCount = 0;
    
    // za vsako vrsto
    for (int i=0; i<v; i++) {
    	int kolikoSt = B[i+1] - B[i];
    	
    	int is = i * s;
    
		// za vsak stolp
		for (int j=0; j<kolikoSt; j++) {
			int indeks = C[indCount];

			koncna[indeks + is] = A[indCount];


			indCount++;			
		}
    }
    
    
    free(A);
    free(B);
    free(C);
    
    
    
    for (int i=0; i<v*s; i++) {    	
    	printf("%d", koncna[i]);
    	
    	if ((i+1) % s == 0) {
    		printf("\n");
    	} else {	
	    	printf("_");
    	}
    }
    
    
	

    return 0;
}
