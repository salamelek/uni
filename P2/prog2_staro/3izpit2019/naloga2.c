#include <stdio.h>
#include <stdlib.h>


void printSlope(char l) {
	if (l == 'G') {
		printf("/");
	} else {
		printf("\\");
	}
}


int main() {
	int n = 0;
	scanf("%d", &n);
	
	char *crke = (char *) malloc(n * sizeof(char));
	char tmp;
	char prev;
	
	int height = 0;
	int maxHeight = 0;
	int minHeight = 0;
	
	for (int i=0; i<n; i++) {
		scanf("%c", &tmp);
		
		
		if (i == 0) {
			crke[i] = tmp;
			prev = tmp;
			continue;
		}
		
		if (tmp == 'G' && prev == 'G') {
			height++;
		}
		if (tmp == 'D' && prev == 'D') {
			height--;
		}
		
		if (height > maxHeight) {
			maxHeight = height;
		}
		
		if (height < minHeight) {
			minHeight = height;
		}
	
		crke[i] = tmp;
		prev = tmp;
	}
	
	// printf("max: %d min: %d\n",maxHeight, minHeight);
	
	
	// vrstice
	for (int i=maxHeight; i>=minHeight; i--) {
		int currH = 0;
	
		// crke
		for (int j=0; j<n; j++) {
			if (j>0) {
				if (crke[j] == 'G' && crke[j-1] == 'G') {
					currH++;
				}
				
				if (crke[j] == 'D' && crke[j-1] == 'D') {
					currH--;
				}
			}
				
			if (currH == i) {
				printSlope(crke[j]);
			} else {
				printf(".");
			}
		}
		
		printf("\n");
	}
	
	
	free(crke);

	return 0;
}
