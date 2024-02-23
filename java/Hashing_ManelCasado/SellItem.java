import java.io.*;
import java.util.*;

/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 5: Hashing
/*  Created: October 2022
/*  Class:   SellItem
*******************************************/

public class SellItem {
    public static final int DATASIZE=20;
    public static final int IDOFFSET=4;
    private char location;
    private char type;
    private int id;
    private int amount;
    private double cost;
    
    public SellItem(String line) {
        String[] parts;
        parts = line.split(",");
        location = parts[0].charAt(0);
        type = parts[4].charAt(0);
        id = Integer.parseInt(parts[6]);
        amount = Integer.parseInt(parts[8]);
        cost = Double.parseDouble(parts[9]);
    }
    
    // Contructor to generate hash
    public SellItem(int inId) {
        id = inId;
    }
    
    public SellItem(char inLoc, char inTyp, int inId, int inAm, double inCos) {
        location = inLoc;
        type = inTyp;
        id = inId;
        amount = inAm;
        cost = inCos;
    }
    
    public SellItem(RandomAccessFile datafile) throws IOException {
        location = datafile.readChar();
        type = datafile.readChar();
        id = datafile.readInt();
        amount = datafile.readInt();
        cost = datafile.readDouble();
    }
    
    public void write(RandomAccessFile datafile) throws IOException {
        datafile.writeChar(location);
        datafile.writeChar(type);
        datafile.writeInt(id);
        datafile.writeInt(amount);
        datafile.writeDouble(cost);
    }
    
    public void read(RandomAccessFile datafile) throws IOException {
        location = datafile.readChar();
        type = datafile.readChar();
        id = datafile.readInt();
        amount = datafile.readInt();
        cost = datafile.readDouble();
    }

    public char getLocation() {
        return this.location;
    }
    public char getType() {
        return this.type;
    }
    public int getId() {
        return this.id;
    }
    public int getAmount() {
        return this.amount;
    }
    public double getCost() {
        return this.cost;
    }
    public int[] hash() {
        int[] myHashArray;
    	
        myHashArray = hash(this.id);
        return myHashArray;
    }
    
    public static int[] hash(int id) {
        int[] myHashArray;
    	
        myHashArray = new int[2];
        myHashArray[0]= (id/DataFile.BLOCKSIZE) % DataFile.INDEXSIZE;
        myHashArray[1]= (id % DataFile.INDEXSIZE);
        
        return myHashArray;
    }
    
    public boolean equals(SellItem other) {
        return id == other.getId();
    }

    public String toString() {
        return "id= " + id + ", location= " + location 
                + ", type= " + type + ", amount= " +
                amount + ", cost" + cost;
    }
}    
