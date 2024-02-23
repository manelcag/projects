/*******************************************
/** Author:  Manel Casado  
/*  Course:  CSC 221, Fall 2023
/*  Lab:     Lab 6: Linked List Lab: Worm
/*  Created: November 2023
/*  Class:   LinkedList
*******************************************/
public class LinkedList {
    LNode head;
    
    public LinkedList() {
        head = null;
    }
    
    public void addFront(LNode node) {
        node.setNext(head);
        head = node;
    }
    
    public void addAfter(LNode prev, LNode newOne) {
        newOne.setNext(prev.getNext());
        prev.setNext(newOne);
    }
    // adding after node sent
    public void add(LNode prev, LNode newOne) {
        if (prev == null) {// adding to head
            newOne.setNext(head);
            head = newOne;
        } else {
            newOne.setNext(prev.getNext());
            prev.setNext(newOne);
        }
    }
                    
    public void delete(LNode prev) {
        LNode deleteGuy;

        if (prev == null)
            head = (head.getNext());
        else {
            deleteGuy = prev.getNext();
            prev.setNext(deleteGuy.getNext());
        }
    }
            
    public LNode findNode(Segment item) {
        return findNode(item, head);
    }
    
    private LNode findNode(Segment item, LNode cur) {
        if (cur == null) return null;
        if (cur.getSegment().equals(item)) return cur;
        return findNode(item,cur.getNext());
    }
    
    public LNode findPrevNode(Segment item) {
        LNode cur, prev;
        boolean found;
        
        found = false;
        cur = head;
        prev = null;
        while (cur!= null && !found) {
            if (cur.getSegment().equals(item)) found = true;
            else {
                prev = cur;
                cur = cur.getNext();
            }
        }
        if (found) return prev;
        return null;
    }
    
    public void deleteNode(Segment item) {
        LNode node;
        if (findNode(item) != null) {// in the list
            node = findPrevNode(item);
            delete(node);
        }
        // else throw an exception because it is not in the list
    }
    
    public LNode findNodeIt(Segment item) {
        LNode cur;
        boolean found;
        
        found = false;
        cur = head;
        while (cur!= null && !found) {
            if (cur.getSegment().equals(item)) found = true;
            else cur = cur.getNext();
        }
        if (found) return cur;
        return null;
    }
            
    public String toString() {
        return toString(head);
    }
    
    private String toString(LNode cur) {
        if (cur == null) return "";
        return cur.getSegment().toString()+" "+
               toString(cur.getNext());
    }
    public String toStringRev() {
        return toStringRev(head);
    }
    
    private String toStringRev(LNode cur) {
        if (cur == null) return "";
        return toStringRev(cur.getNext())+" "+
               cur.getSegment().toString();
    }
    
    public LNode getHead() {
        return head;
    }
    
    public void append() {
        LNode lastNode;
        Segment lastSegment;
        Segment newSegment;
        LNode newNode;

        // Go to the last node, starting from head
        lastNode = head;
        while (lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
        }

        // Get last segment
        lastSegment = lastNode.getSegment();
        
        // Create new segment
        // TODO keep in mind that worm could be in down direction
        newSegment = new Segment(lastSegment.getRow() + 1, lastSegment.getColumn());
        newNode = new LNode(newSegment);

        add(lastNode, newNode);
    }
}