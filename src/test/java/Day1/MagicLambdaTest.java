package Day1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhao
 * 2019/12/15 23:46
 */
public class MagicLambdaTest {
    private List<Product> products = new ArrayList<>();

    @Before
    public void init(){
        products.add(new Product(1L,"苹果手机",8888.88));
        products.add(new Product(2L,"华为手机",6666.66));
        products.add(new Product(3L,"联想笔记本",7777.77));
        products.add(new Product(4L,"机械键盘",999.99));
        products.add(new Product(5L,"雷蛇鼠标",222.22));
    }

    public interface MyPredicate<T> {
        boolean test(T t);
    }

    public class FilterProductByName implements MyPredicate<Product> {
        @Override
        public boolean test(Product t) {
            return t.getName().contains("手机");
        }
    }

    public class FilterProductByPrice implements MyPredicate<Product> {
        @Override
        public boolean test(Product t) {
            return t.getPrice() > 1000;
        }
    }

    public List<Product> filterProduct(List<Product> products,MyPredicate<Product> predicate){
        List<Product> list = new ArrayList<>();
        for (Product product : products) {
            if (predicate.test(product)) {
                list.add(product);
            }
        }
        return list;
    }

    @Test
    public void test2() throws Exception {
        List<Product> list = filterProduct(products, new FilterProductByName());
        //List<Product> list = filterProduct(products, new FilterProductByPrice());
        for (Product product : list) {
            System.out.println(product);
        }
    }


    @Test
    public void test3() throws Exception {
        List<Product> list = filterProduct(products, new MyPredicate<Product>() {
            @Override
            public boolean test(Product t) {
                return t.getName().contains("手机");
            }
        });

        for (Product product : list) {
            System.out.println(product);
        }
    }

    @Test
    public void test4() throws Exception {
        filterProduct(products, (p) -> p.getPrice() > 1000).forEach(System.out::println);
    }

    //获取价格从高到低排行前三的商品信息
    @Test
    public void test5() throws Exception {
        //获取价格从高到低排行前三的商品信息
        products.stream().sorted((x,y) -> y.getPrice().compareTo(x.getPrice()))
                .limit(3).forEach(System.out::println);
    }

    //获取价格从高到低排行前三的商品信息
    @Test
    public void test6() throws Exception {
        //获取价格从高到低排行前三的商品信息
        products.forEach(System.out::println);
    }
}
