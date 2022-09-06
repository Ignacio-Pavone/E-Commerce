package domain;

import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
@Getter
@Setter
public class ShoppingCart {
   private Long sellCode;
   private HashMap<Long,SellProduct> productList;
   private Integer totalProducts;
   private Double totalPrice;
}
