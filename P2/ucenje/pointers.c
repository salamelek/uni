void foo(int** p) {
	*p = (int*)0xFEE;
}


void foo2(int* p) {
	p = (int*)0xBEEF;
}


#include <iostream>

int main() {
	int a = 100;
	
	int* b = &a;
	// b je pointer na a
	
	std::cout << b << std::endl;
    foo(&b);
    std::cout << b << std::endl;
    // b = 0xFEE (pointer)
	
	foo2(b);
	std::cout << b << std::endl;
    // b = 0xFEE (pointer)
	

	return 0;
}
