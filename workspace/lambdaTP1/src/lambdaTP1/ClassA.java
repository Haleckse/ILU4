package lambdaTP1;

import java.util.function.Function;

public class A {
	public interface T1 {
		public Integer trait(Integer i); 
	} 
	
	public static class F1 implements T1 {
		
		@Override
		public Integer trait(Integer i) {
			return i + 1; 
		}
	}
	
	
	public static void main(String[] args) { 
        T1 f1 = i -> i+1; 
        System.out.println(f1.trait(3));  
	}
	
	
}
