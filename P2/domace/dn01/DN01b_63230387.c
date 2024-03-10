# include <stdio.h>

int main() {
	int running = 1;

	int first1index = -1;
	int counter = 0;
	while (running == 1) {
		int digit = getchar() - '0';
		
		if (digit != 1 && digit != 0) {
			running = 0;
			break;
		}
		
		if (digit == 1 && first1index == -1) {
			first1index = counter;
		}
		
		counter++;
	}
	
	int log2 = counter - first1index;
	
	if (log2 < 10) {
		putchar(log2 + '0');
	} else {
		putchar((log2 / 10) + '0');
		putchar((log2 % 10) + '0');
	}
	
	putchar('\n');

	return 0;
}
