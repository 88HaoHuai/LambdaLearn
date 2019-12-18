package Day3;


import Day3.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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

    /**
     * 排序

     sorted()：自然排序使用Comparable<T>的int compareTo(T o)方法
     sorted(Comparator<T> com)：定制排序使用Comparator的int compare(T o1, T o2)方法
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        System.out.println("====================自然排序============================");
        Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().sorted().forEach(System.out::println);
        System.out.println("====================定制排序============================");
        Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().sorted((x,y) -> y.compareTo(x)).forEach(System.out::println);
    }

    /**
     * 查找匹配
     allMatch:检查是否匹配所有元素
     anyMatch:检查是否至少匹配一个元素
     noneMatch:检查是否没有匹配的元素
     findFirst:返回第一个元素(返回值为Optional<T>)
     findAny:返回当前流中的任意元素(一般用于并行流)
     */
    @Test
    public void test5() throws Exception {
        System.out.println("======================检查是否匹配所有==========================");
        boolean allMatch = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().allMatch(x-> x>0);
        System.out.println(allMatch);
        System.out.println("======================检查是否至少匹配一个元素====================");
        boolean anyMatch = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().anyMatch(x -> x>7);
        System.out.println(anyMatch);
        System.out.println("======================检查是否没有匹配的元素======================");
        boolean noneMatch = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().noneMatch(x -> x >10);
        System.out.println(noneMatch);
        System.out.println("======================返回第一个元素==========================");
        Optional<Integer> first = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().findFirst();
        System.out.println(first.get());
        System.out.println("======================返回当前流中的任意元素=======================");
        Optional<Integer> any = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().findAny();
        System.out.println(any.get());
    }

    /**
     *统计
     count():返回流中元素的总个数
     max(Comparator<T>):返回流中最大值
     min(Comparator<T>):返回流中最小值
     */
    @Test
    public void test6() throws Exception {
        long count = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().count();
        System.out.println(count);
        Optional<Integer> max = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().max((x,y) -> x.compareTo(y));
        System.out.println(max.get());
        Optional<Integer> min = Arrays.asList(3, 2, 1, 4, 5, 8, 6).stream().min((x,y) -> x.compareTo(y));
        System.out.println(min.get());
    }

    /**
     * 归约
     reduce(T identity, BinaryOperator) / reduce(BinaryOperator) :将流中元素挨个结合起来，得到一个值。
     */
    @Test
    public void test7() throws Exception {
        System.out.println("=====reduce:将流中元素反复结合起来，得到一个值==========");
        Stream<Integer> stream = Stream.iterate(1, x -> x+1).limit(100);
//        stream.forEach(System.out::println);
        Integer sum = stream.reduce(0,(x,y)-> x+y);
        System.out.println(sum);
    }

    @Test
    public void test8() throws Exception {
        System.out.println("=====collect:将流转换为其他形式:list");
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(100).collect(Collectors.toList());
        System.out.println(list);
        System.out.println("=====collect:将流转换为其他形式:set");
        Set<Integer> set = Arrays.asList(1, 1, 2, 2, 3, 3, 3).stream().collect(Collectors.toSet());
        System.out.println(set);
        System.out.println("=====collect:将流转换为其他形式:TreeSet");
        TreeSet<Integer> treeSet = Arrays.asList(1, 1, 2, 2, 3, 3, 3).stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);
        System.out.println("=====collect:将流转换为其他形式:map");
        Map<Integer, Integer> map = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.toMap(Integer::intValue, Integer::intValue));
        System.out.println(map);
        System.out.println("=====collect:将流转换为其他形式:sum");
        Integer sum = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.summingInt(Integer::intValue));
        System.out.println(sum);
        System.out.println("=====collect:将流转换为其他形式:avg");
        Double avg = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.averagingInt(Integer::intValue));
        System.out.println(avg);
        System.out.println("=====collect:将流转换为其他形式:max");
        Optional<Integer> max = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(max.get());
        System.out.println("=====collect:将流转换为其他形式:min");
        Optional<Integer> min = Stream.iterate(1, x -> x+1).limit(100).collect(Collectors.minBy((x,y) -> x-y));
        System.out.println(min.get());

    }

    /**
     * 分区partitioningBy
     * 分区的好处在于保留分区函数返回true或false的两套流元素列表
     */
    @Test
    public void test11(){
        List<String> list = Arrays.asList("java", "python", "C++","php","java");
        Map<Boolean, List<String>> result = list.stream().collect(Collectors.partitioningBy(s->s.length() > 3));
        System.out.println("符合条件："+result.get(Boolean.TRUE));
        System.out.println("不符合条件："+result.get(Boolean.FALSE));
    }


    private List<Product> products = new ArrayList<>();

    @Before
    public void init() {
        products.add(new Product(1L, "苹果手机", 8888.88,"手机"));//注意:要给Product类加一个分类名称dirName字段
        products.add(new Product(2L, "华为手机", 6666.66,"手机"));
        products.add(new Product(3L, "联想笔记本", 7777.77,"电脑"));
        products.add(new Product(4L, "机械键盘", 999.99,"键盘"));
        products.add(new Product(5L, "雷蛇鼠标", 222.22,"鼠标"));
    }



    @Test
    public void test9() throws Exception {
        System.out.println("=======根据商品分类名称进行分组==========================");
        Map<String, List<Product>> map = products.stream().collect(Collectors.groupingBy(Product::getDirName));
        System.out.println(map);
        System.out.println("=======根据商品价格范围多级分组==========================");
        Map<Double, Map<String, List<Product>>> map2 = products.stream().collect(Collectors.groupingBy(
                Product::getPrice, Collectors.groupingBy((p) -> {
                    if (p.getPrice() > 1000) {
                        return "高级货";
                    } else {
                        return "便宜货";
                    }
                })));
        System.out.println(map2);

    }


    @Test
    public void test10() throws Exception {
        System.out.println("========根据商品价格是否大于1000进行分区========================");
        Map<Boolean, List<Product>> map = products.stream().collect(Collectors.partitioningBy(p -> p.getPrice() > 1000));
        System.out.println(map);
    }


    /**
     * Stream分类
     ●中间操作(intermediate operations)
     返回值为Stream的大都是中间操作，中间操作支持链式调用，并且会惰式执行

     ●终端操作(结束操作)(terminal operations)
     返回值不为Stream 的为终端操作(立即求值)，终端操作不支持链式调用，会触发实际计算

     作者：叩丁狼教育
     链接：https://www.jianshu.com/p/f3c2c32d981f
     来源：简书
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

}