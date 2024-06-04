package pl.edu.agh.mwo.invoice;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.Invoice;
import pl.edu.agh.mwo.invoice.InvoicePrinter;
import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

import java.math.BigDecimal;

public class InvoicePrinterTest {

    private Invoice invoice;
    private InvoicePrinter printer;

    @Before
    public void createInvoiceForTheTests() {
        invoice = new Invoice();
        printer = new InvoicePrinter(invoice);
    }

    @Test
    public void testInvoiceNumber() {
        String invoiceNumber = printer.invoiceNumber();
        assertTrue(invoiceNumber.contains("Numer faktury: "+invoice.getNumber()));
    }

    @Test
    public void testPrintedProductsCount() {
        Product cream = new DairyProduct("Śmietana", new BigDecimal("3.75"));
        Product roll = new TaxFreeProduct("Bułka", new BigDecimal("1.50"));
        Product towel = new OtherProduct("Ręcznik", new BigDecimal("15.00"));
        invoice.addProduct(cream, 2);
        invoice.addProduct(roll);
        invoice.addProduct(towel);
        String invoiceDetails = printer.printInvoice();
        assertTrue(invoiceDetails.contains("Liczba pozycji: 3"));
    }

    @Test
    public void testIfProductsAreDisplayedCorrectly() {
        Product cream = new DairyProduct("Śmietana", new BigDecimal("3.75"));
        Product roll = new TaxFreeProduct("Bułka", new BigDecimal("1.50"));
        Product towel = new OtherProduct("Ręcznik", new BigDecimal("25.00"));
        invoice.addProduct(cream);
        invoice.addProduct(roll);
        invoice.addProduct(towel);
        String invoiceDetails = printer.printInvoice();
        assertTrue(invoiceDetails.contains("Produkt: Śmietana"));
        assertTrue(invoiceDetails.contains("Produkt: Bułka"));
        assertTrue(invoiceDetails.contains("Produkt: Ręcznik"));
    }

    @Test
    public void testIfRepeatedProductsAreNotDuplicated() {
        Product cheese = new DairyProduct("Ser", new BigDecimal("10.00"));
        Product book = new OtherProduct("Książka", new BigDecimal("25.40"));
        invoice.addProduct(cheese, 2);
        invoice.addProduct(book, 3);
        invoice.addProduct(cheese, 3);
        invoice.addProduct(book, 4);
        String invoiceDetails = printer.printInvoice();
        assertFalse(invoiceDetails.contains("Produkt: Ser, Ilość: 2"));
        assertFalse(invoiceDetails.contains("Produkt: Książka, Ilość: 3"));
        assertTrue(invoiceDetails.contains("Produkt: Ser, Ilość: 5"));
        assertTrue(invoiceDetails.contains("Produkt: Książka, Ilość: 7"));
    }
}
