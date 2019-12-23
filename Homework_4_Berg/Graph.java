//Dale Berg
//Homework 4
//CPSC 2600-01
//11/21/19

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Graph {
    Map<Integer, Set<Integer>> adjacencies;
    int numberVertices;

    Graph() {
        adjacencies = new HashMap<>();
        numberVertices = 0;
    }

    public void addVertex(int vertex) {
        if (!adjacencies.containsKey(vertex)) {
            SortedSet<Integer> edges = new TreeSet<>();
            adjacencies.put(vertex, edges);
            numberVertices++;
        }
    }

    public void addEdge(int v1, int v2) {
        adjacencies.get(v1).add(v2);
        adjacencies.get(v2).add(v1);
    }

    public void addConnectedPair(int v1, int v2) {
        addVertex(v1);
        addVertex(v2);
        addEdge(v1, v2);
    }

    //returns 1 for yes, 0 for no
    public int hasEdge(int v1, int v2) {
        return adjacencies.containsKey(v1) && adjacencies.get(v1).contains(v2) ? 1 : 0;
    }

    public int[][] getAdjacencyMatrix() {
        int size = numberVertices;
        int[][] arr = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                arr[row][col] = this.hasEdge(row, col);
                arr[col][row] = this.hasEdge(row, col);
            }
        }
        return arr;
    }

    public void display() {
        int[][] arr =  new int[numberVertices][numberVertices];
        for(int row = 0; row < numberVertices; row++) {
            for(int col = 0; col < numberVertices; col++) {
                arr[row][col] = this.hasEdge(row, col);
                arr[col][row] = this.hasEdge(row,col);
                System.out.print(arr[row][col]);
            }
            System.out.println();
        }
    }

    public int numVertices() {
        return adjacencies.keySet().size();
    }

    public void displayDFS(int vertex) {
        int[][] matrix = this.getAdjacencyMatrix();
        boolean[] visited = new boolean[numberVertices];
        displayDFS(vertex, matrix, visited);
    }

    public void displayDFS(int vertex, int[][] matrix, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int j = 0; j < visited.length; j++) {
            if (matrix[vertex][j] == 1 && !visited[j])
                displayDFS(j, matrix, visited);
        }
    }

    public void displayBFS(int vertex) {
        boolean[] visited = new boolean[this.numberVertices];
        Queue<Integer> que = new LinkedList<>();
        que.add(vertex);
        visited[vertex] = true;
        while (!que.isEmpty()) {
            vertex = que.poll();
            System.out.print(vertex + " ");
            for(int v : adjacencies.get(vertex) )
            if (!visited[v]) {
                visited[v] = true;
                que.add(v);
            }
        }
    }

    public static void load( String fileName, Graph graph) throws FileNotFoundException {
        File inputFile = new File(fileName);

        Scanner inputs = new Scanner(inputFile);

        int numberVertices = inputs.nextInt();

        while(inputs.hasNextInt()) {
            int v1 = inputs.nextInt();
            int v2 = inputs.nextInt();
            // duplicates will not be added
            graph.addConnectedPair(v1, v2);
        }
    }
}
