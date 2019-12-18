package Day2;

import Day1.Product;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;

/**
 * Created by hhao
 * 2019/12/16 21:47
 */
public class SecondDayQuoteTest {
    @Test
    public void test1() throws Exception {
        // 1.对象的引用 :: 实例方法名
        //Arrays.asList(1,2,3).forEach((i) -> System.out.println(i));
        PrintStream printStream = System.out;
        Arrays.asList(1,2,3).forEach(printStream::println);
        Arrays.asList(1,2,3).forEach(System.out::println);
        System.out.println("================================================");

        // 2.类名 :: 静态方法名
        //Supplier<Double> s = () -> Math.random();
        Supplier<Double> s = Math::random;
        System.out.println(s.get());

        System.out.println(Math.random());
        System.out.println("================================================");

        // 3.类名 :: 实例方法名
        //Function<Product,String> f = (p) -> p.getName();
        Function<Product,String> f = Product::getName;
        String name = f.apply(new Product(1L, "锤子手机", 999.99));
        System.out.println(name);
        System.out.println("================================================");

        // 4.类名 :: new
        //Supplier<Product> sp = ()-> new Product();
        Supplier<Product> sp = Product::new;
        Product product = sp.get();
        System.out.println(product);
        System.out.println("================================================");

        // 5.类型[] :: new
        Function<Integer,String[]> f2 = String[]::new;
        String[] strings = f2.apply(20);
        System.out.println(strings.length);
    }
}