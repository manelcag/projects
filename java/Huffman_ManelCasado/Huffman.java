import java.io.*;
import java.util.*;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 6: Huffman
/*  Created: October 2022
/*  Class:   Huffman
*******************************************/

public class Huffman {
    private static final String ENCODE = "encode";
    private static final String DECODE = "decode";

    public static void main(String[] args) {
        Scanner scanner;
        String operation, fileName;
        Tree tree;
        Node node1, node2, newNode;
        String path;
		
        scanner = new Scanner(System.in);
        operation = inputOperation(scanner);
        fileName = inputFilename(scanner);
		
        tree = new Tree();
        if (operation.equals(ENCODE)) {
            // Encode
            /*
			    https://en.wikipedia.org/wiki/Huffman_coding
			  	1. Create a leaf node for each symbol and add it to the priority queue.
				2. While there is more than one node in the queue:
				2.1. Remove the two nodes of highest priority (lowest probability) from the queue
				2.2.Create a new internal node with these two nodes as children and with probability equal to the sum of the two nodes' probabilities.
				2.3.Add the new node to the queue.
				The remaining node is the root node and the tree is complete.
			 */

            // 1. Create a leaf node for each symbol and add it to the priority queue.
            tree = readFile(fileName);
            System.out.print("Encoding: ");
            System.out.println(tree.getSentence());
			
            System.out.println("Leaf nodes before sort:");
            System.out.println(tree);

            System.out.println("Leaf nodes after sort (non mandatory but improves performance:");
            tree.sortNodesByPriority();
            System.out.println(tree);
			
            // 2 While there is more than one node in the queue
            while (tree.size() > 1) {
                // 2.1. Remove the two nodes of highest priority (lowest probability) from the queue
                node1 = tree.pull();
                node2 = tree.pull();
				
                // 2.2.Create a new internal node with these two nodes as children and with probability equal to the sum of the two nodes' probabilities.
                newNode = new Node(node1, node2);
				
                // 2.3.Add the new node to the queue.
                tree.add(newNode);
            }
            // The remaining node is the root node and the tree is complete.
            // System.out.println(tree);
			
            path = "";
            System.out.println("Huffman tree:");
            displayTree(tree.getRoot(), path);
		
            System.out.println("\nCharacters with its path:");
            tree.buildPaths();
		
            tree.writeToFile("testing.encode");
        } else {
            // Decode
            tree.loadFromFile("testing.encode");
			
            tree = tree.rebuild();
            System.out.println(tree);

            System.out.print("Decoded: ");
            System.out.println(tree.decode());
        }
    }

    private static String inputOperation(Scanner scanner) {
        String operation;
		
        do {
            System.out.println("Do you want to encode or decode?");
            operation = scanner.nextLine();
        } while (!operation.equalsIgnoreCase(ENCODE) && !operation.equalsIgnoreCase(DECODE));
		
        return operation.toLowerCase();
    }

    private static String inputFilename(Scanner scanner) {
        String filename;
		
        System.out.println("What is the name of the file?");
        filename = scanner.nextLine();
		
        return filename;
    }
	
    private static Tree readFile(String filename) {
        Scanner dataFile;
        Tree tree;
        char aChar;
        Node node;
        String sentence;
	        
        tree = new Tree();
        sentence = "";
        try {
            dataFile = new Scanner(new FileReader(filename));
            while (dataFile.hasNext()) {
                aChar = dataFile.next().charAt(0);
                node = tree.findNode(aChar);
                if (node == null) {
                    node = new Node(aChar, 1);
                    tree.add(node);
                } else {
                    node.incWeight();
                }
		    	
                sentence+= aChar;
            }
            dataFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        
        tree.setSentence(sentence);
        return tree;
    }
	
    public static void displayTree(Node node, String path) {
        Node left, right;
	
        if(node != null) {
            node.setPath(path);
            System.out.println(node);
        }

        left = node.getLeftNode();
        if(left != null) {
            //left.setPath(path + "0");
            displayTree(left, path + "0");
        }    

        right = node.getRightNode();
        if(right != null) {
        //right.setPath(path + "1");
            displayTree(right, path + "1");
        }
    }
}
