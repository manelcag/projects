/*******************************************
/** Author:  Dr. Cathy Bareiss
/*  Editor:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 4: Sorting
/*  Created: August 2022
/*  Interface:   QueueInterface
/*  Related classes:  Any queue impelementation
*******************************************/

public interface QueueInterface {
    public boolean isEmpty();
    public boolean isFull();
    public int size();
    public void enqueue(Object item);
    public Object dequeue();
}