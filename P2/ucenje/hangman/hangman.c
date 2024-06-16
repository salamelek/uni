#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>


#define NUM_OF_STATES 10


void printHangman(int state) {
	switch (state) {
		case 0:
			printf("\n\n\n\n\n\n\n\n════════════════\n");
			break;
		case 1:
			printf("  ║\n  ║\n  ║\n  ║\n  ║\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 2:
			printf("  ╔═══════════\n  ║\n  ║\n  ║\n  ║\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 3:
			printf("  ╔══╦════════\n  ║ //\n  ║//\n  ║/\n  ║\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 4:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//\n  ║/\n  ║\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 5:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//       0\n  ║/\n  ║\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 6:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//       0\n  ║/        |\n  ║\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 7:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//       0\n  ║/        |\n  ║        /\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 8:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//       0\n  ║/        |\n  ║        / \\\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 9:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//       0\n  ║/       /|\n  ║        / \\\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		case 10:
			printf("  ╔══╦══════╦═\n  ║ //      ║\n  ║//       0\n  ║/       /|\\\n  ║        / \\\n  ║\n  ║\n  ║\n══╩═════════════\n");
			break;
		default:
			printf("What :|\n");
			break;
	}
}


bool isInArr(char letter, char *arr, int arrLen) {
	for (int i=0; i<arrLen; i++) {
		if (arr[i] == letter) {
			return true;
		}
	}
	
	return false;
}


void printWord(char *chosenWord, char *guessedLetters, int wordLen, int turn, char *wl, int wlCount) {
	// clear screen
	// system("clear");
	printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

	// print hangman
	printHangman(turn);

	// print dashed word
	for (int i=0; i<wordLen; i++) {
		if (isInArr(chosenWord[i], guessedLetters, wordLen)) {
			printf("%c", chosenWord[i]);
		} else {
			printf("_");
		}
		
		if (i < wordLen - 1) {
			printf(" ");
		} else {
			printf("\n");
		}
	}
	
	// print wrong letters
	printf("Wrong letters: ");
	
	for (int i=0; i<wlCount; i++) {
		printf("%c", wl[i]);
		
		if (i < wlCount - 1) {
			printf(", ");
		} else {
			printf("\n");
		}
	}
	
	printf("\n\n");
}


bool hasGuessedWord(char *guessedLetters, char *word, int wordLen) {
	for (int i=0; i<wordLen; i++) {
		if (!(isInArr(word[i], guessedLetters, wordLen))) {
			return false;
		}
	}
	
	return true;
}


int countWordsInFile(FILE *file) {
	int numOfWords = 0;
	char currChar;
	
	while ((currChar = fgetc(file)) != EOF) {
		if (currChar == '\n') {
			numOfWords++;
		}
	}
	
	rewind(file);
	
	return numOfWords;
}


char *getRandomWord(char *fileName) {
	FILE *file = fopen(fileName, "r");

	int numOfWords = countWordsInFile(file);
	int randRow = rand() % numOfWords;
	
	// get word at randRow
	char *word = (char *) malloc(25 * sizeof(char));

	for (int i=0; i<randRow; i++) {
		fgets(word, 25, file);
	}	
	
	fclose(file);
	
	word[strlen(word) - 1] = '\0';
	
	return word;
}


int main() {
	// set random seed
	srand(time(NULL));

	
	int lang = 0;
	
	while (lang < 1 || lang > 2) {	
		printf("Select language (ita: 1, eng: 2): \n");
		scanf(" %d", &lang);
		
		// clear buffer
		while (getchar() != '\n');
	}
	
	char *fileName;
	
	switch (lang) {
		case 1:
			fileName = "ita.txt";
			break;
		case 2:
			fileName = "eng.txt";
			break;
		default:
			printf("[Error]: invalid language!");
			return 1;
	}

	char *chosenWord = getRandomWord(fileName);
	int chosenWordLen = strlen(chosenWord);
	
	
	// char *guessedLetters = (char *) malloc(chosenWordLen * sizeof(char));
	char *guessedLetters = (char *) calloc(chosenWordLen, sizeof(char));
	char *wrongLetters = (char *) calloc(26, sizeof(char));
	
	int glCount = 0;
	int wlCount = 0;
	
	// main game loop
	bool alive = true;
	int turn = 0;
	char chosenLetter = 0;
	
	while (alive) {
		printWord(chosenWord, guessedLetters, chosenWordLen, turn, wrongLetters, wlCount);
		
		// get a valid letter
		while (
			chosenLetter < 'a' || 
			chosenLetter > 'z' || 
			isInArr(chosenLetter, guessedLetters, chosenWordLen) || 
			isInArr(chosenLetter, wrongLetters, 26)
		) {
			printf("Choose a letter (a-z): ");
			scanf(" %c", &chosenLetter);
		}
		printf("\n");
		
		if (isInArr(chosenLetter, chosenWord, chosenWordLen)) {
			guessedLetters[glCount] = chosenLetter;
			glCount++;
		} else {
			wrongLetters[wlCount] = chosenLetter;
			wlCount++;
			turn++;
		}
		
		// check if guessed
		if (hasGuessedWord(guessedLetters, chosenWord, chosenWordLen)) {
			break;
		}
		
		if (turn >= NUM_OF_STATES) {
			alive = false;
			break;
		}
		
		chosenLetter = 0;
	}
	
	// final print
	printWord(chosenWord, guessedLetters, chosenWordLen, turn, wrongLetters, wlCount);
	
	// end screen
	if (alive) {
		printf("Congratulations! You won!\n");
	} else {
		printf("Womp Womp... The word was %s\n", chosenWord);
	}
	
	
	// free everything
	free(chosenWord);
	free(guessedLetters);
	free(wrongLetters);

	return 0;
}







