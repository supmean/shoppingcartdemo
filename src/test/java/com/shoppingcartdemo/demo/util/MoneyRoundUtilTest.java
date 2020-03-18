package com.shoppingcartdemo.demo.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MoneyRoundUtilTest {

    @Test
    void round() {

        Assert.assertEquals("138.99", MoneyRoundUtil.round("138.99").toString());
        Assert.assertEquals("0.57", MoneyRoundUtil.round("0.565").toString());
        Assert.assertEquals("0.76", MoneyRoundUtil.round("0.7649").toString());
    }
}