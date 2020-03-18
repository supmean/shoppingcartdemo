package com.shoppingcartdemo.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A converter that takes a double number and converts it to BigDecimal Round-Half-Up (Arithmetic Rounding)
 *
 * @author
 */
public class MoneyRoundUtil {
    public static BigDecimal round(double moneyNumber) {
        return new BigDecimal(moneyNumber).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal round(BigDecimal moneyNumberDecimal) {
        return moneyNumberDecimal.setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal round(String moneyNumberString) {
        BigDecimal moneyNumberDecimal = new BigDecimal(moneyNumberString);
        return round(moneyNumberDecimal);
    }
}
