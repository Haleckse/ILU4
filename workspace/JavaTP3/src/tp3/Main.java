package tp3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
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

	public static List<Pays> triCPc(Stream<Pays> p) {
		return p.sorted(Comparator.comparing(Pays::getContinent)
				.thenComparingInt(Pays::getPopulation))
				.collect(Collectors.toList()); 
	}

	
	public static List<Pays> triCPd(Stream<Pays> p) {
		return p.sorted(Comparator.comparing(Pays::getContinent)
				.thenComparing(Pays::getPopulation, Comparator.reverseOrder()))
				.collect(Collectors.toList()); 
	}
	
	public static boolean predicate(Ville v) {
//		Pays pays = v.getPays(); 
//		for(Ville ville : pays.getVilles()) {
//			if (ville.getPopulation() > v.getPopulation()) return true; 
//		}
//		return false; 
		Pays pays = v.getPays();
	    
	    // On compare la population de la capitale avec toutes les autres villes du pays
	    return pays.getVilles().stream()
	            .anyMatch(ville -> ville.getPopulation() > v.getPopulation());
	}
	
	public static List<Ville> villesMoinsPeuplees(Stream<Pays> p) {
		return p.map(Pays::getCapitale)
		.filter(Main::predicate)
		.collect(Collectors.toList()); 
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
		System.out.println("------Question a, b, c------");
		
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
		
		System.out.println("\n\n------EXO 3------\n\n");
		
//		PaysRecord france = new PaysRecord(
//				List.of(
//						new VilleRecord("Paris", null, 75000), 
//						new VilleRecord("Marseille", null, 56000) 
//				)
//				, "Europe", new VilleRecord("Paris", null, 75000), 670000);
//		
//		PaysRecord poland = new PaysRecord(
//				List.of(
//						new VilleRecord("Krakovich", null, 5000), 
//						new VilleRecord("Marseille", null, 2000) 
//				)
//				, "Europe", new VilleRecord("Krakovich", null, 5000), 670000);
//		
//		PaysRecord usa = new PaysRecord(
//				List.of(
//						new VilleRecord("NewYork", null, 83000), 
//						new VilleRecord("Chicago", null, 87000) 
//				)
//				, "NorthAmerica", new VilleRecord("NewYork", null, 83000), 880000);
//		
//		PaysRecord japon = new PaysRecord(
//				List.of(
//						new VilleRecord("Tokyo", null, 27000), 
//						new VilleRecord("Kyoto", null, 16000) 
//				)
//				, "Asia", new VilleRecord("Tokyo", null, 27000), 436000);
		// Étape 1 : Création des pays avec des listes de villes vides
		PaysRecord france = new PaysRecord(new ArrayList<>(), "Europe", null, 670000);
		PaysRecord poland = new PaysRecord(new ArrayList<>(), "Europe", null, 670000);
		PaysRecord usa = new PaysRecord(new ArrayList<>(), "NorthAmerica", null, 880000);
		PaysRecord japon = new PaysRecord(new ArrayList<>(), "Asia", null, 436000);

		// Étape 2 : Création des villes en leur attribuant leur pays respectif
		VilleRecord paris = new VilleRecord("Paris", france, 7500 );
		VilleRecord marseille = new VilleRecord("Marseille", france, 2000000);
		france = new PaysRecord(List.of(paris, marseille), "Europe", paris, 670000);

		VilleRecord krakovich = new VilleRecord("Krakovich", poland, 5000);
		VilleRecord varsovie = new VilleRecord("varsovie", poland, 2000);
		poland = new PaysRecord(List.of(krakovich, varsovie), "Europe", krakovich, 670000);

		VilleRecord newYork = new VilleRecord("NewYork", usa, 83000);
		VilleRecord chicago = new VilleRecord("Chicago", usa, 87000);
		usa = new PaysRecord(List.of(newYork, chicago), "NorthAmerica", newYork, 880000);

		VilleRecord tokyo = new VilleRecord("Tokyo", japon, 27000);
		VilleRecord kyoto = new VilleRecord("Kyoto", japon, 16000);
		japon = new PaysRecord(List.of(tokyo, kyoto), "Asia", tokyo, 436000);

		

		List<Pays> pays = List.of(france, usa, japon, poland); 
		Stream<Pays> p = pays.stream(); 
		
		
		System.out.println("------Question b------");
		
		System.out.println("La capital la plus peuplée est : " + capitalLaPlusPeuple(p).toString());
		
		System.out.println("------Question c------");
		Stream<Pays> p1 = Stream.of(poland,france, usa, japon); 
		List<Pays> p1List = p1.collect(Collectors.toList()); 
		List<Pays> paysTrieCPc = triCPc(p1List.stream()); 
		
		System.out.println("------Question d------");
		
		List<Pays> paysTrieCPd = triCPd(p1List.stream()); 
		
		
		System.out.println("Stream de pays non trié : " + p1);
		System.out.println("Stream de pays trié par Continent puis Population" + paysTrieCPc);
		System.out.println("Stream de pays trié par Continent puis Population décroissante" + paysTrieCPd);
		
		System.out.println("------Question e------");
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		List<Ville> v = villesMoinsPeuplees(p1List.stream()); 
		System.out.println("Capital qui sont moins peuplé que certaines ville de leurs pays : [paris]"); 
		
		
		
		
	}
	
	
	
	
}
