import java.util.Scanner;

public class Parser {
    private static Scanner input;
    public static String toEvaluate = "8 + 2 * (5-2)";
    public static char[] toParse;
    private static int lexLen;
    private static char nextChar;
    private static char lexeme[];
    
    private static final int LETTER = 0;
    private static final int DIGIT = 1;
    private static final int UNKNOWN = 99;

    private static final int INT_LIT = 10;
    private static final int IDENT = 11;
    private static final int ASSIGN_OP = 20;
    private static final int ADD_OP = 21;
    private static final int SUB_OP = 22;
    private static final int MULT_OP = 23;
    private static final int DIV_OP = 24;

    private static final int LEFT_PAREN = 25;
    private static final int RIGHT_PAREN = 26;

    private static int lookup(char ch){
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                break;

            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                break;

            case '+':
                addChar();
                nextToken = ADD_OP;
                break;

            case '-':
                addChar();
                nextToken = SUB_OP;
                break;

            case '*':
                addChar();
                nextToken = MULT_OP;
                break;

            case '/':
                addChar();
                nextToken = DIV_OP;
                break;

            case '=':
                addChar();
                nextToken = ASSIGN_OP;
                break;

            default:
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 0;
                lexLen = 4;
                nextToken = 'END_OF_FILE';
                break;
        }
        return nextToken;
    }

    public static void addChar(){
        if (lexLen <= 98) {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        } 
        else {System.out.println("Error - lexeme is too long \n");}
    }

    static boolean isAlpha(char c){
        int ascii = (int) c;

        if((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)){return true;}
        else {return false;}
    }

    static boolean isDigit(char c){
        int ascii = (int) c;

        if(ascii > 47 && ascii < 58){return true;}
        else {return false;}
    }

    static boolean isSpace(char c){
        int ascii = (int) c;

        if(ascii == 32){return true;}
        else {return false;}
    }

        //Assign Function
    // <assign> -> id = <expr>
    static void assign() throws IOException {
        if (nextToken == IDENT)
            lex();

        if(nextToken == ASSIGN_OP);
        {
            lex();
            expr();
        }
    }
    /*
     Parses strings in the language generated by the rule:
     <expr> -> <term> {(+ | -) <term>}
     */
    static void expr() throws IOException {
        /* Parse the first term */
        term();
	/* As long as the next token is + or -, get
	 the next token and parse the next term */
        while (nextToken == ADD_OP || nextToken == SUB_OP) {
            lex();
            term();
        }
    } /* End of function expr */

    //Parses strings in the language generated by the rule:
    //<term> -> <factor> {(* | /) <factor>)}

    static void term() throws IOException {
        /* Parse the first factor */
        factor();
	/* As long as the next token is * or /, get the
	 next token and parse the next factor */
        while (nextToken == MULT_OP || nextToken == DIV_OP)
        {
            lex();
            factor();
        }
    } /* End of function term */

    static void factor() throws IOException {
        /* Determine which RHS */
        if (nextToken == IDENT || nextToken == INT_LIT)
            /* Get the next token */
            lex();

	/* If the RHS is ( <expr>), call lex to pass over the
	 left parenthesis, call expr, and check for the right
	 parenthesis */
        else {
            if (nextToken == LEFT_PAREN) {
                lex();
                expr();

                if (nextToken == RIGHT_PAREN)
                    lex();
                else
                    System.out.println("ERROR: Does not have RIGHT_PAREN");
            } /* End of if (nextToken == ... */
	/* It was not an id, an integer literal, or a left
	 parenthesis */
            else {
                System.exit(0);
            }
        } /* End of else */
    } 
    
    public static void readExpression() {
        for (int pos = 0; pos < toEvaluate.lenght(); pos++) {
            toParse[pos] = toEvaluate[pos];
        }
    }

    public static void main(String[] args) {
        readExpression(); // filling up the array of characters with the expression to parse


    }
}