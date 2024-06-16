#include <stdio.h>
#include <stdlib.h>


int main() {
	char *inputName = (char *) malloc(20 * sizeof(char));
	scanf("%s", inputName);
	
	int byteCount = 0;
	scanf("%d", &byteCount);
	
	char *outputName = (char *) malloc(20 * sizeof(char));
	scanf("%s", outputName);
	
	
	FILE *vhod = fopen(inputName, "rb");
	FILE *izhod = fopen(outputName, "wb");
	
	char *tmp = (char *) malloc(10 * sizeof(char));
	fgets(tmp, 10, vhod);
	
	printf("%s\n", tmp);
	
	free(inputName);
	free(outputName);
	
	fclose(vhod);
	fclose(izhod);
	
	return 0;
}
