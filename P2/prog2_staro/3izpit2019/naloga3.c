#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>


bool tiles[1001];


// 			  12 		4      6       2       0
int prestej(int n, int k1, int k2, int s, int pos) {
	int tot = 0;
	
	// end
	if (s == 0) {
		if (tiles[pos] == true) {
			tiles[pos] = false;
			return 1;
		} else {
			return 0;
		}
	}

	// k1 naprej
	if (pos + k1 < n) {
		if (tiles[pos] == true) {
			tiles[pos] = false;
			tot++;
		}
		
		//tiles[pos + k1] = false;
		tot += prestej(n, k1, k2, s - 1, pos + k1);
	}
	
	// k1 nazaj
	if (pos - k1 >= 0) {
		if (tiles[pos] == true) {
			tiles[pos] = false;
			tot++;
		}
		
		//tiles[pos - k1] = false;
		tot += prestej(n, k1, k2, s - 1, pos - k1);
	}
	
	// k2 naprej
	if (pos + k2 < n) {
		if (tiles[pos] == true) {
			tiles[pos] = false;
			tot++;
		}
		
		//tiles[pos + k2] = false;
		tot += prestej(n, k1, k2, s - 1, pos + k2);
	}
	
	// k2 nazaj
	if (pos - k2 >= 0) {
		if (tiles[pos] == true) {
			tiles[pos] = false;
			tot++;
		}
		
		//tiles[pos - k1] = false;
		tot += prestej(n, k1, k2, s - 1, pos - k2);
	}

	return tot;
}


int main() {
	int n, k1, k2, s;
	scanf("%d %d %d %d", &n, &k1, &k2, &s);
	
	for (int i=0; i<1001; i++) {
		tiles[i] = true;
	}
	tiles[0] = false;
	
	printf("%d\n", prestej(n, k1, k2, s, 0) + 1);

	return 0;
}
