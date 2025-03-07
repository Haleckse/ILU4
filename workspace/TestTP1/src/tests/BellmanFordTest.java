package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import bf.BellmanFord; 


public class BellmanFordTest {
	
	public static void main(String[] args) {
        String fileName = "Z:\\ILU4\\workspace\\TestTP1\\src\\tests\\tests.txt";
        List<int[]> dataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int[] row = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    row[i] = Integer.parseInt(parts[i]);
                }
                dataList.add(row);
            }
        } catch (IOException e) {
            System.err.println("Erreur de lecture du fichier: " + e.getMessage());
        }

        // Conversion de la liste en tableau 2D
        int[][] dataArray = dataList.toArray(new int[0][]);

        // Affichage du tableau pour vérification
        for (int[] row : dataArray) {
            System.out.println(Arrays.toString(row));
        }
        BellmanFord bf = new BellmanFord(); 
    	bf.calculcateDistance(dataArray, 6); 

    }
	
	
	
	
}
