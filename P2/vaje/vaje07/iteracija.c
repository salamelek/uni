# include <stdio.h>


int vsRek(int* tab, int n) {
	if (n == 0) {
		return 0;
	}
	
	return tab[0] + vsRek(tab + 1, n - 1);
}


int vsota(int* tab) {
	return vsRek(tab, 5);
}


int main() {
	int a[5] = {1, 2, 3, 4, 5};
	int vs = vsota(a);
	
	printf("%d\n", vs);
}
