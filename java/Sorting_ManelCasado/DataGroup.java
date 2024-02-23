import java.io.*;
import java.util.*;

/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 4: Sorting
/*  Created: August 2022
/*  Class:   DataGroup:  Can hold a limted but large number of items.
/*  Related classes:  SellItem
/*  Comments: Must count the number of swaps, compares, reads, and writes
*******************************************/

public class DataGroup {
    // Constants and field variables
    public static final int MAX = 10000; // done with 10 first for testing

    private SellItem[] data;
    int n;
    
    //constructors (2 of them)
    // Constructor without data (empty)
    public DataGroup() {
   	    this.data = new SellItem[MAX];
        this.n = -1;
    }
    
    // Constructor loading data from filename
    public DataGroup(String filename) throws IOException {
        this();	// Call default constructor (without data)
        Scanner datafile;
        datafile = new Scanner(new FileReader(filename));
        loadData(datafile);
    }
    
    // accessors for the counters
    
    // Saves the data collected into a binary file of the passed name
    // Should be updated to remove the throwing of exception: done
    public void saveData(String fileName) {
        ObjectOutputStream outfile;
        
        try {
            outfile = new ObjectOutputStream(new FileOutputStream(fileName));
            for (int i=0; i < n; i++) {
                outfile.writeObject(data[i]);	// Save data in binary
            }
            outfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("It was imposible to create output file.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There was a problem saving data to file.");
        }
    }
    
    //mutators for the counters
    
    // load up to MAX items from an ascii file of passed name
    // should handle exception instead of throwing it
    public void loadData(Scanner datafile) throws IOException {
        int i;
        String item;
        
        // Discard first row with headers
        if (datafile.hasNext()) {
            datafile.nextLine();
        }
        
        i = 0;
        while (i < data.length && datafile.hasNext()) {
            item = datafile.nextLine();
            data[i] = new SellItem(item);
            i++;
        }
        n = i;
    }
    
    
    // loads set amount from a binary (serialized) file
    // should handle IOException and ClassNotFoundException instead of
    // throwing them
    public void loadSortedData(String fileName)
                  throws IOException, ClassNotFoundException {
        ObjectInputStream dataFile;
        int i;
        SellItem item;
        boolean done;
        
        dataFile = new ObjectInputStream(new FileInputStream(fileName));
        i = 0;
        done = false;
        while (!done) {
            try {
                item = (SellItem)(dataFile.readObject());
                data[i] = item;
            } catch (EOFException e) {
                done = true;
            }
            i++;
        }
        n = i;
        dataFile.close();
    }

    // implement the merge sort
    public void sort() {
        mergeSort(data, n);
    }
    
   	public void mergeSort(SellItem[] myData, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        SellItem[] l = new SellItem[mid];
        SellItem[] r = new SellItem[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = myData[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = myData[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(myData, l, r, mid, n - mid);
    }

    public void merge(SellItem[] data1, SellItem[] dataLeft, SellItem[] dataRight, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (dataLeft[i].compareTo(dataRight[j])  <= 0) {
                data1[k++] = dataLeft[i++];
            } else {
                data1[k++] = dataRight[j++];
            }
        }
        while (i < left) {
            data1[k++] = dataLeft[i++];
        }
        while (j < right) {
            data1[k++] = dataRight[j++];
        }
    }

    
    
    // helpers
    public String toString() {
        String result;
        int i;
        
        result = "";
        for (i=0; i < n; i++) 
            result += data[i].toString()+"\n";
        return result;
    }
}