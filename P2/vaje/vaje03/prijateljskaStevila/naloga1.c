# include <stdio.h>

int vsotaDeliteljev(int n) {
	int vsota = 0;
	
	for (int i=1; i<n; i++) {
		if (n % i == 0) {
			vsota += i;
		}
	}
	
	return vsota;
}

int main() {
	int n;
	scanf("%d", &n);
	
	int vsota = vsotaDeliteljev(n);
	
	if (vsotaDeliteljev(vsota) == n) {
		printf("%d\n", vsota);
	} else {
		printf("NIMA\n");
	}
}
