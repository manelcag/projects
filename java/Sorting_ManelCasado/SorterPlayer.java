import java.io.*;
/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 4: Sorting
/*  Created: August 2022
/*  Class:   SorterPlayer
*******************************************/

public class SorterPlayer {

    public static void main(String[] args) {
        // -------------------- Test 1: simple merge sort with int --------------------
        int[] actual = { 5, 1, 6, 2, 3, 4 };
        // int[] expected = { 1, 2, 3, 4, 5, 6 };

        showArray(actual);
        mergeSort(actual, actual.length);
        showArray(actual);
		
        // Test Queue
        Queue myQueue;
        int startSize;
		
        myQueue = new Queue();
        myQueue.enqueue("aaa");
        myQueue.enqueue("bbb");
        myQueue.enqueue("ccc");
		
        startSize = myQueue.size();
        for (int i=0; i<startSize; i++) {
            Object obj = myQueue.dequeue();
            System.out.println(obj);
        }
        System.out.println("final size: " + myQueue.size());

        // -------------------- Test 2: exercise --------------------
        DataGroup dataGroup;
        BigData bigData;
		
        try {
            // Part1 sorting in memory with a DataGroup
            dataGroup = new DataGroup("100 Sales Records.csv");
            System.out.println("Loaded data:");
            System.out.println(dataGroup);

            System.out.println("Sorting loaded data.");
            dataGroup.sort();
            System.out.println("Now data is:");
            System.out.println(dataGroup);
			
            // Part 2 using BigData
            // 3. Design another class called BigData that can sort more data than can fit in memory using the method covered in class
            // This class should report back the number of reads and number of writes (each divided by 5) done by the program.

            // Phase 1
            bigData = new BigData("100 Sales Records.csv");
			
            // Phase 2
            bigData.mergeAllRuns();
        } catch (IOException e) {
            System.err.println("It was no possible to load data from file. " + e.getLocalizedMessage());
        }
		
    }

    public static void mergeSort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public static void showArray(int[] array) {
        System.out.println("Array size: " + array.length);
        System.out.println("Content:");
        for (int i=0; i< array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
    }
}
