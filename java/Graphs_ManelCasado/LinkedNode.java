public class LinkedNode{
    private int node;
    private LinkedNode next;
    
    public LinkedNode(int value) {
        this.value = value;
        next = null;
    }
    
    public int getNode() {
        return node;
    }
    
    public LinkedNode getNext() {
        return next;
    }
    
    public void setNext(LinkedNode node) {
        this.node = node;
    }
}