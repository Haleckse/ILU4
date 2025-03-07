package bf;
// Java Program to implement
// Bellman For Algorithm
//Java program to find single source shortest path using 
//Bellman-Ford algorithm

import java.util.Arrays;

public class BellmanFord {
 static int[] bellmanFord(int V, int[][] edges, int src) {
     
     // Initially distance from source to all other vertices 
     // is not known(Infinite).
     int[] dist = new int[V];
     Arrays.fill(dist, (int)1e8);
     dist[src] = 0;

     // Relaxation of all the edges V times, not (V - 1) as we  
     // need one additional relaxation to detect negative cycle
     for (int i = 0; i < V; i++) {
         for (int[] edge : edges) {
             int u = edge[0];
             int v = edge[1];
             int wt = edge[2];
             if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                 
                 // If this is the Vth relaxation, then there is
                 // a negative cycle
                 if (i == V - 1)
                     return new int[]{-1};
                 
                 // Update shortest distance to node v
                 dist[v] = dist[u] + wt;
             }
         }
     }
     return dist;
     
     
 }
 
 	public void calculcateDistance(int[][] edges, int nbSommets) {
 		int V = nbSommets; 
 		 int src = 0;
 	     int[] ans = bellmanFord(V, edges, src);
 	     for (int dist : ans) 
 	     System.out.print(dist + " ");
 	}

 public static void main(String[] args) {
//     int V = 5;
//     int[][] edges = new int[][] {
//         {1, 3, 2},
//         {4, 3, -1},
//         {2, 4, 1},
//         {1, 2, 1},
//         {0, 1, 5}
//     };
//
//     int src = 0;
//     int[] ans = bellmanFord(V, edges, src);
//     for (int dist : ans) 
//         System.out.print(dist + " ");
     
     BellmanFord bf = new BellmanFord(); 
     bf.calculcateDistance(new int[][] { 
     {0, 4, 8},
     {0, 5, 10},
     {1, 5, -1},
     {2, 1, -2},
     {3, 2, -1},
     {3, 5, -4},
     {4, 3, 1},
     {5, 2, 2}}, 6);
 }
}
