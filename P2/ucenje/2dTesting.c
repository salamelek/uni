# include <stdio.h>
# include <stdlib.h>
# include <math.h>

int main() {
	int tabela[2] = {1, 2};
	
	int** tabela2 = calloc(2, sizeof(int*));
	
	tabela2[0] = tabela;
	
	double a = sqrt(2);
	printf("%f\n", a);

	return 0;
}
