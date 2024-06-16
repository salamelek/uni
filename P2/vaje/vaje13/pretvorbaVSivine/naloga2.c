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


unsigned char *toGrayScale(unsigned char *barve, int sirina, int visina) {
	unsigned char *sivine = (unsigned char *) malloc(visina * sirina * sizeof(unsigned char));
	
	int barveIx = 0;
	int sivineIx = 0;
	
	for (int i=0; i<visina; i++) {
		for (int j=0; j<sirina; j++) {
			sivine[sivineIx] = (30 * barve[barveIx] + 59 * barve[barveIx+1] + 11 * barve[barveIx+2]) / 100;
			barveIx += 3;
			sivineIx += 1;
		}
	}

	return sivine;
}


int main(int argc, char **argv) {
	if (argc != 3) {
		printf("Argumenti!\n");
		return 1;
	}
	
	int sirina = 0;
	int visina = 0;
	
	unsigned char *pike = vrniRaster(argv[1], &sirina, &visina);
	unsigned char *sivine = toGrayScale(pike, sirina, visina);
	
	free(pike);
	
	// napisi sivine v .pgm format
	FILE *izhod = fopen(argv[3], "wb");	
	
	fprintf(izhod, "P5\n");
	fprintf(izhod, "%d %d\n", sirina, visina);
	fprintf(izhod, "255\n");
	fwrite(sivine, sizeof(unsigned char), visina * sirina, izhod);
	
	fclose(izhod);

	return 0;
}

/*
#include <stdio.h>
#include <stdlib.h>
 
 
unsigned char *vrniRaster(char *fileName, int *sirina, int *visina)
{	
	FILE *fileSlika = fopen(fileName, "rb");
 
	// 1 - preberemo raster slike / najprej glavo
	// preberi glavo datoteke (pomembni sta samo sirina in visina)
 
	// P6					(magic number)
	// sirina visina
	// 255					(maximum color value)
	// [vsebina]			(raster)
 
	int nTemp = 10;
	char *temp = (char *) malloc((nTemp + 1) * sizeof(char));
	// char *fgets(char *str, int n, FILE *stream)
	// 1. vrstica
	fgets(temp, nTemp, fileSlika);
	// 2. vrstica
	fscanf(fileSlika, "%d%d", sirina, visina);	
	// se '\n'
	fgetc(fileSlika);	
	// 3. vrstica
	fgets(temp, nTemp, fileSlika);
	free(temp);
 
	// 2 - preberi podatke o slikovnih pikah v tabelo 'pike'
	int blokSize = 3 * *visina * *sirina;
	unsigned char *pike = (unsigned char *) malloc(blokSize * sizeof(unsigned char));
	// size_t fread(void *ptr, size_t size, size_t nmemb, FILE *stream)
	//        (pointer na blok pomnilnika, velikost enga elementa, stevilo elementov, stream)
	fread(pike, sizeof(unsigned char), blokSize, fileSlika);
	fclose(fileSlika);
 
	return pike;
}
 

 
unsigned char *toGrayscale(unsigned char *barve, int sirina, int visina)
{
	unsigned char *sivine = (unsigned char *) malloc(visina * sirina * sizeof(unsigned char));
	int b = 0;
	int s = 0;
	for (int i = 0; i < visina; i++)
	{
		for (int j = 0; j < sirina; j++)
		{
			sivine[s] = (30 * barve[b] + 59 * barve[b + 1] + 11 * barve[b + 2] ) / 100;
			b += 3;
			s++;
		}
	}
	return sivine;
}
 

 
void createPgmFile(char *fileName, unsigned char *sivine, int sirina, int visina)
{
	// P5
	// sirina visina
	// 255
	// [vsebina]
 
	FILE *pgmFile = fopen(fileName, "wb");
	fprintf(pgmFile, "P5\n");
	fprintf(pgmFile, "%d %d\n", sirina, visina);	
	fprintf(pgmFile, "255\n");
	// size_t fwrite(const void *ptr, size_t size, size_t nmemb, FILE *stream)
	fwrite(sivine, sizeof(unsigned char), visina * sirina, pgmFile);
	fclose(pgmFile);
}
 
 
int main(int argc, char **argv)
{
	// 1 - preveri datoteko <ppm>
	int sirina = 0;
	int visina = 0;
	unsigned char *barve = vrniRaster(argv[1], &sirina, &visina);
 
	// 2 - pretvori barve v sivine
	unsigned char *sivine = toGrayscale(barve, sirina, visina);
	free(barve);
 
	// 3 - zapisi v datoteko <pgm>
	createPgmFile(argv[2], sivine, sirina, visina);
	free(sivine);
 
	return 0;
}
*/
