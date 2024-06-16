/*
 * Zagon testne skripte ("sele potem, ko ste prepri"cani, da program deluje!):
 *
 * export name=naloga1
 * make test
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// po potrebi dopolnite ...

int main(int argc, char** argv) {
	if (argc != 3) {
		printf("Argumentov mora biti 2!\n");
		return 1;
	}
	
    FILE *vhod = fopen(argv[1], "rb");
    FILE *izhod = fopen(argv[2], "wb");
    
    // dolzina vhoda
    fseek(vhod, 0, SEEK_END);
    long dolz = ftell(vhod);
    fseek(vhod, 0, SEEK_SET);
    
    unsigned char bajt1;
    unsigned char bajt2;
    unsigned char c9 = 0xC9;
    
	fread(&bajt1, 1, 1, vhod);
    
	for (int i=0; i<dolz; i++) {		
		bajt2 = bajt1;
		fread(&bajt1, 1, 1, vhod);
		
		if (bajt1 == 0xC9 && bajt2 == 0x1B) {
			fwrite(&c9, 1, 1, izhod);
			bajt2 = bajt1;
			fread(&bajt1, 1, 1, vhod);
			continue;
		}
		
		fwrite(&bajt2, 1, 1, izhod);
	}
	
	fclose(vhod);
	fclose(izhod);

    return 0;
}
