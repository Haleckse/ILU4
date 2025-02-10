package tp2;

import java.util.function.Function;

public class Main {
	@FunctionalInterface 
	interface Integrable {
		
		default public double integr(double a, double b, int n) {
			double h = (b - a) / n; 
		    double sum = 0.5 * (f(a) + f(b));
		
		    for (int i = 1; i < n; i++) {
		        double x = a + i * h;
		        sum += f(x);
		    }
		
		    return sum * h;
		}
		
		public double f(double x); 
	}
	

	static class squareFunction implements Integrable {
		@Override 
		public double f(double x) {
			return x*x;
		}
	}
	
	@FunctionalInterface
	interface Expr<V> {
		int eval(Function<V, Integer> env); 
		
		static <V> Expr<V> Var(V v) {
			return env -> env.apply(v); 
		}
		
		default Expr<V> add(Expr<V> e) {
			return env -> this.eval(env) + e.eval(env); 
		}
		
		default Expr<V> mul(Expr<V> e) {
			return env -> this.eval(env) * e.eval(env); 
		}
		
	}
	
	interface HasSucc<N extends HasSucc<N>> {
		N Succ(); // renvoie le successeur de l’objet courant
		<T> T cases(T x, Function<N,T> f); // renvoie x si this n’est pas un successeur, f(p) si this est le successeur de p
		
		default boolean isSucc() {
			return cases(false, p -> true);  
		}
		
		default N pred() {
			return cases((N) this, p -> p); 
		}
	}

	interface HasPlus<N extends HasPlus<N>> {
	    N Succ(); // Renvoie le successeur
	    <T> T cases(T x, Function<N, T> f); // Cas base ou récursif

	    // Addition définie récursivement
	    default N add(N other) {
	        return this.cases(other, p -> p.add(other).Succ());
	    }
	}
	
	// Classe représentant les nombres naturels
	class MyNat implements HasPlus<MyNat> {
	    private final MyNat pred; // Le prédécesseur (null si c'est zéro)
	    private final boolean isZero; // Indique si c'est zéro

	    // Constructeur pour Zero
	    public MyNat() {
	        this.pred = null;
	        this.isZero = true;
	    }

	    // Constructeur pour un successeur
	    private MyNat(MyNat pred) {
	        this.pred = pred;
	        this.isZero = false;
	    }

	    // Crée un successeur
	    @Override
	    public MyNat Succ() {
	        return new MyNat(this);
	    }

	    // Implémentation de cases
	    @Override
	    public <T> T cases(T x, Function<MyNat, T> f) {
	        return isZero ? x : f.apply(pred);
	    }

	    // Convertit l'objet en un entier pour affichage
	    @Override
	    public String toString() {
	        int count = 0;
	        MyNat current = this;
	        while (!current.isZero) {
	            count++;
	            current = current.pred;
	        }
	        return String.valueOf(count);
	    }

	    // Méthode utilitaire pour créer un nombre entier
	    public static MyNat of(int n) {
	        MyNat result = new MyNat(); // Zéro
	        for (int i = 0; i < n; i++) {
	            result = result.Succ();
	        }
	        return result;
	    }
	}

	
	
	public static void main(String[] args) {
		
		System.out.println("------EXO1------");
		Integrable square = new squareFunction(); 
		Integrable sin = x -> Math.sin(x); 
		System.out.println("Integralle de la fonction x -> x*x sur [0,10] : " + square.integr(0, 10, 1000));
		System.out.println("Integralle de la fonction x -> sin x sur [0,PI] : " + sin.integr(0, Math.PI, 1000));
		
		System.out.println("------EXO2------");
		
		Expr<String> x = Expr.Var("x"); 
		Expr<String> y = Expr.Var("y");
		
		Expr<String> z = x.mul(x).add(y); 
		
		Function<String, Integer> eval = var -> {
			switch (var) {
				case "x" : return 2; 
				case "y" : return 5; 
				default : return -99; 
			}
		}; 
		
		int result = z.eval(eval); 
		System.out.println("resultat de l'expression 2*2+5 : " + result);
		
		System.out.println("------EXO3------");
		MyNat n1 = MyNat.of(2);
        MyNat n2 = MyNat.of(3);
        System.out.println("Somme: " + n1.add(n2)); // Devrait afficher 5

	}
	

	
}

