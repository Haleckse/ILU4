package tp3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	static int fact(int n) {
		return IntStream.rangeClosed(1, n).reduce(1, (x, y) -> x*y); 
	}
	
	static boolean isSizePair(int n) {
		int size = String.valueOf(n).length(); 
		return size%2 == 0; 
	}
	
	public static class Pair {
		int number; 
		int fact; 
		public Pair(int n) {
			this.number = n;
			this.fact = fact(n); 
		}
		
		@Override 
		public String toString() {
			return "" + fact; 
		}
	}
	
	interface Produit {
		int getPrix();
	}
	
	class Article implements Produit {
	    private final String nom;
	    private final int prix;

	    public Article(String nom, int prix) {
	        this.nom = nom;
	        this.prix = prix;
	    }

	    @Override
	    public int getPrix() {
	        return prix;
	    }

	    @Override
	    public String toString() {
	        return nom + " : " + prix + "€";
	    }
	}

	public static void main(String[] args) {
		
		System.out.println("------Question a------");
		System.out.println("factorielle de 4 = " + fact(4));
		
		System.out.println("------Question b------");
		List<Integer> FactList = (List<Integer>) IntStream.rangeClosed(0, 9).map(Main::fact).boxed().collect(Collectors.toList()); 
		System.out.println("La liste des dix premières factorielles : " + FactList);
		
		System.out.println("------Question c------");
		
		IntStream
		.iterate(1, x -> x+1)
		.map(Main::fact)
		.filter(Main::isSizePair)
		.limit(4)
		.forEach(x -> {System.out.println(x); }); 
		
		System.out.println("------Question d------");
		
//		Stream
//		.iterate(new Pair(1), p -> new Pair(p.number+1))
//		.filter(Main::isSizePair)
//		.limit(4)
//		.forEach(x -> {System.out.println(x.toString()); }); 
		
		System.out.println("\n\n------EXO 2------\n\n");
		System.out.println("------Question a------");
		
//		List<Produit> produits = List.of(
//                new Article("Ordinateur", 1000),
//                new Article("Téléphone", 800),
//                new Article("Casque", 150),
//                new Article("Clavier", 50),
//                new Article("Souris", 30)
//        );

	}
	
	
	
	
}
