# include <stdio.h>
# include <math.h>		// when compiling, use the flag -lm
# include <stdbool.h>	// to use bools

int main() {
	int m, n;
	scanf("%d%d", &m, &n);		// & je pointer na nekaj (&a je pointer na a)
	
	int counter = 0;
	int alreadyDone = 0;
	
	// for each number in the given range
	for (int c=m; c<=n; c++) {
		for (int a=1; a<c; a++) {
			int b = sqrt(c*c - a*a);
			
			if (a*a + b*b == c*c) {
				counter++;
				break;
			}
		}
	}
	
	printf("%d\n", counter);

	return 0;
}
