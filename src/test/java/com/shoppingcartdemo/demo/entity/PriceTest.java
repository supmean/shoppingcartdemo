package com.shoppingcartdemo.demo.entity;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PriceTest {

    @Test
    void testCalculateTax() {
        String taxRateString = "12.5%";
        BigDecimal tax = Price.calculateTax(279.96, taxRateString);
        Assert.assertEquals("35.00", tax.toString());
    }

    @Test
    void testCalculateTotalWithTax() {
        String taxRateString = "12.5%";
        double taxRateDouble = 0.125;
        BigDecimal beforeTax = new BigDecimal("279.96");
        BigDecimal taxRateBigDecimal = new BigDecimal(0.125);

        Assert.assertEquals("314.96", Price.calculateTotalWithTax(beforeTax, taxRateString).toString());
        Assert.assertEquals("314.96", Price.calculateTotalWithTax(beforeTax, taxRateDouble).toString());
        Assert.assertEquals("314.96", Price.calculateTotalWithTax(beforeTax, taxRateBigDecimal).toString());
    }
}