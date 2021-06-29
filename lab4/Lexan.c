
/* Fullexan.c - a lexical analyzer system for simple arithmetic expressions */
// C related files
#include <stdio.h>
#include <ctype.h>

// lexan.c related file of constants
#include "lexan.h"

/* Global Variable */
int nextToken;

/* Local Variables */
static int charClass;
static char lexeme [100]; // Buffer to build lexemes
static char nextChar;
static int lexLen;
static FILE *in_fp;	// C runtime. File descriptor

/* Local Function declarations */
static void addChar();
static void getChar();
static void getNonBlank();

/******************************************************/
/* Logic for the main driver */
int main() 
{
    /* Open the input data file and process its contents */
 
    if ((in_fp = fopen("front.txt", "r")) == NULL) { // C code
        printf("ERROR - cannot open front.txt \n");
    } else {
        getChar();
        do {
            lex();
        } while (nextToken != EOF);
    }

    return 0;
}

/***************************************************************/
/* lex - a simple lexical analyzer for arithmetic expressions */
/**************************************************************/
int lex() {
    lexLen = 0;
    Skip the Blanks.

    switch on charClass {
        /* Parse identifiers */
         If a LETTER, add it to the lexeme buffer (build the identifier)
         	Get the next character  

         	while charClass == LETTER or charClass == DIGIT
	 		Add the character to the lexeme buffer
           
         	nextToken = IDENT;
         	leave   

        /* Parse integer literals */
	If a DIGIT, add it to the lexeme buffer (build the integer)
		Get the next character
		while charClass == DIGIT
			Add the character to the buffer
                 nextToken = INT_LIT;
            	 leave

        /* Parentheses and operators */
        if an UNKNOWN, check which character it is by calling lookup
        	Get the next character.
		leave 
        
        /* EOF */
        If it is EOF, set nextToken to EOF and build the lexeme with EOF
            lexeme[0] = 'E';
            lexeme[1] = 'O';
            lexeme[2] = 'F';
            lexeme[3] = 0;     
	    leave
    } 

    printf("Next token is: %d, Next lexeme is %s\n", nextToken, lexeme);
    return nextToken;
} /* End of function lex */


/*****************************************************/
/* lookup - a function to lookup operators and parentheses and return the 
 * token */
static int lookup(char ch) {

	if input ch is '(' add the character to lexeme
	set nextToken to the LEFT_PAREN code
	leave

	if ch is ')' add the character to lexeme
	set nextToken to the RIGHT_TOKEN code 
	leave

	if ch is '+', add the character to lexeme
	set nextToken to ADD_OP
	leave
   
	if ch is '-', add the character to lexeme
	set nextToken to ADD_OP
	leave

	if ch is '*', add the character to lexeme
	set nextToken to MULT_OP
	leave

	if ch is '/', add the character to lexeme
	set nextToken to DIV_OP
	leave

	else add the character to lexeme
	set nextToken to EOF
	leave
 
    return nextToken;
}

/*****************************************************/
/* addChar - a function to add nextChar to lexeme 
	Make sure the lexeme does not overflow the buffer
*/
static void addChar() {

    if the length of the lexeme (lexlen) is >= 98
	store the character (nextChar) in the buffer 'lexeme' 
	Increment the length
	set 0 in the buffer (CLEAR IT)
    else 
        printf("Error - lexeme is too long \n");
}

/*****************************************************/
/* getChar - a function to get the next character of input and 
 	determine its character class
 */
static void getChar() {
    Read next character from file and assign it to nextChar 
    if it is not EOF
        if nextChar is alpha (isalpha()), 
		set charClass to LETTER
    	else if nextChar is digit (isdigit()),
		set charClass to DIGIT
             else charClass is UNKNOWN
    else
	charClass is EOF
  
}

/*****************************************************/
/* getNonBlank - a function to call getChar until it returns a 
	non-whitespace character (skip spaces)
*/
static void getNonBlank() {
    while nextChar is a space, keep reading input (getChar)
}

