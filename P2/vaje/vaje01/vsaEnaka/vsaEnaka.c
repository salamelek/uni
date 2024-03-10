# include <stdio.h>

int main() {
	int n = getchar() - '0';
	getchar();
	
	int prejsnje = 10;
	
	for (int i=0; i<n; i++) {
		if (prejsnje == 10) {
			prejsnje = getchar() - '0';
			getchar();
			continue;
		}
		
		int novo = getchar() - '0';
		
		if (novo != prejsnje) {
			putchar(0 + '0');
			return 0;
		}
		
		getchar();
		
		prejsnje = novo;
	}
	
	putchar(1 + '0');
	
	return 0;
}
