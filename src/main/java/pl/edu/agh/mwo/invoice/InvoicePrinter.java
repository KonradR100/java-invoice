package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;

public class InvoicePrinter {

    public Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public String invoiceNumber() {
        return "Numer faktury: " + invoice.getNumber() + "\n\n";
    }

    public String printInvoice() {
        String invoiceDetails = invoiceNumber();
        Map<Product, Integer> products = invoice.getProducts();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            BigDecimal totalPrice = product.getPriceWithTax().multiply(new BigDecimal(quantity));

            invoiceDetails += "Produkt: " + product.getName()
                    + ", Ilość: " + quantity
                    + ", Cena netto: " + product.getPrice()
                    + ", Cena brutto: " + totalPrice + "\n";
        }

        invoiceDetails += "\nLiczba pozycji: " + products.size();
        return invoiceDetails;
    }
}

