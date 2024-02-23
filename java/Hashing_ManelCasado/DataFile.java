import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 5: Hashing
/*  Created: October 2022
/*  Class:   DataFile
*******************************************/

public class DataFile {
    // Constants
    public static final int BLOCKSIZE = 20000;
    public static final int INDEXSIZE  = 4000;
    public static final String BASENAME  = "selldata";
    public static final int MAX_FILES = 10;
	
    // Class variables (or properties)
    public static int numberOfReadWrites = 0;
	
    // Collision table
    public static int[][] collisionTable = new int[MAX_FILES][INDEXSIZE];
	
    // Methods (or operations)
    public static SellItem find(int id) {
        SellItem sellItem;
        int[] myHashArray;
        int fileNum, position;
        String filePath;
        RandomAccessFile raf = null;
        int i;
        boolean found;

        sellItem = new SellItem(id);
        myHashArray = sellItem.hash();
        fileNum = myHashArray[0];
        position = myHashArray[1];
	
        found = false;

        // Open the file where the item was stored
        filePath = BASENAME + "/" + myHashArray[0] + ".ran";
        try {
            raf = new RandomAccessFile(filePath, "rw");

            // Search this id in positions starting for the hashed position
            i = position;
            while (i < INDEXSIZE && !found) {
                if (collisionTable[fileNum][i] == id) {
                    // If we have found the item we read it
                    raf.seek(i *BLOCKSIZE);
                    sellItem.read(raf);
                    numberOfReadWrites++;
                    found = true;
                }
				
                // Next element
                i++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File " + filePath + " not found.");
        } catch (IOException e) {
            System.err.println("Item not found in file ");
        }
		
        if (found) {
            return sellItem;
        } else {
            return null;
        }
    }

    // Store a SelItem in the right file / position
    public static void save(SellItem item) {
        String filePath;
        RandomAccessFile raf = null;
        int[] myHashArray;
        int indexPosition;
	
        // The irst element of the hash array is the number of file, 
        // the second one is the position in the file
        myHashArray = item.hash();
        filePath = BASENAME + "/" + myHashArray[0] + ".ran";
		
        try {
    	    // If file does not exist, the file is created
            // It is a random file for read and write
            // using method seek we are positioning the read/write
            raf = new RandomAccessFile(filePath, "rw");
			
            indexPosition = insertion(item, collisionTable);
            // If we multiply indexNum by the BLOCKSIZE we get the position in the file
            // We use this position in the file in order to seek.
            // If an exception is thrown it means that this SelItem does not exist, and must be created
            raf.seek(indexPosition*BLOCKSIZE);

            // Write the item in file/position
            item.write(raf);
            System.out.println("Item " + item + " stored.");
            numberOfReadWrites++;

            raf.close();
        } catch (IOException e) {
            System.err.println("Error saving item. Check if exists directory: " + BASENAME);
        }
    }
	
    // Find a position using hash function and collision table
    public static int insertion(SellItem item, int[][] arr) {
        int[] myHashArray;
        int fileNum, indexPosition;
        int position;
        
        myHashArray = item.hash();
        fileNum = myHashArray[0];
        indexPosition = myHashArray[1];
				
        position = indexPosition % INDEXSIZE;
        while (arr[fileNum][position] != 0) {
            position = ++position % INDEXSIZE;
        }
        arr[fileNum][position] = item.getId();
		
        return position;
    }	
}
