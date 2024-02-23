import java.io.*;
import java.util.*;

/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 5: Hashing
/*  Created: October 2022
/*  Class:   TestRandomWriteData
*******************************************/

public class TestRandomWriteData {
    public static void main(String[] args) throws IOException {
        Scanner data;
        RandomAccessFile output;
        String line;
        
        data = new Scanner(new FileReader("100.csv"));
        line = data.nextLine();
        output = new RandomAccessFile("threerow.ran","rw");

        writeLine(data,output);
        writeLine(data,output);
        writeLine(data,output);
        data.close();
        output.close();
    }
    public static void writeLine(Scanner data, RandomAccessFile output) throws IOException {
        String line;
        String[] parts;
        int intnum;
        double doublenum;

        line = data.nextLine();
        parts = line.split(",");
        for (int i=0; i < parts.length; i++)
            System.out.println(parts[i]);
        output.writeChar(parts[0].charAt(0));
        output.writeChar(parts[4].charAt(0));
        intnum = Integer.parseInt(parts[6]);
        output.writeInt(intnum);
        intnum = Integer.parseInt(parts[8]);
        output.writeInt(intnum);
        doublenum = Double.parseDouble(parts[9]);
        output.writeDouble(doublenum);

    }
}
