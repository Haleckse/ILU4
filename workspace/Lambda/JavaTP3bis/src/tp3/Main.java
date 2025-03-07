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
		  void ajouterVille(Ville v) ;
	
		  
		}
	
	interface Ville {
		  Pays getPays();
		  int getPopulation();
		  String toString();
		}
	
//	static record VilleRecord(String nom, Pays pays, int population) implements Ville {
//		
//		@Override
//		public Pays getPays() {
//			return pays;
//		}
//		
//		@Override
//		public int getPopulation() {
//			return population; 
//		}
//		
//		@Override 
//		public final String toString() {
//			return nom;
//		}
//		
//		
//	}
	
	
	static class VilleR implements Ville {
		
		private String nom; 
		private Pays pays; 
		private int population; 
		
		
		
		public VilleR(String nom, Pays pays, int population) {
			super();
			this.nom = nom;
			this.pays = pays;
			this.population = population;
		}

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
	
	static class PaysR implements Pays {
		private List<Ville> villes; 
		private String continent; 
		private Ville capital; 
		private int population; 
		
		public PaysR(String continent, Ville capital, int population) {
			this.villes = new ArrayList<>();
			this.continent = continent; 
			this.capital = capital; 
			this.population = population; 
		}
		
		@Override
		public List<Ville> getVilles() {
			return villes;
		}

		@Override
		public String getContinent() {
			return continent;
		}

		@Override
		public Ville getCapitale() {
			return capital;
		}

		@Override
		public int getPopulation() {
			return population;
		}
		
		@Override
		public void ajouterVille(Ville v) {
			this.villes.add(v); 
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
		

		Pays france = new PaysR("Europe", null, 670000);
		Pays poland = new PaysR("Europe", null, 670000);
		Pays usa = new PaysR("NorthAmerica", null, 880000);
		Pays japon = new PaysR("Asia", null, 436000);

		// Étape 2 : Création des villes en leur attribuant leur pays respectif
		Ville paris = new VilleR("Paris", france, 7500 );
		Ville marseille = new VilleR("Marseille", france, 2000000);
		france.ajouterVille(marseille);
		france.ajouterVille(paris);
		

		Ville krakovich = new VilleR("Krakovich", poland, 5000);
		Ville varsovie = new VilleR("Varsovie", poland, 2000);
		poland.ajouterVille(varsovie);
		poland.ajouterVille(krakovich);

		Ville newYork = new VilleR("NewYork", usa, 83000);
		Ville chicago = new VilleR("Chicago", usa, 87000);
		usa.ajouterVille(newYork);
		usa.ajouterVille(chicago);
		
		Ville tokyo = new VilleR("Tokyo", japon, 27000);
		Ville kyoto = new VilleR("Kyoto", japon, 16000);
		japon.ajouterVille(tokyo);
		japon.ajouterVille(kyoto);
		

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
