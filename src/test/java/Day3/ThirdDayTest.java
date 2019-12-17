package Day3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
}