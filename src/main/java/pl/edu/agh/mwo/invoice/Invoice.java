package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Collection<Product> products = new ArrayList<>();

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        this.products.put(product,quantity);
    }

    public BigDecimal getNetPrice() {
        BigDecimal result = BigDecimal.ZERO;
        for (Product product : this.products) {
            result = result.add(product.getPrice());
        }
        return result;
    }

    public BigDecimal getTax() {
        return BigDecimal.ZERO;
    }

    public BigDecimal getGrossPrice() {
        return BigDecimal.ZERO;
    }
}
