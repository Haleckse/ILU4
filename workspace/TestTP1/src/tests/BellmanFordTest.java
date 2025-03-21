package tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bf.BellmanFord;  // Importation de la classe BellmanFord pour ex�cuter l'algorithme

public class BellmanFordTest {
    
    // Classe interne Tuple pour stocker un couple d'entiers (fst, snd)
    private static class Tuple {
        private Integer fst;  // Premier �l�ment du tuple
        private Integer snd;  // Deuxi�me �l�ment du tuple
        
        // Constructeur pour initialiser les valeurs du tuple
        public Tuple(Integer fst, Integer snd) {
            this.fst = fst;
            this.snd = snd;
        }
        
        // M�thode getter pour fst
        public Integer getFst() {
            return fst;
        }
        
        // M�thode getter pour snd
        public Integer getSnd() {
            return snd;
        }
    }
    
    // Initialisation d'une carte de test pour comparer les r�sultats obtenus
    private static Map<Integer, List<Integer>> checkTest = new HashMap<>();
    
    // M�thode pour initialiser les tests avec des r�sultats attendus
    public static void initTest() {
    	checkTest.put(0, new ArrayList<>(Arrays.asList(0, 1, 3, 5, 0, 3)));
        checkTest.put(1, new ArrayList<>(Arrays.asList(0, 5, 6, 6, 7)));
        checkTest.put(2, new ArrayList<>(Arrays.asList(0, 2, -1, 0, -4)));
       
        checkTest.put(5, new ArrayList<>(Arrays.asList(0, 7, 3, 9, 5))); 
        
        
        //graphes orient� cycle negatifs
        checkTest.put(3, new ArrayList<>(Arrays.asList(-1)));
        checkTest.put(4, new ArrayList<>(Arrays.asList(-1))); 
        
        // graphes poids nul
        checkTest.put(6, new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0))); 
        
        //graphe non connexe
        checkTest.put(7, new ArrayList<>(Arrays.asList(0, 2, 5, 100000000, 100000000))); 
        
        //graphe non orient� avec cycle neg
        
        //graphe orient� sans cycle neg
        

        
        
        
    }
    
    // M�thode pour ex�cuter un test individuel
    public static int runSingleTest(int test_number) {
        boolean first_line = true;  // Variable pour g�rer la premi�re ligne du fichier
        int nb_sommet = 0;  // Variable pour stocker le nombre de sommets
        String fileName = String.format("Z:\\ILU4\\workspace\\TestTP1\\src\\tests\\tests%d.txt", test_number); 
        List<int[]> dataList = new ArrayList<>();

        // Lecture du fichier de test
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                
                if (first_line) {
                    // La premi�re ligne contient le nombre de sommets
                    nb_sommet = Integer.parseInt(line);
                    first_line = false;
                } else {
                    // Les lignes suivantes contiennent les informations du graphe
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

        // Conversion de la liste en un tableau 2D pour traitement par l'algorithme
        int[][] dataArray = dataList.toArray(new int[0][]);

        // Ex�cution de l'algorithme de Bellman-Ford
        BellmanFord bf = new BellmanFord();
        List<Integer> result = bf.calculcateDistance(dataArray, nb_sommet, 0);

        // V�rification que le r�sultat est correct
        assert(result == checkTest.get(test_number));

        if (result.equals(checkTest.get(test_number))) {
            System.out.println(result + " == " + checkTest.get(test_number) + "    test passed");
            return 1;  // Test r�ussi
        } else {
            System.out.println(result + " != " + checkTest.get(test_number) + "    test failed");
            return 0;  // Test �chou�
        }
    }
    
    // M�thode pour ex�cuter tous les tests
    public static Tuple runAllTests(int nbTest) {
        int nbTestPassed = 0;
        for (int i = 0; i < nbTest; i++) {
            nbTestPassed += runSingleTest(i);  // Ex�cution de chaque test
        }
        
        // Retourne un tuple avec le nombre de tests r�ussis et le nombre total de tests
        return new Tuple(nbTestPassed, nbTest);
    }
    
    // M�thode principale pour ex�cuter tous les tests et afficher les r�sultats
    public static void main(String[] args) {
        
        // Initialisation des tests
        initTest();
        
        // Ex�cution de tous les tests (ici 4 tests)
        Tuple testPassedRatio = runAllTests(8);
        
        // Affichage des r�sultats
        System.out.println("\nNombre de Tests effectu�s : " + testPassedRatio.getSnd());
        System.out.println("Nombre de Tests r�ussis : " + testPassedRatio.getFst());
        System.out.println("Nombre de Tests �chou�es : " + (testPassedRatio.getSnd() - testPassedRatio.getFst()));
        System.out.println("Taux de r�ussite des Tests : " + (testPassedRatio.getFst() / (float) testPassedRatio.getSnd()) * 100 + " %");
        
//        runSingleTest(0); 
    }
}
