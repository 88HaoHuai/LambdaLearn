package Day3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by hhao
 * 2019/12/17 23:25
 */
public class ThirdDayTest {
    @Test
    public void testWithStream() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("马云");
        list.add("马化腾");
        list.add("李彦宏");
        list.add("雷军");
        list.add("刘强东");
        list.stream()
                .filter(s -> s.startsWith("马"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println);
    }

    @Test
    public void testWithStreamInt() throws Exception {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.stream()
                .filter(s -> !s.equals(s=0))
                .forEach(System.out::println);
    }

    @Test
    public void test2() throws Exception {
        System.out.println("=======================外部迭代=========================");
        List<Integer> list = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("=======================内部迭代=========================");
        Stream<Integer> stream = list.stream().filter(i -> {
            System.out.println("内部迭代惰性求值");
            return i % 2 == 0;
        });
        stream.forEach(System.out::println);//只有执行终止操作的时候才会真正执行中间操作(惰性求值)
    }
}