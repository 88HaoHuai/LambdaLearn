package Day1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hhao
 * 2019/12/15 22:49
 */
public class FirstDayTest {
    @Test
    public void testTradition() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("使用传统方式开启线程");
            }
        }).start();
    }

    @Test
    public void testLambda() {
        new Thread(() -> System.out.println("使用Lambda方式开启线程")).start();
    }

    @Test
    public void fist() {
        System.out.println("11");
    }

}