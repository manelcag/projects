/****************************************************
 * Author:      Manel Casado Garrigues
 * Course:      CSC 121
 * Assignment:  Lab 4 (tables)
*****************************************************/
import java.io.*;
import java.util.*;

public class Tables {
    
    public static void main(String[] args) {
        Scanner keyboard;
        keyboard = new Scanner(System.in);
        int tableSize;
        String op;
      
        System.out.println("What operation do you want to do?"
        + " (type first letter only):");
        System.out.println("- addition");
        System.out.println("- subtraction");
        System.out.println("- multiplication");
        System.out.println("- division");
       
        op = keyboard.next();
        // Convert uppercase to lowercase
        op = op.toLowerCase();
         
        switch (op){ 
            case "a":
                tableSize = getNumberOfRows(keyboard);
                showAddition(tableSize);
                break;
        
            case "s":
                tableSize = getNumberOfRows(keyboard);
                showSubstraction(tableSize);
                break;
            
            case "m":
                tableSize = getNumberOfRows(keyboard);
                showMultiplication(tableSize);
                break;
        
            case "d":
                tableSize = getNumberOfRows(keyboard);
                showDivision(tableSize);
                break;
         
            default:
            System.out.println("Invalid choice");

        }
    }
    
    private static int getNumberOfRows(Scanner keyboard) {
        int tableSize;
        System.out.print("Number of lines/rows?");
        tableSize = keyboard.nextInt();
        System.out.format("     +");
        return tableSize;
    }
    
    private static void showAddition(int tableSize) {
        
        // Components of a loop:
        // Intialization
        // Ending condition
        // Repeating work
        // Aproaching ending condition
        for (int i = 1; i <= tableSize; i++){ 
            System.out.format("%6d", i);           
        }    
        System.out.println();
        
        for (int i = 1 ; i <= tableSize; i++) {
            System.out.format("%6d", i);
            for (int j = 1; j <= tableSize; j++) {
                System.out.format("%6d", i + j);
            }
            System.out.println();
        }
    }
    
    private static void showSubstraction(int tableSize) {
        for (int i = 1; i <= tableSize ;i++){ 
            System.out.format("%6d", i);           
        }
        System.out.println();
        
        for (int i = 1 ; i <= tableSize; i++) {
            System.out.format("%6d", i);
            for (int j = 1; j <= tableSize; j++) {
                System.out.format("%6d", i - j);
            }
            System.out.println();
        }
    }

    private static void showMultiplication(int tableSize) {
        for (int i = 1; i <= tableSize; i++){
            System.out.format("%6d", i);           
        }    
        System.out.println();
        
        for (int i = 1 ; i <= tableSize; i++) {
            System.out.format("%6d", i);
            for (int j = 1; j <= tableSize; j++) {
                System.out.format("%6d", i * j);
            }
            System.out.println();
        }
   }

    private static void showDivision(int tableSize) {
        double result;
        
        for (int i = 1; i <= tableSize; i++){
            System.out.format("%6d", i);           
        }
        System.out.println();
        
        for (int i = 1 ; i <= tableSize; i++) {
            System.out.format("%6d", i);    
            for (int j = 1; j <= tableSize; j++) {
            	// Convert int to double for obtain a double result with decimals
                result = (double) i / (double) j;
                System.out.format("%6.3f",  result);
            }
            System.out.println();
        }
    }
}