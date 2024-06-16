#include <stdio.h>
#include <string.h>
#include <stdlib.h>


int main() {
	char str[] = "Hello world this is imposter... amogus!";
	char delim = ' ';
	
	char *token = strtok(str, &delim);
	char **words = (char **) malloc(10 * sizeof(char *));
	int count = 0;
	
	while (token != NULL) {		
		char *word = (char *) malloc(50 * sizeof(char));
		strcpy(word, token);
		
		words[count] = word;
		
		
		token = strtok(NULL, &delim);
		count++;
	}
	
	
	
	char *newStr = (char *) malloc(10 * 50 * sizeof(char));
	newStr[0] = '\0';
	
	for (int i=count-1; i>=0; i--) {
		strcat(newStr, words[i]);
		strcat(newStr, " ");
	}
	
	
	printf("%s\n", newStr);

    return 0;
}

