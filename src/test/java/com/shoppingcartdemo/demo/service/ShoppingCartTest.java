package com.shoppingcartdemo.demo.service;

import com.shoppingcartdemo.demo.entity.Product;
import com.shoppingcartdemo.demo.util.MoneyRoundUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ShoppingCartTest {
    private ShoppingCart shoppingCart;
    private Product doveSoap;
    private Product axeDeos;

    @BeforeEach
    void init() {

        String taxRateString = "12.5%";
        BigDecimal taxRate = new BigDecimal(Double.parseDouble(taxRateString.replace("%", "")) * 0.01);
        shoppingCart = new ShoppingCart(taxRate);
        doveSoap = new Product.ProductBuilder()
                .withProductName("Dove Soap")
                .withUnitPrice("39.99").build();
        axeDeos = new Product.ProductBuilder()
                .withProductName("Axe Deos")
                .withUnitPrice("99.99").build();
    }

    @Test
    void add() {
        shoppingCart.add(doveSoap);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        Assert.assertEquals("39.99", shoppingCart.getTotalPriceRounded().toString());
        Assert.assertEquals(1,shoppingCart.getItemCount());
    }

    @Test
    void testAdd() {
        shoppingCart.add(doveSoap, 5);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        Assert.assertEquals("5", shoppingCart.getProductIntegerMap().get(doveSoap).toString());
        Assert.assertEquals("39.99", MoneyRoundUtil.round(doveSoap.getUnitPrice()).toString());
        Assert.assertEquals("199.95", shoppingCart.getTotalPriceRounded().toString());
        Assert.assertEquals(5,shoppingCart.getItemCount());
    }

    @Test
    void testConsecutiveAdding() {
        shoppingCart.add(doveSoap, 5);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        shoppingCart.add(doveSoap, 3);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        Assert.assertEquals("8", shoppingCart.getProductIntegerMap().get(doveSoap).toString());
        Assert.assertEquals("39.99", MoneyRoundUtil.round(doveSoap.getUnitPrice()).toString());
        Assert.assertEquals("319.92", shoppingCart.getTotalPriceRounded().toString());
        Assert.assertEquals(8,shoppingCart.getItemCount());

    }

    @Test
    void remove() {
        shoppingCart.add(doveSoap);
        shoppingCart.remove(doveSoap);
        Assert.assertNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        Assert.assertEquals("0.00", shoppingCart.getTotalPriceRounded().toString());
        Assert.assertEquals(0,shoppingCart.getItemCount());
    }

    @Test
    void testRemoveAllExistingProduct() {
        shoppingCart.add(doveSoap, 5);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        shoppingCart.remove(doveSoap, 5);
        Assert.assertEquals("0.00", shoppingCart.getTotalPriceRounded().toString());
        Assert.assertEquals(0,shoppingCart.getItemCount());
    }

    @Test
    void testTwoProductsWithTax() {
        shoppingCart.add(doveSoap, 2);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        shoppingCart.add(axeDeos, 2);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(axeDeos));

        String totalSaleTaxExpected = "35.00";
        String totalPriceExpect = "314.96";

        Assert.assertEquals(totalSaleTaxExpected, shoppingCart.getTaxRounded().toString());
        Assert.assertEquals(totalPriceExpect, shoppingCart.getTotalPriceWithTax().toString());
        Assert.assertEquals(4,shoppingCart.getItemCount());

    }

    @Test
    void testWhenOverRemoveProductThrownIllegalParameterException() {
//        shoppingCart.add(doveSoap, 5);
//        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
//            shoppingCart.remove(doveSoap, 8);
//        });
//        String expectedMessage = "Removing Product Error";
//        String actualMessage = exception.getMessage();
//
//        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testRemovePartOfExistingProduct() {
        shoppingCart.add(doveSoap, 5);
        Assert.assertNotNull(shoppingCart.getProductIntegerMap().get(doveSoap));
        shoppingCart.remove(doveSoap, 4);
        Assert.assertEquals("39.99", shoppingCart.getTotalPriceRounded().toString());
    }

    @Test
    void print() {
    }
}