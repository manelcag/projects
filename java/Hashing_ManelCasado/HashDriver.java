import java.io.*;
import java.util.*;

/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 5: Hashing
/*  Created: October 2022
/*  Class:   HashDriver
*******************************************/

public class HashDriver {
    public static void main(String[] args) throws IOException {
        // Arguments are: sampleFile.csv idsToSearch.csv
    	
        // The following line generates a test file to be able to use seachData later
        generateSearchFile(args[0], args[1]);
    	
        // Load data and store it in .ran file using a hash function and collision table
        loaddata(args[0]);
        System.out.println("Number of read/writes done: " + DataFile.numberOfReadWrites);
        
        searchdata(args[1]);
        System.out.println("Number of read/writes done: " + DataFile.numberOfReadWrites);
    }
    
    public static void loaddata(String inFile) throws IOException {
        Scanner file;
        String line;
        SellItem item;
        
        file = new Scanner(new FileReader(inFile));
        file.nextLine(); // skip header line
        while (file.hasNext()) {
            line = file.nextLine();
            item = new SellItem(line);
            DataFile.save(item);
        }
        file.close();
    }
    
    public static void searchdata(String searchFile) throws IOException {
        Scanner file;
        int id;
        SellItem item;
                
        file = new Scanner(new FileReader(searchFile));
        while (file.hasNext()) {
            id = file.nextInt();
            item = DataFile.find(id);
            if (item != null) 
                System.out.println("Found: " + item.toString());
            else
                System.out.println("Item with id= " + id + " not found");
        }
        file.close();
    }

    // This method generates a searchFile using all the id's stored in inFile 
    public static void generateSearchFile(String inFile, String searchFile) throws IOException {
        Scanner file;
        String line;
        SellItem item;
        FileWriter fileWriter = null;
        
        file = new Scanner(new FileReader(inFile));
        fileWriter = new FileWriter(searchFile);
        
        System.out.println("Generating " + searchFile + " This is the test file with Ids to search.");
        
        file.nextLine(); // skip header line
        while (file.hasNext()) {
            line = file.nextLine();
            item = new SellItem(line);
            fileWriter.write(item.getId() + " ");
        }
        
        file.close();
        fileWriter.close();
        
        System.out.println("Generated " + searchFile);
    }
}