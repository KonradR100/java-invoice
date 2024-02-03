package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        //ważna kolejność, na odwrót nie zadzaiała
//            throw new NameIsNullException;
//        if (name == ""){
//                throw new IllegalArgumentException("Product name cannot be empty");
//        }
        this.name = name;

        if (price == null || price.compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
            //if (price == null ||price.doubleValue() < 0){
            //throw new IllegalArgumentException("Price cannot be null or negative");
        }

//        if (price.compareTo(BigDecimal.ZERO)<0) {
//            throw new IllegalArgumentException("Product price cannot be negative");
//        }
        this.price = price;

        this.taxPercent = tax;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public BigDecimal getPriceWithTax() {
        return price.add(taxPercent.multiply(price));
    }
}
