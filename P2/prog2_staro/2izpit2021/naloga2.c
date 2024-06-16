#include <stdio.h>
#include <stdlib.h>


void printNum(int *buffer, int bufferLen) {
	int num = 0;
	
	for (int i=0; i<bufferLen; i++) {
		num += buffer[bufferLen - i - 1] << (i * 7);
	}
	
	printf("%d\n", num);
}


int main(int argc, char **argv) {
	if (argc != 2) {
		printf("Argumenti!\n");
		return 1;
	}
	
	FILE *input = fopen(argv[1], "rb");
	int *byteBuffer = (int *) malloc(16 * sizeof(int));
	
	int bufferCounter = 0;
	while (1) {
		int byte = fgetc(input);
		
		if (byte == EOF) {
			break;
		}
		
		byteBuffer[bufferCounter] = byte;
		bufferCounter++;
		
		byteBuffer[bufferCounter-1] = byteBuffer[bufferCounter-1] >> 1;
		
		if (byte % 2 == 0) {
			printNum(byteBuffer, bufferCounter);
			bufferCounter = 0;
		}
	}
	
	
	free(byteBuffer);
	fclose(input);

	return 0;
}
