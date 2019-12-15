package Day1;

import lombok.*;

/**
 * Created by hhao
 * 2019/12/16 00:20
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product {
    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private Long id; //商品编号
    private String name; //商品名称
    private Double price; //商品价格

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
