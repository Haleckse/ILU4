package tp1;

import java.util.function.Function;

//import tp1.TP1.T1;

public class TP1 {
	
	@FunctionalInterface
	interface T1 {
		Integer apply(Integer n);
	}
	
	@FunctionalInterface
	interface T2 {
		Integer apply(Function<Integer, Integer> f); 
	}
	
	@FunctionalInterface
	interface T3<T> {
		T apply(Function<T, T> fu, T x); 
	}
	
//	class F3<T> implements T3<T> {
//		@Override
//		public T apply(Function<T, T> fu, T x) {
//			return fu.apply(fu.apply(x)); 
//		}
//
//	}
	static <T> T3<T> f3() { return (fu, x) -> fu.apply( fu.apply(x) ); }

	
	
	public static void main(String[] args) {
		T1 f1 = x -> x+1; 
		System.out.println("test de A : " + f1.apply(3));
		
		T2 f2 = f -> f.apply(f.apply(0)); 
		System.out.println("test de B : " + f2.apply(x -> x+1));
		
		
				
		System.out.println(f3().apply(s -> "x" + s, "a")); // Affiche "xxa"
	}
	
}

