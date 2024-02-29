package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;


    //Tworzymy pustą listę produktów
    {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product != null) {
            if (quantity > 0) {
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
            BigDecimal tax = BigDecimal.ZERO;
            for (Product product : products) {
                tax = tax.add(product.getTaxPercent().multiply(product.getPrice()));
            }
            return tax;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : products) {
            total = total.add(product.getPriceWithTax());
        }
        return total;
    }
}
