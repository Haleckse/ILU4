package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bf.BellmanFord; 


public class BellmanFordTest {
	
    
	public static void main(String[] args) {
		
		final String GREEN = "\u001B[32m";
		final String RED = "\u001B[31m";
	    final String RESET = "\u001B[0m"; // Réinitialise la couleur
		
		Map< Integer, List<Integer> > checkTest = new HashMap<>(); 
		checkTest.put(0, new ArrayList<>(Arrays.asList(-1)));
		checkTest.put(1, new ArrayList<>(Arrays.asList(0, 5, 6, 6, 7)));
		checkTest.put(2, new ArrayList<>(Arrays.asList(0, 2, -1, 0, -4)));

		
		for (int test_number = 0; test_number < 3; test_number++) {
			boolean first_line = true; 
			int nb_sommet = 0; 
			 String fileName = String.format("Z:\\ILU4\\workspace\\TestTP1\\src\\tests\\tests%d.txt", test_number); 
		        List<int[]> dataList = new ArrayList<>();

		        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		            String line;
		            while ((line = br.readLine()) != null) {
		            	
		            	if (first_line) {
		            		nb_sommet = Integer.parseInt(line); 
		            		first_line = false; 
		            	}
		            	else {
		            		String[] parts = line.split(" ");
			                int[] row = new int[parts.length];
			                for (int i = 0; i < parts.length; i++) {
			                    row[i] = Integer.parseInt(parts[i]);
			                }
			                dataList.add(row);
		            	}
		            	
		                
		            }
		        } catch (IOException e) {
		            System.err.println("Erreur de lecture du fichier: " + e.getMessage());
		        }

		        // Conversion de la liste en tableau 2D
		        int[][] dataArray = dataList.toArray(new int[0][]);

		        // Affichage du tableau pour vérification
//		        for (int[] row : dataArray) {
//		            System.out.println(Arrays.toString(row));
//		        }
		        BellmanFord bf = new BellmanFord(); 
		    	
		    	List<Integer> result = bf.calculcateDistance(dataArray, nb_sommet, 0); 
		    	assert(result == checkTest.get(test_number)); 
		    	if(result.equals(checkTest.get(test_number))) {
		    		System.out.println(result + " == " + checkTest.get(test_number) + "    test passed");
		    	}
		    	else {
		    		System.out.println(result + " != " + checkTest.get(test_number) + "    test failed"); 
		    	}
		    	
		    	
		}
       

    }
	
	
	
	
}
