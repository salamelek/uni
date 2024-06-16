#include <stdio.h>
#include <stdlib.h>


char *getWhite(int n) {
	switch (n) {
		case 1:
			return "██";
			break;
		case 2:
			return "████████";
			break;
		case 3:
			return "████████████████████████████████";
			break;
	}
	
	return "hmmm";
}


char *printPatternRec(int n) {
	if (n == 0) {
		return "░░";
	}
	
	char *tmp = (char *) malloc(128 * sizeof(char));
	
	sprintf(tmp, "%s%s%s%s", printPatternRec(n-1), printPatternRec(n-1), getWhite(n), printPatternRec(n-1));
	
	return tmp;
}


int main(int argc, char **args) {
	int n = args[1][0] - '0';
	
	
	char *a = printPatternRec(n);
	
	printf("%s\n", a);


/*
	int pow2 = 2 << n;
	int velikost = pow2 / 2;
	
	for (int i=0; i<velikost; i++) {
		for (int j=0; j<velikost; j++) {
			// 1 - 2⁰
			if (i == 0  && j % pow2 >= i) {printf("░░");} else
			
			// 2 - 2¹
			if (i == 1  && j % pow2 >= i) {printf("░░");} else
			
			// 4 - 2²
			if (i == 2  && j % pow2 >= i) {printf("░░");} else
			if (i == 3  && j % pow2 >= i) {printf("░░");} else
			
			// 8 - 2³
			if (i == 4  && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 5  && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 6  && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 7  && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			// 16
			if (i == 8  && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 9  && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 10 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 11 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 12 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 13 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 14 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 15 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			// 32
			if (i == 16 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 17 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 18 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 19 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 20 && j % pow2 >= i && j % 1 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			if (i == 21 && j % pow2 >= i && j % 2 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			if (i == 22 && j % pow2 >= i && j % 4 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			if (i == 23 && j % pow2 >= i && j % 4 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			
			if (i == 24 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 25 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 26 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 27 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 28 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 29 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 30 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 31 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			// 64
			if (i == 32 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 33 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 34 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 35 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 36 && j % pow2 >= i && j % 1 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			if (i == 37 && j % pow2 >= i && j % 2 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			if (i == 38 && j % pow2 >= i && j % 4 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			if (i == 39 && j % pow2 >= i && j % 4 >= i % 4 && j % 8 >= 4) {printf("░░");} else
			
			if (i == 40 && j % pow2 >= i && j % 1 >= i % 4 && j % 16 >= 8) {printf("░░");} else
			if (i == 41 && j % pow2 >= i && j % 2 >= i % 4 && j % 16 >= 8) {printf("░░");} else
			if (i == 42 && j % pow2 >= i && j % 4 >= i % 4 && j % 16 >= 8) {printf("░░");} else
			if (i == 43 && j % pow2 >= i && j % 4 >= i % 4 && j % 16 >= 8) {printf("░░");} else
			
			if (i == 44 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 45 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 46 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 47 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 48 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 49 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 50 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 51 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 52 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 53 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 54 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 55 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 56 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 57 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 58 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 59 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			if (i == 60 && j % pow2 >= i && j % 1 >= i % 4) {printf("░░");} else
			if (i == 61 && j % pow2 >= i && j % 2 >= i % 4) {printf("░░");} else
			if (i == 62 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			if (i == 63 && j % pow2 >= i && j % 4 >= i % 4) {printf("░░");} else
			
			
			{printf("██");}
		
			
		}
		
		printf("\n");
	}
	
	*/

	return 0;
}
