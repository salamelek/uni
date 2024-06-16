#include <stdio.h>
#include <stdlib.h>


unsigned char *vrniRaster(char *fileName, int *sirina, int *visina) {
	FILE *slika = fopen(fileName, "rb");
	
	// skip magic word
	while (fgetc(slika) != '\n');
	
	fscanf(slika, "%d%d", sirina, visina);
	
	// skip \n and 255\n
	while (fgetc(slika) != '\n');
	while (fgetc(slika) != '\n');
	
	// read pixels
	int blockSize = 3 * *visina * *sirina;
	unsigned char *pike = (unsigned char *) malloc(blockSize * sizeof(unsigned char));
	fread(pike, sizeof(unsigned char), blockSize, slika);
	
	fclose(slika);
	
	return pike;
}


int main(int argc, char **argv) {
	if (argc != 4) {
		printf("Argumenti!\n");
		return 1;
	}
	
	
	int vrstica = atoi(argv[2]);
	int stolpec = atoi(argv[3]);
	
	
	int sirina = 0;
	int visina = 0;
	
	unsigned char *pike = vrniRaster(argv[1], &sirina, &visina);
	
	
	
	int index = 3 * (sirina * vrstica + stolpec);
	printf("%d %d %d\n", pike[index+0], pike[index+1], pike[index+2]);
	free(pike);
	

	return 0;
}
