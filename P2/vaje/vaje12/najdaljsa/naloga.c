#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_DOLZINA_VRSTICE 1000000


int main(int argc, char** argv) {
	if (argc != 3) {
		printf("Napaka! program rabi 2 argumenta!");
		return 1;
	}

	char* imeDatoteke = argv[1];
	char* pisiV = argv[2];
	
    FILE* datBeri = fopen(imeDatoteke, "rt");
    FILE* datPisi = fopen(pisiV, "wt");

    char* vrstica = (char*) malloc((MAX_DOLZINA_VRSTICE + 1) * sizeof(char));
    
    int najSt = -1;
    char *najStr = NULL;
    
    while (fgets(vrstica, MAX_DOLZINA_VRSTICE + 2, datBeri) != NULL) {
    	int dolz = strlen(vrstica);
    	
    	if (dolz > najSt) {
    		najSt = dolz;
    		
    		free(najStr);	// da ni memory leaks
    		najStr = (char*) malloc((dolz + 1) * sizeof(char));
    		strcpy(najStr, vrstica);
    	}
    }
    
	fputs(najStr, datPisi);
	
	free(vrstica);
	free(najStr);
	
    fclose(datBeri);
    fclose(datPisi);

    return 0;
}
