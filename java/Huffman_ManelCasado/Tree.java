import java.io.*;
import java.util.Scanner;

/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 6: Huffman
/*  Created: October 2022
/*  Class:   Tree
*******************************************/

public class Tree {
    private static final int MAX_NODES = 255;
    private static final String SEPARATOR = "-----";
	
    private Node[] nodes;
    private int numOfNodes;
    private String sentence;
	
    public Tree() {
        this.numOfNodes = 0;
        this.nodes = new Node[MAX_NODES];
    }
	
    public Node getRoot() {
        return this.nodes[0];
    }
	
    public int size() {
        return this.numOfNodes;
    }
	
    public int getNumOfNodes() {
        return numOfNodes;
    }
	
    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void add(Node node) {
        int i;
        Node addedNode;
		
        i = 0;
        addedNode = null;
        while (addedNode == null && i < MAX_NODES) {
            if (this.nodes[i] == null) {
                addedNode = node;
                this.nodes[i] = addedNode;
                numOfNodes++;
            }
            i++;
        }
    }
	
    // Retrieves and delete the lowest weight letter, that means high priority
    public Node pull() {
        int lowest;
        int idxLowest;
        Node nodeResult;
		
        lowest = 0;
        idxLowest = -1;
        for (int i= 0; i < MAX_NODES; i++) {
            if (nodes[i] != null) {
                if (lowest == 0 || nodes[i].getWeight() < lowest) {
                    lowest = nodes[i].getWeight();
                    idxLowest = i;
                }
            }
        }
		
        nodeResult = nodes[idxLowest];
        nodes[idxLowest]= null;
        numOfNodes--;
		
        return nodeResult;
    }
	
    public Node findNode(char aChar) {
        Node nodeFound;
        int i;
		
        nodeFound = null;
        i = 0;
        while (i < MAX_NODES) {
            nodeFound = nodes[i];
            if (nodeFound != null && nodeFound.getLetter() == aChar) {
                return nodeFound;
            }
            i++;
        }
		
        return null;
    }

    // Find a node by letter recursively
    public Node findNode(Node startNode, char aChar) {
        Node nodeFound;
		
        if (startNode.getLetter() == aChar) {
            nodeFound = startNode;
            return nodeFound;
        } else {
            if (startNode.getLeftNode() != null) {
                nodeFound = findNode(startNode.getLeftNode(), aChar);
                if (nodeFound != null) {
                    return nodeFound;
                }
            }
            if (startNode.getRightNode() != null) {
                nodeFound = findNode(startNode.getRightNode(), aChar);
                if (nodeFound != null) {
                    return nodeFound;
                }
            }
        }
        return null;
    }

    public void sortNodesByPriority() {
        mergeSort(nodes, numOfNodes);
    }

    private void mergeSort(Node[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Node[] l = new Node[mid];
        Node[] r = new Node[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    private static void merge(Node[] a, Node[] l, Node[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getWeight() <= r[j].getWeight()) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    public Node[] findNodesWithHightestPriority() {
        Node[] nodesResult;
        int lowest1, lowest2;
		
        nodesResult = new Node[2];
        lowest1 = 0;
        lowest2 = 0;
		
        // List is not sortered from low weight to hight,
        // because we are adding new elements after leaf nodes creation
        nodesResult[0] = null;
        nodesResult[1] = null;
        for (int i= 0; i < numOfNodes; i++) {
            if (nodes[i] != null) {
                if (lowest1 == 0 || (nodes[i].isLeaf() && nodes[i].getWeight() < lowest1)) {
                    lowest1 = nodes[i].getWeight();
                    nodesResult[0] = nodes[i];
                } else if (lowest2 == 0 || (nodes[i].isLeaf() && nodes[i].getWeight() < lowest2)) {
                    lowest2 = nodes[i].getWeight();
                    nodesResult[1] = nodes[i];
                }
            }
        }
		
        return nodesResult;
    }
	
    public void buildPaths() {
        int[] arr;
        int top;
		
        arr = new int[MAX_NODES];
        top = 0;
        printCodes(nodes[0], arr, top);
    }
	
    private void printCodes(Node node, int[] arr, int top) {
        if(node.getLeftNode() != null) {
            arr[top] = 0;
            printCodes(node.getLeftNode(), arr, top + 1);
        }
        
        if(node.getRightNode() != null) {
            arr[top] = 1;
            printCodes(node.getRightNode(), arr, top + 1);
        }
        
        if(node.isLeaf()) {
            System.out.print(node.getLetter() + " : ");
            String code = printArr(arr, top);
            node.setPath(code);
            //codeMap.put(root.character, code);
        }
    }

    private String printArr(int[] arr, int n) {
        StringBuilder result = new StringBuilder();
        
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            result.append(String.valueOf(arr[i]));
        }
        System.out.println();
        return result.toString();
    }

    public String toString() {
        String result;
		
        result = "";
        for (int i = 0; i < numOfNodes; i++) {
            result= result + nodes[i] + "\n";
        }
		
        return result;
    }
    
    public void writeToFile(String fileName) {
        PrintWriter out;
        
        try {
            out = new PrintWriter(fileName);
            writeNode(out, nodes[0]);
			
            out.println(SEPARATOR);
            out.println(getCode(this.sentence));
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("It was imposible to create output file.");
        }
    }
    
    private void writeNode(PrintWriter out, Node node) {
        out.print(node.getLetter());
        out.print(":");
        out.println(node.getPath());
        if (node.getLeftNode() != null) {
            writeNode(out, node.getLeftNode()); 
        }
        if (node.getRightNode() != null) {
            writeNode(out, node.getRightNode()); 
        }
    }
    
    private String getCode(String sentence) {
        String result;
        char character;
        Node node;
    	
        result= "";
        for (int i= 0; i<sentence.length(); i++) {
            character = sentence.charAt(i);
            node = findNode(nodes[0], character);	// Always using root node to start search recursively
            result+= node.getPath();
        }
    	
        return result;
    }
    
    public void loadFromFile(String fileName) {
        Scanner dataFile;
        String line;
        String[] lineSplitted;
        char aChar;
        Node node;
            
        try {
            dataFile = new Scanner(new FileReader(fileName));
            while (dataFile.hasNext()) {
                line = dataFile.next();
                if (line.equals(SEPARATOR)) {
                    this.sentence = dataFile.next(); // sentence codified
                } else {
                    lineSplitted = line.split(":");
                    aChar = lineSplitted[0].charAt(0);
                    node = new Node(aChar, 0);	// 0 is not true, but it doesn't means right now
                    if (lineSplitted.length > 1) {
                        node.setPath(lineSplitted[1]);
                    }
                    add(node);
                }
            }
            dataFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    
    public Tree rebuild() {
        Tree newTree;
        Node node, parent;
        char lastCharPath;
    	
        parent = null;
        newTree = new Tree();
        for(int i= 0; i < this.size(); i++) {
            node = nodes[i];
            newTree.add(node);
            parent = findParentByPath(newTree, node.getPath());
            if (parent != null) {
                lastCharPath = node.getPath().charAt(node.getPath().length()-1);
                if (lastCharPath == '0') {
                    parent.setLeftNode(node);
                }
                if (lastCharPath == '1') {
                    parent.setRightNode(node);
                }
            }
        }
        newTree.setSentence(sentence);
    	
        return newTree;
    }
    
    private Node findParentByPath(Tree tree, String path) {
        Node node;
        Node parent;
    	
        if (path.equals("")) {
            return null;
        }
    	
        parent = null;
        node = tree.getRoot();
        for (int i= 0; i<path.length(); i++) {
	        parent =node;
            if (path.charAt(i) == '0') {
                node = node.getLeftNode();
            }
            if (path.charAt(i) == '1') {
                node = node.getRightNode();
            }
        }
    	
        return parent;
    }
    
    public String decode() {
        String result;
        Node node;
        char character;
        int i;
    	
        result = "";
        character = '\0';
        i = 0;
        while (i<sentence.length()) {
            node = getRoot();
            while (!node.isLeaf()) {
                if (sentence.charAt(i) == '0') {
                    node = node.getLeftNode();
                }
                if (sentence.charAt(i) == '1') {
                    node = node.getRightNode();
                }
                character = node.getLetter();
                i++;
            }
            result+= character;
        }
    	
        return result;
    }
}
