package sorting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class IOHandling {
    public static Scanner openInput(String inputFile) {
        try {
            File file = new File(inputFile);
            return new Scanner(file);
        }
        catch (IOException e) {
            return new Scanner(System.in);
        }
    }

    /* may have a problem parsing console output when using PrintWriter(System.out)
       this may be due to the difference between PrintStream and PrintWriter:
       may be encoding or flush() method */
    public static PrintWriter openOutput(String outputFile) {
        try {
            File file = new File(outputFile);
            return new PrintWriter(file);
        }
        catch (IOException e) {
            return new PrintWriter(System.out);
        }
    }
}
