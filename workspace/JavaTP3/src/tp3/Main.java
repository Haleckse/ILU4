package tp3;

import java.util.ArrayList;
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
	
	static class Article implements Produit {
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
	        return nom + " : " + prix + "�";
	    }
	}
	
	interface Pays {
		  List<Ville> getVilles();
		  String getContinent();
		  Ville getCapitale();
		  int getPopulation();
		}
	
	interface Ville {
		  Pays getPays();
		  int getPopulation();
		  String toString();
		}
	
	static record VilleRecord(String nom, Pays pays, int population) implements Ville {
		
		@Override
		public Pays getPays() {
			return pays;
		}
		
		@Override
		public int getPopulation() {
			return population; 
		}
		
		@Override 
		public final String toString() {
			return nom;
		}
		
		
	}
	
	static record PaysRecord(List<Ville> villes, String continent, Ville capital, int population) implements Pays {

		@Override
		public List<Ville> getVilles() {
			// TODO Auto-generated method stub
			return villes;
		}

		@Override
		public String getContinent() {
			// TODO Auto-generated method stub
			return continent;
		}

		@Override
		public Ville getCapitale() {
			// TODO Auto-generated method stub
			return capital;
		}

		@Override
		public int getPopulation() {
			// TODO Auto-generated method stub
			return population;
		}
		
	}

	public static Ville capitalLaPlusPeuple(Stream<Pays> p)  {
		return p
			.map(Pays::getCapitale)
			.max( (v1, v2) -> Integer.compare(v1.getPopulation(), v2.getPopulation()))
			.orElse(null); 
	}



	public static void main(String[] args) {
		
		System.out.println("------Question a------");
		System.out.println("factorielle de 4 = " + fact(4));
		
		System.out.println("------Question b------");
		List<Integer> FactList = (List<Integer>) IntStream.rangeClosed(0, 9)
				.map(Main::fact)
				.boxed()
				.collect(Collectors.toList()); 
		System.out.println("La liste des dix premi�res factorielles : " + FactList);
		
		System.out.println("------Question c------");
		
		IntStream.iterate(1, x -> x+1)
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
		
		List<Produit> produits = List.of(
                new Article("Ordinateur", 1000),
                new Article("T�l�phone", 800),
                new Article("Casque", 150),
                new Article("Clavier", 50),
                new Article("Souris", 30)
        ); 
		
		int sommeDesPrix = produits.stream()
				.mapToInt(x -> x.getPrix())
					.sum(); 
		System.out.println("Somme des prix des produits : " + sommeDesPrix);
		
		int SommeDesPrixReduce = produits
				.stream()
				.mapToInt(x -> x.getPrix())
				.reduce( 0, (x, y) -> (x+y) );
		
		System.out.println("Somme des prix avec un reduce : " + SommeDesPrixReduce);
		
		
		
		PaysRecord france = new PaysRecord(
				List.of(
						new VilleRecord("Paris", null, 75000), 
						new VilleRecord("Marseille", null, 56000) 
				)
				, "Europe", new VilleRecord("Paris", null, 75000), 670000);
		
		PaysRecord usa = new PaysRecord(
				List.of(
						new VilleRecord("NewYork", null, 83000), 
						new VilleRecord("Chicago", null, 87000) 
				)
				, "NorthAmerica", new VilleRecord("NewYork", null, 83000), 880000);
		
		PaysRecord japon = new PaysRecord(
				List.of(
						new VilleRecord("Tokyo", null, 27000), 
						new VilleRecord("Kyoto", null, 16000) 
				)
				, "Asia", new VilleRecord("Tokyo", null, 27000), 436000);
		

		List<Pays> pays = List.of(france, usa, japon); 
		Stream<Pays> p = pays.stream(); 
		
		System.out.println("La capital la plus peuplée est : " + capitalLaPlusPeuple(p).toString());
	}
	
	
	
	
}
