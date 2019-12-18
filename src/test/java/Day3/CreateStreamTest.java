package Day3;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author huaihao
 * @create 2019-12-18-19:56
 */
public class CreateStreamTest {

    @Test
    public void testCreateStream(){
        // 1.Collection的默认方法stream()和parallelStream()
        List<String> list= Arrays.asList("a","b","c");
        Stream<String> stream = list.stream(); // 获取顺序流
        Stream<String> parallelStream = list.parallelStream(); //parallelStream提供了流的并行处理

//        stream.forEach(System.out::println);
//        parallelStream.forEach(System.out::println);

        // 2.Arrays.stream()
        IntStream intStream1 = Arrays.stream(new int[]{1, 2, 3});
        Stream<Integer> integerStream1 = Arrays.stream(new Integer[]{1, 2, 3});

        // 3.Stream.of()
        // of(T... values)：返回含有多个T元素的Stream
        // of(T t)：返回含有一个T元素的Stream
        IntStream intStream2 = IntStream.of(1, 2, 3);
        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4);

        // 4.Stream.iterate()//迭代无限流
        // Stream.iterate(1, n->n +1).forEach(System.out::println);
        Stream.iterate(1, n -> n + 1).limit(100).forEach(System.out::println);

        // 5.Stream.generate()//生成无限流
        // Stream.generate(Math::random).forEach(System.out::println);
        Stream.generate(Math::random).limit(2).forEach(System.out::println);



    }

    /*
    筛选和切片
    filter(Predicate<T> p)：过滤(根据传入的Lambda返回的ture/false 从流中过滤掉某些数据(筛选出某些数据))
    distinct()：去重(根据流中数据的 hashCode和 equals去除重复元素)
    limit(long n)：限定保留n个数据
    skip(long n)：跳过n个数据
     */
    @Test
    public void test1() throws Exception {

        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().filter(i -> i % 2 == 0).forEach(System.out::println);
        System.out.println("================================================");
        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println("================================================");
        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().distinct().limit(2).forEach(System.out::println);
        System.out.println("================================================");
        Arrays.asList(1, 2, 1, 3, 3, 2, 4).stream().distinct().skip(2).forEach(System.out::println);

        /**
         * Java 8 Stream 流的重用
         */
        Integer[] integers = {1, 2, 1, 3, 3, 2, 4};
        Supplier<Stream<Integer>> streamSupplier = () -> Stream.of(integers);
        streamSupplier.get().filter(i -> i % 2 == 0).forEach(System.out::println);
        System.out.println("================================================");
        streamSupplier.get().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println("================================================");
        streamSupplier.get().distinct().limit(2).forEach(System.out::println);
        System.out.println("================================================");
        streamSupplier.get().distinct().skip(2).forEach(System.out::println);

    }

    /**
     * 映射
     * map(Function<T, R> f)：接收一个函数作为参数，该函数会被应用到流中的每个元素上，并将其映射成一个新的元素。
     * flatMap(Function<T, Stream<R>> mapper)：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test3() throws Exception {
        System.out.println("=======================map=========================");
        Stream<String> stream = Stream.of("i","love","java");
        stream.map(s -> s.toUpperCase()).forEach(System.out::println);
        System.out.println("=======================flatMap========================");
        Stream<List<String>> stream2 = Stream.of(Arrays.asList("H","E"), Arrays.asList("L", "L", "O"));
        stream2.flatMap(list -> list.stream()).forEach(System.out::println);
    }
}