import java.io.FileNotFoundException;
/*******************************************
/** Author:  Manel Casado
/*  Course:  CSC 122, Fall 2022
/*  Lab:     Lab 8: Graphs and coloring
/*  Created: December 2022
/*  Class:   GraphPlayer
*******************************************/

public class GraphPlayer {

    public static void main(String[] args) {
        Graph myGraph;

        try {
            // Simple test
            myGraph = new Graph("smallgraph");
			
            myGraph.color();
			
            System.out.println("Graph colored is: " );
            System.out.println(myGraph);
			
            // Test with designed graph (9 nodes, 25 edges)
            myGraph = new Graph("9x25graph");
            myGraph.color();
            System.out.println("Graph colored is: " );
            System.out.println(myGraph);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }
}
