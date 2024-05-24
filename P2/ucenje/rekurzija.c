# include <stdio.h>

void fibRek(int n, long long a, long long b) {
	if (n == 0) {
		return;
	}
	
	printf("%d: %lld\n", n, a);
	
	fibRek(n-1, b, a+b);
}

int main() {
	fibRek(100, 0, 1);
	
	/*
	for (int i=0; i<=99; i++) {
		long long res = 1;
	
		for (int j=1; j<=i; j++) {
			res *= j;
		}
		
		printf("%d! = %lld\n", i, res);
	}
	*/

	return 0;
}
