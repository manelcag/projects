/*******************************************
/** Author:  Manel Casado  
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 6: Linked List Lab: Worm
/*  Created: November 2023
/*  Class:   LNode
*******************************************/
public class LNode {
    private Segment segment;
    private LNode next;
    
    public LNode(Segment inSegment) {
        segment = inSegment;
        next = null;
    }
    
    public LNode(Segment inSegment, LNode inNode) {
        segment = inSegment;
        next = inNode;
    }
    
    public LNode getNext() {
        return next;
    }
    
    public Segment getSegment() {
        return segment;
    }
    
    public void setSegment(Segment inSegment) {
        segment = inSegment;
    }
    
    public void setNext(LNode next) {
        this.next = next;
    }
}

