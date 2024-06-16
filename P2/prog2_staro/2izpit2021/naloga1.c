#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main() {
	char *num = (char *) malloc(1500 * sizeof(char));
	scanf("%s", num);
	
	int len = strlen(num);
	int stPik = (len-1) / 3;
	int stPrv = (len-1) % 3 + 1;
	
	
	for(int i=0; i<stPrv; i++) {
		printf("%c", num[i]);
	}
	
	if (stPik > 0) {
		printf(".");
	}
	
	int i = stPrv;
	while (stPik > 0) {
		for(int j=0; j<3; j++) { // per pata
			printf("%c", num[i+j]);
		}
		
		stPik--;
		if (stPik > 0) {
			printf(".");	
		}
		i++;
	}	
	
	printf("\n");
	
	return 0;
}
