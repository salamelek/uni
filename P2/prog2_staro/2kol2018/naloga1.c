#include <stdio.h>
#include <stdlib.h>


typedef struct _Vozlisce {
	struct _Vozlisce* naslednje;
} Vozlisce;


int steviloElementov(Vozlisce* p) {
	if (p->naslednje == NULL) {
		return -1;
	}

	Vozlisce *temp = p;
	int counter = 1;

	while (temp->naslednje != p) {
		counter++;
		temp = temp->naslednje;
	}
	
	return counter;
}


Vozlisce *novo(Vozlisce *naslednje) {
	Vozlisce *novo = (Vozlisce *) malloc(sizeof(Vozlisce));
	novo->naslednje = naslednje;

	return novo;
}


int main() {
	Vozlisce *a = novo(NULL);
	Vozlisce *b = novo(a);
	
	/*Vozlisce *c = novo(b);
	Vozlisce *d = novo(c);
	Vozlisce *e = novo(d);
	Vozlisce *f = novo(e);
	Vozlisce *g = novo(f);
	*/
	a->naslednje = b;
	
	int num = steviloElementov(a);
	printf("%d\n", num);

	return 0;
}
