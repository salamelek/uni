# include <stdio.h>

int main() {
	int a = 0;
	int b = 0;
	int neg = 1;
	
	while (1 == 1) {
		char input = getchar();
		
		if (input == '-') {
			// treba je pomnožiti število z -1
			neg = -1;
			continue;
		}
		
		if (input == ' ') {
			break;
		}
		
		a *= 10;
		a += input - '0';
	}
	
	a *= neg;
	neg = 1;
	
	while (1 == 1) {
		char input = getchar();
		
		if (input == '-') {
			// treba je pomnožiti število z -1
			neg = -1;
			continue;
		}
		
		if (input == '\n') {
			break;
		}
		
		b *= 10;
		b += input - '0';
	}
	
	b *= neg;
	
	int sum = a + b;
	int countSum = sum;
	int digitCounter = 1;
	
	// how many digits does the sum have
	while (countSum / 10 != 0) {
		digitCounter++;
		countSum /= 10;
	}
	
	// če je neg, puttaj -
	if (sum < 0) {
		putchar('-');
		sum *= -1;
	}
	
	
	int ost = 0;
	
	for (int i=digitCounter - 1; i>=0; i--) {
		// pow 10
		int p10 = 1;
		for (int j=0; j<i; j++) {
			p10 *= 10;
		}
	
		char digit = sum / p10;
		
		putchar(digit + '0');
		sum -= digit * p10;
	}
	
	putchar('\n');

	return 0;
}
