import java.io.FileNotFoundException;
import Java.io.File;

public class charClass {

}

public class Lexan {
    private int nextToken = 0;
    private static int charClass, lexLen;
    private static char[] lexeme = new char[100];
    private static char nextChar;
    private static java.io.File in_fp;

    public static void addChar() {

    }

    public static void getChar() {

    }

    public static void getNonBlank() {

    }

    public int lex () {
        lexLen = 0;
    }

    public static void main(String[] args) {
        
        try {
            in_fp = new File("C:/Users/Vj/Google Drive/College/3 - Junior/Summer of 2021/Programming Languages/Labs/Lab 4/front.txt");
            getChar();

            do {
                lex();
            } while(nextToken != in_fp.length());
        }

        catch (FileNotFoundException ex) {System.out.print("ERROR - cannot open front.txt" + ex);}
    }
}