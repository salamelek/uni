#include <stdio.h>
#include <stdlib.h>
#include <string.h>


char *vrniIme(char *imeDat, int counter) {
	size_t newSize = (strlen(imeDat) + 25);
	char *novoIme = (char *) malloc(newSize * sizeof(char));
	
	if (novoIme == NULL) {
        return NULL;
    }
	
	snprintf(novoIme, newSize, "%s%d", imeDat, counter);

	return novoIme;
}


int main(int argc, char **argv) {
	if (argc != 3) {
		printf("Argumenti!");
		return 1;
	}
	
	FILE *staraDat = fopen(argv[1], "rb");
	
	int k = atoi(argv[2]);
	int counter = 0;
	int running = 1;
	
	while (running) {
		char *novoIme = vrniIme(argv[1], counter);
		FILE *novaDat = fopen(novoIme, "wb");
		
		for (int i=0; i<k; i++) {
			int byte = fgetc(staraDat);
			
			if (byte == EOF) {
				running = 0;
			}
			
			fputc(byte, novaDat);
		}
		
		fclose(novaDat);
		
		counter++;
	}
	
	fclose(staraDat);
	
	return 0;
}
