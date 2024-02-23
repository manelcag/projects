/*******************************************
/** Author:  Manel Casado  
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 6: Linked List Lab: Worm
/*  Created: November 2023
/*  Class:   WormDriver
*******************************************/
public class WormDriver {
    private static final int INITIAL_ROW_NUM = 10;
    private static final int INITIAL_COL_NUM = 10;
    private static final String QUIT = "q";

    public static void main(String[] args) {
        Worm worm;
        LNode aNode, prevNode;
        Segment aSegment;
        InputOutput ioDevice;
        String color;
        String key = "";
        
        ioDevice = new InputOutput();

        // Create worm
        aSegment = new Segment(INITIAL_ROW_NUM, INITIAL_COL_NUM);
        aNode = new LNode(aSegment);
        worm = new Worm(aNode);
        
        for (int rowNum=INITIAL_ROW_NUM+1; rowNum<16; rowNum++) {
            prevNode = aNode;
            aSegment = new Segment(rowNum, INITIAL_COL_NUM);
            aNode = new LNode(aSegment);
            worm.append(prevNode, aNode);
        }
        
        // Play
        System.out.println("Which color: ");
        color = ioDevice.nextString();
        worm.setColor(color);
        
        do {
            System.out.println(worm);
            
            System.out.println("Next movement and press enter: ");
            key = ioDevice.nextString();
            worm.move(key);
        } while (!key.equals(QUIT));
    }
}
