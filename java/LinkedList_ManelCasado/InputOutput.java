import java.io.*;
import java.util.*;
/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 6: Linked List Lab: Worm
/*  Created: November 2023
/*  Class:   InputOutput
*******************************************/
public class InputOutput {
    private Scanner input;
    
    public InputOutput() {
        input = new Scanner(System.in);
    }
    
    public InputOutput(Scanner inInput) {
        input = inInput;
    }
    
    public InputOutput(String filename) {
        try {
            input = new Scanner(new FileReader(filename));
        } catch (IOException e) {
            System.err.println("Error opening file");
            System.exit(0);
        }
    }
    
    public int nextInt() {
        return input.nextInt();
    }
    
    public String nextString() {
        return input.next();
    }
    
    public void flushLine() {
        input.nextLine();
    }
    
    public void print(String line) {
        System.out.print(line);
    }
    
    public void println(String line) {
        System.out.println(line);
    }
}
    
    