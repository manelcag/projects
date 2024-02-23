/*******************************************
/** Author:  Manel Casado  
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 6: Linked List Lab: Worm
/*  Created: November 2023
/*  Class:   Worm
*******************************************/
public class Worm {
    private static final String LEFT = "h";
    private static final String DOWN = "j";
    private static final String UP = "k";
    private static final String RIGHT = "l";
    private static final String DELETE = "-";
    private static final String ADD = "+";

    private LinkedList segments;
    private String color;
    
    // TODO FIFO stack?
    private Segment lastChangeAt;
    private String lastChangeOfDirection;
    
    public Worm(LNode head) {
        segments = new LinkedList();
        segments.addFront(head);
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String aColor) {
        color = aColor;
    }

    public void append(LNode prev, LNode node) {
        segments.add(prev, node);
    }
    
    public String toString() {
        String result;
        LNode aNode;
        
        
        result = new String("Worm color: ");
        result = result + color;
        result = result + "\nSegments: "; 
        aNode = segments.getHead();
        
        while (aNode != null) {
            result = result + aNode.getSegment();
            aNode = aNode.getNext();
        }
        
        return result;
    }
    
    public void move(String letter) {
        Segment headSeg;
        int currCol, currRow;
        
        headSeg = segments.getHead().getSegment();
        currCol = headSeg.getColumn();
        currRow = headSeg.getRow();

        // Head movement
        if (letter.equals(LEFT)) {
            headSeg.decColumn();
        } else if (letter.equals(DOWN)) {
            headSeg.incRow();
        } else if (letter.equals(UP)) {
            headSeg.decRow();
        } else if (letter.equals(RIGHT)) {
            headSeg.incColumn();
        } else if (letter.equals(DELETE)) {
            segments.delete(null);
        } else if (letter.equals(ADD)) {
            segments.append();
        }
        
        // If there was a movement keys h, j, k, l
        if (currRow != headSeg.getRow() || currCol != headSeg.getColumn()) {
            lastChangeOfDirection = letter;
            lastChangeAt = headSeg; // new Segment(currCol, currRow);
            moveNextSegments();
        }
    }
    
    private void moveNextSegments() {
        LNode aNode;
        Segment segment;
        
        aNode = segments.getHead();
        // Second segment
        aNode = aNode.getNext();
        
        while (aNode != null) {
            segment = aNode.getSegment();
            
            if (lastChangeOfDirection.equals(LEFT) || lastChangeOfDirection.equals(RIGHT)) {
                segment.decRow();
            }
            if (segment.equals(lastChangeAt)) {
                if (lastChangeOfDirection.equals(LEFT)) {
                    segment.decRow();
                } else     if (lastChangeOfDirection.equals(DOWN)) {
                    segment.incRow();
                } else     if (lastChangeOfDirection.equals(UP)) {
                    segment.decRow();
                } else     if (lastChangeOfDirection.equals(RIGHT)) {
                    segment.incRow();
                }
            }
            
            aNode = aNode.getNext();
        }
    }
}