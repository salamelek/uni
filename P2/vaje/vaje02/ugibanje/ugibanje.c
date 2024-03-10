# include <stdio.h>

int main() {
	int a, b;
	
	scanf("%d%d", &a, &b);
	
	int max = b;
	int min = a;
	
	int running = 1;
	
	while (running == 1) {
		int guess = (min + max) / 2;
		
		// printf("guess: %d", guess);
		
		int resp;
		scanf("%d", &resp);
		
		if (resp == 0) {
			// Mirko wants to finish
			
			// printf("DEBUG: min: %d, max: %d\n", min, max);
			
			if (min == max) {
				// we have a number
				printf("%d\n", min);
				return 0;
			}
			
			if (max < min) {
				// how
				printf("PROTISLOVJE\n");
				return 0;
			}
			
			if (max > min) {
				// we print out the range
				printf("%d %d\n", min, max);
				return 0;
			}
		}
		
		if (resp > 0) {
			// guess is too small
			min = guess + 1;
		} else {
			// guess is too large
			max = guess - 1;
		}
	}
}
