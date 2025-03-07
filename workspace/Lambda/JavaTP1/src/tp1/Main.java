package tp1;

import java.util.*;
import java.util.function.*;

public class Main {
    @FunctionalInterface
    interface I1 {
        int apply(int n);
    }

    static class T1 implements I1 {
        @Override
        public int apply(int n) {
            return n + 1;
        }
    }

    @FunctionalInterface
    interface I2 {
        Integer apply(Function<Integer, Integer> f);
    }

    static class F2 implements I2 {
        public Integer apply(Function<Integer, Integer> f) {
            return f.apply(f.apply(0));
        }
    }

    @FunctionalInterface
    interface I3<T> {
        T apply(Function<T, T> f, T x);
    }

    static class F3<T> implements I3<T> {
        @Override
        public T apply(Function<T, T> f, T x) {
            return f.apply(f.apply(x));
        }
    }

    public static <T1, T2> T2 apply(Function<T1, T2> f, T1 x) {
        return f.apply(x);
    }

    public static <F, G, X> G andThen(Function<X, F> f, Function<F, G> g, X x) {
        return g.apply(f.apply(x));
    }

    static class Composition {
        public static <T, R> R apply(Function<T, R> f, T x) {
            return f.apply(x);
        }

        public static <T, R, V> Function<T, V> andThen(Function<T, R> f, Function<R, V> g) {
            return x -> g.apply(f.apply(x));
        }
    }

    interface Power<T> extends Function<T, T> {
        static <T> Function<T, T> identity() {
            return x -> x;
        }

        static <T> Function<T, T> andThen(Function<T, T> f, Function<T, T> g) {
            return x -> g.apply(f.apply(x));
        }

        static <T> Function<T, T> power(int n, Function<T, T> f) {
            if (n == 0) return identity();
            return andThen(f, power(n - 1, f));
        }
    }

    static class Comp2 {
        public static <T, U, V> Function<T, V> comp2(Function<T, U> f1, Function<T, U> f2, BiFunction<U, U, V> g) {
            return x -> g.apply(f1.apply(x), f2.apply(x));
        }
    }

    static class FList {
        public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
            List<R> result = new ArrayList<>();
            for (T item : list) {
                result.add(f.apply(item));
            }
            return result;
        }

        public static <T> List<T> filter(List<T> list, Predicate<T> p) {
            List<T> result = new ArrayList<>();
            for (T item : list) {
                if (p.test(item)) {
                    result.add(item);
                }
            }
            return result;
        }
    }

    static class FIter {
        public static <T, R> Iterable<R> map(Iterable<T> iterable, Function<T, R> f) {
            List<R> result = new ArrayList<>();
            for (T item : iterable) {
                result.add(f.apply(item));
            }
            return result;
        }

        public static <T> Iterable<T> filter(Iterable<T> iterable, Predicate<T> p) {
            List<T> result = new ArrayList<>();
            for (T item : iterable) {
                if (p.test(item)) {
                    result.add(item);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        I1 i1 = x -> x + 1;
        System.out.println("Le resultat attendu est 4 : " + i1.apply(3));

        T1 t1 = new T1();
        System.out.println("Le resultat attendu est 5 : " + t1.apply(4));

        I2 f2 = new F2();
        System.out.println("Le resultat attendu est 2 : " + f2.apply(x -> x + 1));

        I3<String> f3 = new F3<>();
        System.out.println(f3.apply(s -> "x" + s, "a"));

        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> composed = Composition.andThen(addOne, multiplyByTwo);
        System.out.println(Composition.apply(composed, 3));

        Function<Integer, Integer> powerFunction = Power.power(3, x -> x + 1);
        System.out.println(powerFunction.apply(0));

//        System.out.println(Comp2.comp2(x -> x + 1, x -> x * 2, Math::max).apply(2));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println(FList.map(numbers, x -> x * 2));
        System.out.println(FList.filter(numbers, x -> x % 2 == 0));
    }
}
