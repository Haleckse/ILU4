package tp1;

public class TP1 {
	
	@FunctionalInterface
	interface T1 {
		Integer apply(Integer n);
	}
	
	public static void main(String[] args) {
		T1 f = x -> x+1; 
		System.out.println(f.apply(3));
	}
}

