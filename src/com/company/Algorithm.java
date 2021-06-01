package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Algorithm {


   static int vericesNumber;
//file reading method that
    public static int[][] fileReader(String fileName){

        try {
            File fileObject = new File(fileName);
            Scanner myReader = new Scanner(fileObject);

            vericesNumber = Integer.parseInt(myReader.nextLine());

            int[][] dataset=new int[vericesNumber][vericesNumber];


            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                String[] list = data.split(" ");

                int node_One=Integer.parseInt(list[0]);
                int node_Two=Integer.parseInt(list[1]);
                int capacity=Integer.parseInt(list[2]);

                dataset[node_One][node_Two]=capacity;


            }
            myReader.close();
            return dataset;

        } catch (FileNotFoundException e) {
            System.out.println("An error found.");
            e.printStackTrace();
        }
        return null;
    }



   public boolean breadthFirstSearch(int residualGraph[][], int sourNum, int t, int parent[] ) {

        boolean visited[] = new boolean[vericesNumber]; //boolean array checking that BFS algorithm has reached a node or not
        int k=0;
        while(k< vericesNumber){    //setting visited of all nodes to false

            visited[k] = false;
            k++;
            LinkedList <Integer> queue = new LinkedList <Integer>(); //Declaring and instantiating a queue to enqueue a node when it is reached

            queue.add(sourNum);       //adding the source node to the queue
            visited[sourNum] = true; //setting the source node visited to true
            parent[sourNum] = -1;

            while (queue.size() != 0) {
                // standard bfs loop

                int y=0;
                int x = queue.poll();

                while (y < vericesNumber) {

                    if ((visited[y] == false) && (residualGraph[x][y] > 0)) {

                        // If we find a connection to the sink
                        // node, then there is no point in BFS
                        // anymore We just have to set its parent
                        // and can return true

                        if (y == t) {
                            parent[y] = x;
                            return true;
                        }
                        queue.add(y);   //adding values to the queue
                        parent[y] = x;
                        visited[y] = true;  //setting visited of the respective node to true
                    }
                    y++;
                }
            }
        }
        return false;// didn't reach sink in bfs starting from source return false
    }

    //method that implements the the Ford Fulkerson algorithm which returns an integer value
    public int algorithm(int graph[][], int sinkNum) {
        int sorceNum=0;
        int u, v;
        int residual_Graph[][] = new int[vericesNumber][vericesNumber]; // Create a residual graph and fill the residual


        //setting the residual graph values to match the graph given as a parameter
        for (u = 0; u < vericesNumber; u++)
            for (v = 0; v < vericesNumber; v++)
                residual_Graph[u][v] = graph[u][v];

        int parent[] = new int[vericesNumber]; //store the path
        int maximumNumberOfFlow = 0; // There is no flow initially



        //augmenting the path from source to sink
        do{
            int pathNumOfFlow = Integer.MAX_VALUE;

            for ( v = sinkNum; v != sorceNum; v = parent[v]) {
                u = parent[v];
                pathNumOfFlow = Math.min(pathNumOfFlow, residual_Graph[u][v]);
            }
            maximumNumberOfFlow += pathNumOfFlow;

            for (v = sinkNum; v != sorceNum; v = parent[v]) {
                u = parent[v];
                residual_Graph[u][v] -= pathNumOfFlow;
                residual_Graph[v][u] += pathNumOfFlow;
            }


        }while (breadthFirstSearch(residual_Graph, sorceNum, sinkNum, parent));

        // Return the overall flow
        return maximumNumberOfFlow; //adding bottleneck to maxflow if an augmenting path exists

    }







}
