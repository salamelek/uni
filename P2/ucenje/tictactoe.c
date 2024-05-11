# include <stdio.h>
# include <stdlib.h>
# include <stdbool.h>


void printPolje(char** polje) {
	int visina = 3;
	int sirina = 3; 
	
	for (int i=0; i<visina; i++) {
		for (int j=0; j<sirina; j++) {
			printf("%c", polje[i][j]);
			
			if (j != 2) {
				printf("|");
			}
		}
		printf("\n");
		
		if (i != 2) {
			printf("-----\n");
		}
	}
}


int main() {
	char** polje = calloc(3, sizeof(char*));
	
	bool active = true;
	char player = 'x';
	
	for (int i=0; i<3; i++) {
		char* vrstica = calloc(3, sizeof(char));
		
		for (int _=0; _<3; _++) {
			vrstica[_] = ' ';
		}
		
		polje[i] = vrstica;
	}
	
	printPolje(polje);
	
	while (active) {
		int move = -1;
		scanf("%d", &move);
		
		if (move == 1) {polje[2][0] = player;}
		if (move == 2) {polje[2][1] = player;}
		if (move == 3) {polje[2][2] = player;}
		if (move == 4) {polje[1][0] = player;}
		if (move == 5) {polje[1][1] = player;}
		if (move == 6) {polje[1][2] = player;}
		if (move == 7) {polje[0][0] = player;}
		if (move == 8) {polje[0][1] = player;}
		if (move == 9) {polje[0][2] = player;}		
		
		if (player == 'x') {player = 'o';}
		else if (player == 'o') {player = 'x';}
		
		printPolje(polje);
	}

	return 0;
}
