#include <stdio.h>
#include <stdlib.h>


int prestej(int *zaporedje, int currIndex, int dolzina, int depth, int vsota) {
	if (depth == 0 && vsota == 0) {
		return 1;
	}
	
	if (depth == 0 && vsota != 0) {
		return 0;
	}
	
	int tot = 0;
	
	for (int i=currIndex; i<dolzina; i++) {
		tot += prestej(zaporedje, i+1, dolzina, depth - 1, vsota - zaporedje[i]);
	}

	return tot;
}


int main() {
	int n, k, v, tmp;
	scanf("%d", &n);
	scanf("%d", &k);
	scanf("%d", &v);
	
	int *zaporedje = (int *) malloc(n * sizeof(int));
	
	for (int i=0; i<n; i++) {
		scanf(" %d", &tmp);
		zaporedje[i] = tmp;
	}
	
	int st = prestej(zaporedje, 0, n, k, v);
	printf("tot: %d\n", st);
	
	free(zaporedje);

	return 0;
}
