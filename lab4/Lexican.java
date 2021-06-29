import java.io.FileNotFoundException;
import Java.io.File;
import java.util.Scanner;

public class Lexan {
    public Scanner reader;
    private int nextToken = 0;
    private static int charClass, lexLen;
    private static char[] lexeme = new char[100];
    private static char nextChar;
    private static File in_fp = new File("front.txt");

    public static void addChar() {

    }   

    public static void getChar() {
        int i = 0;
        reader = new Scanner(in_fp);
        
        while (reader.hasNext()) {
            nextChar += reader.next().charAt(i);
            i++;
        }
    }

    public static void getNonBlank() {

    }

    public int lex () {
        lexLen = 0;
    }

    public static void main(String[] args) {
        try {
            getChar();

            do {
                lex();
            } while(nextToken != in_fp.length());
        }

        catch (FileNotFoundException ex) {System.out.print("ERROR - cannot open front.txt" + ex);}
    }
}