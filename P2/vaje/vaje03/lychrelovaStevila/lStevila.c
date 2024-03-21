# include <stdio.h>
# include <math.h>

int jePalindrom(int st) {
	// dobi dolzino stevila
	for (int i=0; i<7; i++) {
		if (st < pow(10, i)) {
			continue;
		}
		
		printf("%d", pow(10, i));
		
		return i;
	}
/*
	int st2 = st;
	
	while (st2 / 10 != 0) {
		lastDigit = st % 10;
	
		st2 /= 10;
	}
	*/
	return 0;
}

int main() {
	int k, a, b;
	// scanf("%d %d %d", &k, &a, &b);
	scanf("%d", &a);
	
	int je = jePalindrom(a);
	
	printf("%d\n", je);
	
	return 0;
}
