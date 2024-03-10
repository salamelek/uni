# include <stdio.h>

int main() {
	int a = getchar() - '0';
	
	// preberemo nič, da gremo naprej za presledek ali newline
	getchar();
	
	int b = getchar() - '0';
	
	
	int vsotaPoModulu = (a + b) % 10; 
	
	printf("%d\n", vsotaPoModulu);
	return 0;
}
