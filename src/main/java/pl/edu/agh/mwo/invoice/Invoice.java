package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product != null) {
            if (quantity > 0) {
                if (products == null) {
                    products = new ArrayList<>();
                }
                for (int i = 0; i < quantity; i++) {
                    products.add(product);
                }
            }
            else {
                throw new IllegalArgumentException("Quantity must be positive");
            }
        }
        else {
            throw new IllegalArgumentException("Product cannot be null");
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : products) {
            subtotal = subtotal.add(product.getPrice());
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        return null;
    }

    public BigDecimal getTotal() {
        return null;
    }
}
