//Dale Berg
//Homework 4
//CPSC 2600-01
//11/21/19


import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {

    public static void main (String args[]) throws FileNotFoundException {

        Graph graph = new Graph();

        Scanner inputs = new Scanner(System.in);

        System.out.println("Please enter the a file name to load inputs from the file: ");
        String fileName = inputs.nextLine();
        System.out.println("Which vertex would you like to start BFS and DFS from?" +
                "(enter an integer representing the vertex): ");
        int startingVertex = inputs.nextInt();

        graph.load(fileName, graph);


        System.out.println("Adjacency matrix: ");
        graph.display();
        System.out.println();
        System.out.print("DFS at vertex " + startingVertex + " : ");
        graph.displayDFS(startingVertex);
        System.out.println();
        System.out.print("BFS at vertex " + startingVertex + " : ");
        graph.displayBFS(startingVertex);
        System.out.println();

    }


}
