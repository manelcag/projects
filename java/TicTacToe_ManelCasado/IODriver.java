import java.util.*;
import java.io.*;
/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 3: Objects Lab, UTicTacToe
/*  Class:   IODriver
*******************************************/

public class IODriver {
    
    Scanner inputDevice;
    
    public IODriver(Scanner ioDevice) {
        this.inputDevice = ioDevice;
    }
    
    public int getInt(String message) {
        int myInt;
        
        displayMessage(message);
        myInt = inputDevice.nextInt();
        return myInt;
    }
    
    public String getString(String message) {
        String myString;
        
        displayMessage(message);
        myString = inputDevice.nextLine();
        return myString;
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }
    
  
}