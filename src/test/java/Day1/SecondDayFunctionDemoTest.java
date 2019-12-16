package Day1;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

import static org.junit.Assert.*;

/**
 * @author huaihao
 * @create 2019-12-16-15:59
 */
public class SecondDayFunctionDemoTest {

    //  函数式接口类型  参数类型  返回类型  说明
    //  Consumer<T>消费型接口  T void  对类型为T的对象操作，方法：void accept(T t)
    //  方法：void accept(T t):该函数式接口的唯一的抽象方法,接收一个参数,没有返回值.
    @Test
    public void test1() throws Exception {
        shop(10000.0, new Consumer<Double>() {
            @Override
            public void accept(Double money) {
                System.out.println("购买电脑花费:" + money + "元");
            }
        });
        shop(5000.0, money-> System.out.println("购买手机花费:" + money + "元"));
    }
    public static void shop(Double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test11() throws Exception {
        method("赵丽颖", name -> {
            StringBuilder builder = new StringBuilder();
            builder.append(name);
            System.out.println(builder.reverse().toString());
        });
    }

    public static void method(String name, Consumer<String> consumer){
        consumer.accept(name);
    }

    // 在执行完调用者方法后再执行传入参数的方法
    @Test
    public void test12(){
        Consumer<Integer> consumer = (x) -> {
            int num = x * 2;
            System.out.println(num);
        };
        Consumer<Integer> consumer1 = (x) -> {
            int num = x * 3;
            System.out.println(num);
        };
        consumer.andThen(consumer1).accept(10);
    }

    //===================================================================================
    //  函数式接口类型  参数类型  返回类型 说明
    //  Supplier<T>供给型接口  无  T    返回类型为T的对象，方法：T get();可用作工厂
    @Test
    public void test2() throws Exception {
        String code = getCode(4, ()-> new Random().nextInt(10));
        System.out.println(code);
    }
    //获取指定位数的验证码
    public String getCode(int num, Supplier<Integer> sup){
        System.out.println("=="+sup.get()+"==");
        String str = "";
        for (int i = 0; i <num ; i++) {
            str += sup.get();
        }
        return str;
    }

    @Test
    public void test22(){
        //若方法体只有一条语句可以省略{} 和 return
        String result = getString(() -> "胡歌");
        System.out.println(result);
    }
    public static String getString(Supplier<String> supplier){
        return supplier.get();
    }


    //===================================================================================
    //  函数式接口类型  参数类型  返回类型  说明
    //  Function<T, R>函数型接口  T  R  对类型为T的对象操作，并返回结果是R类型的对象。方法：R apply(T t);
    //  方法：R apply(T t)：将此参数应用到函数中
    @Test
    public void test3() throws Exception {
        int length = getStringRealLength(" www.wolfcode.cn  ", str -> str.trim().length());
        System.out.println(length);

    }
    public int getStringRealLength(String str, Function<String,Integer> fun){
        return fun.apply(str);
    }

    //===================================================================================
    //  函数式接口类型  参数类型  返回类型  说明
    //  Predicate<T>断言型接口  T  boolean  判断类型为T的对象是否满足条件，并返回boolean 值。方法boolean test(T t);
    //  boolean test(T t):根据给定的参数进行判断
    @Test
    public void test4() throws Exception {
        List<String> list = getString(Arrays.asList("Java8","Lambda","wolfcode"), s -> s.length() > 5);
        System.out.println(list);
        //后面学习了Stream API操作更简单
        Arrays.asList("Java8","Lambda","wolfcode").stream().filter(s -> s.length() > 5).forEach(System.out::println);

    }
    //获取集合中长度大于5的字符串
    public List<String> getString(List<String> list, Predicate<String> pre){
        List<String> newList = new ArrayList<>();
        for (String string : list) {
            //根据给定的参数进行判断
            if (pre.test(string)) {
                newList.add(string);
            }
        }
        return newList;
    }


    @Test
    public void testLocalDateTime(){
        // 获取当前的日期时间
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("当前时间: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("date1: " + date1);

        Month month = currentTime.getMonth().firstMonthOfQuarter();
        int day = currentTime.getDayOfMonth();
        int seconds = currentTime.getSecond();

        System.out.println("月: " + month +", 日: " + day +", 秒: " + seconds);

        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
        System.out.println("date2: " + date2);

        // 12 december 2014
        LocalDate date3 = LocalDate.of(2014, Month.DECEMBER, 12);
        System.out.println("date3: " + date3);

        // 22 小时 15 分钟
        LocalTime date4 = LocalTime.of(22, 15);
        System.out.println("date4: " + date4);

        // 解析字符串
        LocalTime date5 = LocalTime.parse("20:15:30");
        System.out.println("date5: " + date5);
    }

}