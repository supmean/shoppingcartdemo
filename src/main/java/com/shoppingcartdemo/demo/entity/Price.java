package com.shoppingcartdemo.demo.entity;

import com.shoppingcartdemo.demo.util.MoneyRoundUtil;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Price {


//    public Price(BigDecimal beforeTax ) {
//        this.beforeTax = beforeTax;
//
//    }
//
//    public Price(double beforeTaxDouble) {
//        this.beforeTax = MoneyRoundUtil.round(beforeTaxDouble);
//    }

    /**
     * Calculate Tax and Total including tax
     *
     * @param taxRateDouble
     * @return tax
     */
    public static BigDecimal calculateTax(BigDecimal beforeTax, double taxRateDouble) {
        BigDecimal taxRate = new BigDecimal(taxRateDouble);
        return calculateTax(beforeTax, taxRate);
    }

    public static BigDecimal calculateTax(BigDecimal beforeTax, String taxRatePercent) {
        Double taxRateDouble = Double.parseDouble(taxRatePercent.replace("%", "")) * 0.01;
        return calculateTax(beforeTax, taxRateDouble);
    }

    public static BigDecimal calculateTax(double beforeTaxDouble, String taxRatePercent) {
        BigDecimal beforeTax = MoneyRoundUtil.round(beforeTaxDouble);
        return calculateTax(beforeTax, taxRatePercent);
    }

    public static BigDecimal calculateTax(BigDecimal beforeTax, BigDecimal taxRate) {
        BigDecimal tax = MoneyRoundUtil.round(beforeTax.multiply(taxRate));
        return tax;

    }

    public static BigDecimal calculateTotalWithTax(BigDecimal before, BigDecimal taxRate) {
        BigDecimal tax = calculateTax(before, taxRate);
        return MoneyRoundUtil.round(before.add(tax));
    }

    public static BigDecimal calculateTotalWithTax(BigDecimal before, String taxRatePercent) {
        Double taxRateDouble = Double.parseDouble(taxRatePercent.replace("%", "")) * 0.01;
        return calculateTotalWithTax(before, taxRateDouble);
    }

    public static BigDecimal calculateTotalWithTax(BigDecimal before, double taxRateDouble) {
        BigDecimal taxBigDecimal = new BigDecimal(taxRateDouble);
        return calculateTotalWithTax(before, taxBigDecimal);
    }

}
