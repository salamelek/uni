#include <stdio.h>
#include <string.h>
#include <stdlib.h>


int indMin(int* sez, int len) {
	int stMin = sez[0];
	int indMin = 0;

	for (int i=1; i<len; i++) {
		if (sez[i] < stMin) {
			stMin = sez[i];
			indMin = i;
		}
	}
	
	return indMin;
}


int main(int argc, char** argv) {
	int n;
	
	scanf("%d", &n);
	
	// seznam vhodov
	FILE** vhodi = (FILE**) malloc(n * sizeof(FILE*));
	
	for (int i=0; i<n; i++) {
		// dobi ime
		char* ime = (char*) malloc(100 * sizeof(char));
		scanf("%s", ime);
		
		// shrani stream
		FILE* dat = fopen(ime, "r");
		vhodi[i] = dat;
		
		free(ime);
	}
	
	
	
	// izhod
	FILE* izhod = fopen("", "");
	
	// buffer
	int* buffer = (int*) malloc(n * sizeof(int));
	
	// koliko koncanih trakov je
	int stKoncanih = 0;
	
	for (int i=0; i<n; i++) {
		if (fscanf(vhodi[i], "%d", &buffer[i]) < 1) {
			buffer[i] = -1;
			stKoncanih++;
		}
	}
	
	while (stKoncanih < n) {
		// poiscemo najmanjsi v bufferju in ga napisemo
		int iMin = indMin(buffer, n);
		fprintf(izhod, "%d\n", buffer[iMin]);
		
		if (fscanf(vhodi[iMin], "%d", &buffer[iMin]) < 1) {
			buffer[iMin] = -1;
			stKoncanih++;
		}
	}
	
	
	// zapri vse stream
	for (int i=0; i<n; i++) {
		fclose(vhodi[i]);
	}
	
	// free vse
	free(vhodi);
	free(buffer);

    return 0;
}
