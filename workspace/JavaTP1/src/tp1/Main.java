package tp1;

import java.util.function.Function;

public class Main {
	@FunctionalInterface
	interface I1 {
		int apply(int n); 
	}
	
	static class T1 implements I1{
		@Override
		public
		 int apply(int n) {
			return n + 1; 
		}
	}
	
	@FunctionalInterface
	interface I2 {
		public Integer apply(Function<Integer, Integer> f); 
	}
	
	static class F2 implements I2 {
		public Integer apply(Function<Integer, Integer> f) {
			return f.apply(f.apply(0)); 
		}
	}
	
	@FunctionalInterface
	interface I3 <T>{
		public T apply(Function<T, T> f, T x); 
	}
	
	static class F3<T> implements I3<T>{
		@Override
		public T apply(Function<T, T> f, T x) {
			return f.apply(f.apply(x)); 
		}
	}
	
	public static void main(String[] args) {
		I1 i1 = x -> x + 1; 
		System.out.println("Le resultat attendu est 4 : " + i1.apply(3));
		
		T1 t1 = new T1(); 
		System.out.println("Le resultat attendu est 5 : " + t1.apply(4));
		
		I2 f2 = new F2(); 
		System.out.println("Le resultat attend est 2 : " + f2.apply(x -> x+1));
		
		I3<String> f3 = new F3<>(); 
		System.out.println(f3.apply(s -> "x" + s, "a"));
		
		
		
		
	}
}


//En utilisant l’interface fonctionnelle Function<T,R>:
//type ‘a t3 = (‘a -> ‘a) -> ‘a -> ‘a
//Traduire f3 par une méthode générique sans paramètres
//let f3 f x = f (f x)
//let _ = print_string (f3 ((^) “x”) “a”)
//Composition
