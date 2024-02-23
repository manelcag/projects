import java.util.Objects;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 6: Huffman
/*  Created: October 2022
/*  Class:   Node
*******************************************/

public class Node implements Comparable {
    private char letter;
    private int weight;	// Number of occurrences
    private Node left;
    private Node right;
    private String path;
	
    // Constructor
    public Node() {
        this.letter = '\0';
        this.weight = 0;
        this.left = null;
        this.right = null;
        this.path = "";
    }
	
    // Constructor with a letter and its weight
    public Node(char letter, int weight) {
        this.letter = letter;
        this.weight = weight;
        this.left = null;
        this.right = null;
        this.path = "";
    }
	
    // Constructor that creates an internal node
    public Node(Node left, Node right) {
        this.left = left;
        this.right = right;
        if (left != null) {
            this.weight+= left.getWeight();
        }
        if (right != null) {
            this.weight+= right.getWeight();
        }
    }
	
    // Getter & setters
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public char getLetter() {
        return letter;
    }

    public Node getLeftNode() {
        return left;
    }

    public void setLeftNode(Node leftNode) {
        this.left = leftNode;
    }

    public Node getRightNode() {
        return right;
    }
	
    public void setRightNode(Node rightNode) {
        this.right = rightNode;
    }

    public void incWeight() {
        this.weight++;
    }
	
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public boolean isLeaf() {
        return (this.left == null) && (this.right == null);
    }
	
    // Helpers
    public int compareTo(Object o) {
        Node nodeToCompare;
		
        nodeToCompare = (Node)o;
        if (this.letter == nodeToCompare.getLetter()) {
            return 0;
        }
        if (this.letter < nodeToCompare.getLetter()) {
            return 1;
        }
        return -1;
    }

    public int hashCode() {
        return Objects.hash(letter);
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        return letter == other.letter;
    }

    public String toString() {
        return letter +  ": " + " repeats: " + weight + " path:" + path;
    }
}
