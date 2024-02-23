/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 4: Sorting
/*  Created: August 2022
/*  Class:   Queue
/*  Related classes:  QueueInterface, LinkedNode, SellItem
*******************************************/

public class Queue implements QueueInterface{
    private LinkedNode firstNode;
    private int currSize;
	
    public Queue() {
        this.firstNode = null;
        this.currSize = 0;
    }

    public boolean isEmpty() {
        return (this.currSize == 0);
    }

    public boolean isFull() {
        // Never is full, becasuse the limit of nodes is the limit of computer memory
        return false;
    }

    public int size() {
        return this.currSize;
    }

    public void enqueue(Object item) {
        LinkedNode nextNode;
        LinkedNode currNode;
        LinkedNode newNode;
		
        this.currSize++;
        newNode = new LinkedNode(item);
        if (this.currSize == 1) {
            // Is the first node
            this.firstNode = newNode; 
        } else {
            // There are previous nodes in the list, find de last one.
            // this loop could be avoid using a new property named lastNode
            currNode = null;
            item = null;
            nextNode = firstNode;
            while (nextNode != null) {
                currNode = nextNode;
                nextNode = nextNode.getNext();
            }
    
            // Add the new item to the end
            currNode.setNext(newNode);
        }
    }

    public Object dequeue() {
        LinkedNode currNode;

        if (this.firstNode == null) {
            return null;
        }

        currNode = this.firstNode;
        this.firstNode = this.firstNode.getNext();
        this.currSize--;

        return currNode.getItem();
    }

}
