#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "naloga.h"


int poisciStudenta(Student** studentje, int stStudentov, int vpisna) {
	for (int i=0; i<stStudentov; i++) {
		int vpisnaTmp = studentje[i]->vpisna;
		
		if (vpisnaTmp == vpisna) {
			return i;
		}
	}

    return -1;
}


int poisciPO(Student* student, char* predmet) {
	PO* predmetiOcena = student->po;
	
	int i = 0;
	while (predmetiOcena[i].ocena != 0) {
		if (!strcmp(predmetiOcena[i].predmet, predmet)) {
			return i;
		}
	
		i++;
	}

    return -1;
}

Student* ustvariStudent(int vpisna, int stPo) {
	Student* stud = (Student*) malloc(sizeof(Student));
	
	stud->vpisna = vpisna;
	stud->po = NULL;
	stud->stPO = stPo;
	
	return stud;
}

int dodaj(Student** studentje, int stStudentov, int vpisna, char* predmet, int ocena) {
	// preveri ce student obstaja
	int indeksStudenta = poisciStudenta(studentje, stStudentov, vpisna);
	
	if (indeksStudenta == -1) {
		// student ne obstaja
		
		// ustvarimo studenta
		Student* student = ustvariStudent(vpisna, 1);
		
		// ustvarimo seznam po-jev
		student->po = (PO*) malloc(10 * sizeof(PO));
		
		// ustvarimo PO
		// char* strcpy(char* dest, const char* src)
		strcpy(student->po[0].predmet, predmet);
		student->po[0].ocena = ocena;
		
		// dodamo studenta in inkrementiramo stStudentov
		studentje[stStudentov++] = student;				
	} else {
		// student ze obstaja
		
		Student* student = studentje[indeksStudenta];
		
		// pogledamo indeks po
		int indeksPo = poisciPO(student, predmet);
		
		if (indeksPo == -1) {
			// treba napisati predmet in oceno
			int naslednjiIndex = student->stPO;
			
			// vpisemo
			student->po[naslednjiIndex].ocena = ocena;
			strcpy(student->po[naslednjiIndex].predmet, predmet);
			
			// inkrementiramo stPO
			student->stPO++;
			
		} else {
			// treba vpisati novo oceno
			student->po[indeksPo].ocena = ocena;
			
		}
	}
	
	return stStudentov;
}


#ifndef test

int main() {

    return 0;
}

#endif
