/*
 * Zagon testne skripte ("sele potem, ko ste prepri"cani, da program deluje!):
 *
 * export name=naloga1
 * make test
 *
 * Javni testni primeri:
 *
 * 01: iz besedila naloge
 * 02..04: a = b > 0
 * 05..07: a > b > 0
 * 01, 08..10: ni dodatnih omejitev glede a in b
 * 02, 04, 06, 08, 10: vsak bajt je bodisi 0 bodisi 1
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>

// po potrebi dopolnite ...

int main(int argc, char** argv) {
	if (argc != 4) {
		printf("Podani morajo biti 3 argumenti!");
		return 1;
	}

	char *imeA = argv[1];
	char *imeB = argv[2];
	char *imeC = argv[3];
	
	FILE *datA = fopen(imeA, "rb");
	FILE *datB = fopen(imeB, "rb");
	FILE *datC = fopen(imeC, "wb");
	
	// dobi dolzino datotek
	fseek(datA, 0, SEEK_END);
	long a = ftell(datA);
	fseek(datA, 0, SEEK_SET);
	
	fseek(datB, 0, SEEK_END);
	long b = ftell(datB);
	fseek(datB, 0, SEEK_SET);
	
	long min = b;
	long max = a;
	FILE *longerFile = datA;
	
	if (a < b) {
		min = a;
		max = b;
		longerFile = datB;
	}
	
	unsigned char byteA;
	unsigned char byteB;
	
	for (long i=0; i<min; i++) {
		fread(&byteA, 1, 1, datA);
		fread(&byteB, 1, 1, datB);
		
		if (byteA > byteB) {
			fwrite(&byteA, 1, 1, datC);
		} else {
			fwrite(&byteB, 1, 1, datC);
		}
	}
	
	for (long i=min; i<max; i++) {
		fread(&byteA, 1, 1, longerFile);
		fwrite(&byteA, 1, 1, datC);
	}
	
	fclose(datA);
	fclose(datB);
	fclose(datC);

    return 0;
}
