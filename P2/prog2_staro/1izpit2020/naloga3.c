#include <stdio.h>
#include <stdlib.h>

	
int **podmnozice(int *stevila, int n, int i, int **subsets) {
	int *currSubset = (int *) malloc(n * sizeof(int));
	
	if (i == n) {
		return subsets;
	}
	
	podmnozice(stevila, n, i+1, subsets);
	
	currSubset[i] = stevila[i];
	podmnozice(stevila, n, i+1, subsets);
}


int main() {
	int n = 0;
	scanf("%d", &n);
	
	int *stevila = (int *) malloc(n * sizeof(int));
	int tmp;
	
	for (int i=0; i<n; i++) {
		scanf("%d", &tmp);
		stevila[i] = tmp;
	}
	
	int **podMnoz = (int **) malloc((2<<n) * sizeof(int *));
	
	int **subsets = podmnozice(stevila, n, 0, podMnoz);
	
	free(stevila);

	return 0;
}
