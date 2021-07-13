import java.io.*;

public class parser {
    static final int MAX_LEXEME_LENGTH = 100;
    static final int MAX_TOKEN_LENGTH = 100;
    static int charClass;
    static char[] lexeme = new char[MAX_LEXEME_LENGTH];
    static char nextChar;
    static int lexLen;
    static int nextToken;
    static FileReader file;
    static BufferedReader reader;
    static final int LETTER = 0;
    static final int DIGIT = 1;
    static final int UNKNOWN = 99;
    static String[] token_dict = new String[MAX_TOKEN_LENGTH];
    static final int INT_LIT = 10;
    static final int IDENT = 11;
    static final int ASSIGN_OP = 20;
    static final int ADD_OP = 21;
    static final int SUB_OP = 22;
    static final int MULT_OP = 23;
    static final int DIV_OP = 24;
    static final int LEFT_PAREN = 25;
    static final int RIGHT_PAREN = 26;
    static final int END_OF_FILE = 98;

    public static void read_file(FileReader file) {
        reader = new BufferedReader(file);

        if (file == null){
            System.out.println("ERROR - cannot open codes.txt");
        }else{
            try {
                while(reader.ready()){
                    getChar();
                    do
                    {
                        lex();
                        assign();
                    }while(nextToken != END_OF_FILE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("%s\t+\t", token_dict[nextToken]);
        }
    }

    static int lookup(char ch){
        switch (ch) {
            case '(':
                addChar();
                nextToken = LEFT_PAREN;
                System.out.printf("<Expr>\n");
                break;

            case ')':
                addChar();
                nextToken = RIGHT_PAREN;
                System.out.printf("<Expr>\n");
                break;

            case '+':
                addChar();
                nextToken = ADD_OP;
                System.out.printf("<Term>\n");
                break;

            case '-':
                addChar();
                nextToken = SUB_OP;
                System.out.printf("<Term>\n");
                break;

            case '*':
                addChar();
                nextToken = MULT_OP;
                System.out.printf("<Factor>\n");
                break;

            case '/':
                addChar();
                nextToken = DIV_OP;
                System.out.printf("<Factor>\n");
                break;

            case '=':
                addChar();
                nextToken = ASSIGN_OP;
                System.out.printf("->\n");
                break;

            default:
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 0;
                lexLen = 4;
                nextToken = END_OF_FILE;
                break;
        }
        return nextToken;
    }

    static void addChar(){

        if (lexLen <= 98)
        {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        } else
            System.out.println("Error - lexeme is too long \n");
    }

    static void getChar(){
        char c = 0;

        try {c = (char)reader.read();}
        catch (IOException e) {e.printStackTrace();}

        nextChar = c;

        if((int)nextChar != 0){
            if(isalpha(nextChar)){
                charClass = LETTER;
            }else if(isdigit(nextChar)){
                charClass = DIGIT;
            }else{ charClass = UNKNOWN;}
        }else{
            charClass = END_OF_FILE;
        }
    }

    static void getNonBlank(){
        while(isspace(nextChar)){
            getChar();
        }
    }

    public static int lex(){
        lexLen = 0;
        lexeme = new char[100];
        getNonBlank();
        switch (charClass){

            case LETTER:
                addChar();
                getChar();
                while(charClass == LETTER || charClass == DIGIT){
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;

            case DIGIT:
                addChar();
                getChar();
                while(charClass == DIGIT){
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;

            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;

            case END_OF_FILE:
                nextToken = END_OF_FILE;;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 0;
                lexLen = 4;
                break;
        }
        if (nextToken != END_OF_FILE) {
            String s = new String(lexeme);
            s = s.substring(0,lexLen);
            System.out.printf("%s\t+\t%s\n", token_dict[nextToken], s);}
        return nextToken;
    }

    static boolean isalpha(char c) {
        int ascii = (int) c;

        if((ascii > 64 && ascii < 91) || (ascii > 96 && ascii < 123)) {return true;}
        else {return false;}
    }

    static boolean isdigit(char c) {
        int ascii = (int) c;

        if(ascii > 47 && ascii < 58){return true;}
        else {return false;}
    }

    static boolean isspace(char c) {
        int ascii = (int) c;

        if(ascii == 32){return true;}
        else {return false;}
    }

    // <assign> -> id = <expr>
    static void assign() throws IOException {
        if (nextToken == IDENT)
            lex();

        if(nextToken == ASSIGN_OP) {
            lex();
            System.out.printf("<Expr>\n");
            expr();
        }
    }
    /*
     Parses strings in the language generated by the rule:
     <expr> -> <term> {(+ | -) <term>}
     */
    static void expr() throws IOException {
        term();
        System.out.printf("<Term>\n");
	/* As long as the next token is + or -, get
	 the next token and parse the next term */
        while (nextToken == ADD_OP || nextToken == SUB_OP) {
            lex();
            System.out.printf("<Term>\n");
            term();
        }
    }

    //Parses strings in the language generated by the rule:
    //<term> -> <factor> {(* | /) <factor>)}
    static void term() throws IOException {
        factor();
	/* As long as the next token is * or /, get the
	 next token and parse the next factor */
        while (nextToken == MULT_OP || nextToken == DIV_OP) {
            lex();
            factor();
            System.out.printf("<Factor>");
        }
    }

    static void factor() throws IOException {
        if (nextToken == IDENT || nextToken == INT_LIT) {System.out.printf("<Factor>\n");lex();}
	/* If the RHS is ( <expr>), call lex to pass over the
	 left parenthesis, call expr, and check for the right
	 parenthesis */
        else {
            System.out.printf("\n");
            if (nextToken == LEFT_PAREN) {
                lex();
                System.out.printf("<Expr>\n");
                expr();

                if (nextToken == RIGHT_PAREN) {lex();}
                else {System.out.println("ERROR: Does not have RIGHT_PAREN");}
            }
            else {System.exit(0);}
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        token_dict[INT_LIT] = "INT_LIT\t";
        token_dict[IDENT] = "IDENT\t";
        token_dict[ASSIGN_OP] = "ASSIGN_OP";
        token_dict[ADD_OP] = "ADD_OP\t";
        token_dict[SUB_OP] = "SUB_OP\t";
        token_dict[MULT_OP] = "MULT_OP\t";
        token_dict[DIV_OP] = "DIV_OP\t";
        token_dict[LEFT_PAREN] = "LEFT_PAREN";
        token_dict[RIGHT_PAREN] = "RIGHT_PAREN";
        token_dict[END_OF_FILE] = "";
        file = new FileReader("src\\codes.txt");

        read_file(file);
    }
}