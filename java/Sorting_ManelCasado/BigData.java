import java.io.*;
import java.util.*;

/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 4: Sorting
/*  Created: August 2022
/*  Class:   DataGroup:  Can hold a limted but large number of items.
/*  Related classes:  SorterPlayer, SellItem
/*  Comments: Must count the number of swaps, compares, reads, and writes
*******************************************/

public class BigData {
    // field variables
    public static final int NUMRUNS=3;
    public static final String BASENAME="100 Sales Records.csv.out";
    private QueueInterface files;
    private int curFileNum;
    
    // constructor
    // needs to:
    // 1) Create empty queue and set curFileNum
    // 2) Load data as runs into a DataGroup: sorting and saving each run
    // 3) Add each "run" to queue
    // Exceptions should be handled not thrown
    public BigData(String dataName) throws IOException {
        Scanner dataFile;
        DataGroup run;
        
        curFileNum = 1;
        files = new Queue(); // need to specify your queue
        run = new DataGroup();
        dataFile = new Scanner(new FileReader(dataName));
        while (dataFile.hasNext()) {
            // load Datagroup
            run.loadData(dataFile);
            // Sort it
            run.sort();
            // Write into binary file
            run.saveData(dataName + ".out" + curFileNum);
            curFileNum++;
            // Load filename in queue
            files.enqueue(run);
        }
    }
    
    // continues to merge runs until only one file left
    // should not throw exceptions : done
    public void mergeAllRuns() {
        try {
	        while (files.size() > 1) {
                mergeSet(BASENAME+curFileNum);
	            
                // Remove file from queue
                files.dequeue();
                curFileNum++;
            }
        } catch (Exception e) {
            System.out.println("Error merging all runs.");
        }
    }
    

    // should merge a set of runs
    // should not throw exceptions
    // needs to "load" the runs and then merge them
    public void mergeSet(String combinedFileName) throws Exception {
        //combinedFileName is the name of the file created by merging runs
        SellItem[] merging;
        ObjectInputStream[] runs;
        int n;
       
        runs = new ObjectInputStream[NUMRUNS];
        merging = new SellItem[NUMRUNS];
        n = loadRuns(merging, runs);
        mergeRuns(merging, runs, combinedFileName, n);
    }
    
    // need to merge the runs and store them in the specified file
    // should not throw exceptions
    public void mergeRuns(SellItem[] items, ObjectInputStream[] data,
                          String outFileName, int numRuns) throws Exception {
        
        for (int i=0; i < numRuns; i++)
            data[i].close();
        files.enqueue(outFileName);
    }

    // should find the smallest of the items (but some items might be null
    //       and be skipped)
    public int findSmallest(Comparable[] items) {
        Comparable smallest;
        int smallestIdx;
        int i;
        
        smallest = null;
        smallestIdx = -1;
        for (i = 0; i<items.length; i++) {
            // Skip null items
            if (items[i] != null) {
                if (smallest == null || items[i].compareTo(smallest) < 0) {
                    smallest = items[i];
                    smallestIdx = i;
                }
            }
        }
        return smallestIdx;
    }

    // Loads two arrays for the runs being merged
    // SellItem contains the first item of each of the files
    // ObjectInputStream is the open file for each of the runs
    // Loads either the max number of runs or the number of files on the
    //     queue, whichever is less
    // return the number of runs loaded
    // should not throw exceptions
    public int loadRuns(SellItem[] items, ObjectInputStream[] data) throws Exception {
        for (int i= 0; i<data.length; i++) {
            System.out.println("String data: " + data[i].readObject());
        }
        return items.length;
    }
    
    // outputs a file specified by the number passed
    // should not throw exceptions: done
    public String toStringLastFile(int fileNum) {
        ObjectInputStream inFile;
        boolean done;
        SellItem item;
        String result;
        
        result = "";
        done = false;
        
        try {
	        inFile = new ObjectInputStream(new FileInputStream(BASENAME+fileNum));
	        while (!done) {
	            try {
	                item = (SellItem)(inFile.readObject());
	                result += item.toString()+"\n";
	            } catch (EOFException e) {
	                done = true;
	            }
	        }
        } catch (Exception e) {
            System.out.println("Error showing outputs.");
        }
        return result;
    }

}