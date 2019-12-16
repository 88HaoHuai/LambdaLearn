package Day2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hhao
 * 2019/12/17 00:10
 */
public class LambdaLazyDemoTest {
    @Test
    public void testLambdaLazy() throws Exception {
        String msgA = "Hello";
        String msgB = "World";
        String msgC = "Java";

        log(1, msgA + msgB + msgC);

        log(1, (msg) -> msgA + msgB + msgC);

        log(2, (msg) -> {
            System.out.println("Lambda执行！");//这句话没有被输出,说明Lambda是延迟执行的
            return msgA + msgB + msgC;
        });
    }

    private static void log(int level, String msg) {
        if (level == 1) {//不论条件是否满足,msg都是进行拼接然后传递过来的
            System.out.println(msg);
        }
    }

    private static void log(int level, MessageBuilder builder) {
        if (level == 1) {//只有当条件满足才会执行Lambda表达式中的拼接
            System.out.println(builder.buildMessage());
        }
    }
}

@FunctionalInterface
interface MessageBuilder {
    //String... msgs 是java里面的可变参数类型，代表类型明确但个数不明确的一组参数，Java实际上把这个当做数组来处理的
    String buildMessage(String... msgs);
}