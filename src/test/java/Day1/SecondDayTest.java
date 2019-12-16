package Day1;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author huaihao
 * @create 2019-12-16-11:27
 */
public class SecondDayTest {
    @Test
    public void test1() throws Exception {

        String[] array = {"a", "c"};

        // 以下是lambda表达式的语法特征:
        // 1.可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
        Arrays.asList("a", "c", "d").forEach((String x) -> System.out.print(x));// 声明了参数类型
        System.out.println();
        Arrays.asList("a", "b", "c").forEach((x) -> System.out.print(x));// 省略了参数类型
        System.out.println("\n"+"1 end =========");

        // 2.可选的参数圆括号()：一个参数无需定义圆括号，但没有参数或多个参数需要定义圆括号()。
        Arrays.asList("a", "b", "c").forEach(x -> System.out.print(x));// 一个参数可以省略()
        new Thread(() -> System.out.println("没有参数")).start();// 没有参数不可以省略()
        System.out.println();
        Arrays.asList("a", "b", "c").stream().sorted((x, y) -> x.compareTo(y)).forEach(x -> System.out.print(x));// 多个参数不可以省略()
        System.out.println("\n"+"2 end =========");
        System.out.println();

        // 3.可选的大括号{}：如果主体包含了一个语句，就不需要使用{}。
        Arrays.asList("a", "d", "b","c").stream().sorted((x, y) -> {
            System.out.println("Lambda中有多条语句,return和{}不可以省略"+y.compareTo(x));
            return y.compareTo(x);
        }).forEach(x -> System.out.print(x));// 多条语句不可以省略{}
        System.out.println();

        Arrays.asList(array).stream().sorted((x, y) -> {
            System.out.println("Lambda中有多条语句,return和{}不可以省略");
            return y.compareTo(x);
        }).forEach(y -> System.out.print(y));// 多条语句不可以省略{}

        System.out.println();
        Arrays.asList("a", "b", "c").stream().sorted((x, y) -> y.compareTo(x)).forEach(x -> System.out.println(x));// 一条语句可以省略{}
        Arrays.asList("a1", "c3", "b2").stream().sorted((x,y)->y.compareTo(x)).forEach(x -> System.out.println(x));
        System.out.println("\n"+"3 end =========");

        // 4.可选的返回关键字return：如果主体只有一个表达式返回值则可以省略return和{}
        Arrays.asList("a", "c", "b").stream().sorted((x, y) -> {
            System.out.println("Lambda中有多条语句,return和{}不可以省略");
            return y.compareTo(x);
        }).forEach(x -> System.out.print(x));// 多条语句不可以省略{}
        System.out.println();
        Arrays.asList("a", "b", "c").stream().sorted((x, y) -> y.compareTo(x)).forEach(x -> System.out.print(x));// 省略{}和return
        System.out.println("\n"+"4 end =========");

        System.out.println("1".compareTo("4"));
    }

}