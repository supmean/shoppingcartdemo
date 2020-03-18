package com.shoppingcartdemo.demo.service;

import com.shoppingcartdemo.demo.entity.Price;
import com.shoppingcartdemo.demo.entity.Product;
import com.shoppingcartdemo.demo.util.MoneyRoundUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author
 */
@Data
public class ShoppingCart implements ICart {
    public static final String DEFAULT_TAX_RATE = "12.5%";
    private Map<Product, Integer> productIntegerMap;
    private int itemCount;
    private BigDecimal totalPrice;
    private BigDecimal totalPriceRounded;
    private BigDecimal taxRounded;
    private BigDecimal taxRate;
    private BigDecimal totalPriceWithTax;
    private int capacity;


    public ShoppingCart() {
        init(new BigDecimal(Double.parseDouble(DEFAULT_TAX_RATE.replace("%", "")) * 0.01));
    }

    public ShoppingCart(BigDecimal taxRate) {
        init(taxRate);

    }

    /**
     * Initialization for constructor, setting up tax rate
     *
     * @param taxRate
     */
    public void init(BigDecimal taxRate) {
        itemCount = 0;
        productIntegerMap = new HashMap<Product, Integer>();
        totalPrice = BigDecimal.valueOf(0);
        totalPriceRounded = BigDecimal.valueOf(0);
        taxRounded = BigDecimal.valueOf(0);
        this.taxRate = taxRate;
        totalPriceWithTax = BigDecimal.valueOf(0);
    }

    @Override
    public void add(Product product) {
        add(product, 1);

    }

    /**
     * Add n of products to shopping cart
     *
     * @param product
     * @param n
     */
    public void add(Product product, int n) {
        if (!productIntegerMap.containsKey(product)) {
            productIntegerMap.put(product, n);
        } else {
            int before = productIntegerMap.get(product).intValue();
            int after = before + n;
            productIntegerMap.put(product, after);
        }

        BigDecimal addedAmount = product.getUnitPrice().multiply(BigDecimal.valueOf(n));
        totalPrice = addedAmount.add(totalPrice);
        totalPriceRounded = MoneyRoundUtil.round(totalPrice);
        itemCount += n;

        updateTax(totalPriceRounded);

    }

    /**
     * Update tax-related properties
     *
     * @param beforeTax
     */
    private void updateTax(BigDecimal beforeTax) {
        if (beforeTax != null) {
            this.taxRounded = Price.calculateTax(beforeTax, taxRate);
            this.totalPriceWithTax = beforeTax.add(this.taxRounded);
        } else {
            throw new IllegalArgumentException("Before Tax is not set");
        }

    }

    /**
     * Add a single Product
     *
     * @param product
     */
    @Override
    public void remove(Product product) {
        remove(product, 1);
    }

    /**
     * Add a number of Product at the same time
     *
     * @param product
     * @param n
     */
    public void remove(Product product, int n) {
        int before = productIntegerMap.get(product).intValue();

        if (before == n) {
            productIntegerMap.remove(product);
        } else if (before > n) {
            int after = before - n;
            productIntegerMap.put(product, after);
        } else {
            throw new IllegalArgumentException("Removing Product Error");
        }

        totalPrice = totalPrice.subtract(product.getUnitPrice().multiply(BigDecimal.valueOf(n)));
        itemCount -= n;
        totalPriceRounded = MoneyRoundUtil.round(totalPrice.doubleValue());

        updateTax(totalPriceRounded);

    }


    /**
     * Print out the Shopping Cart content
     */
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("The shopping list: \n");

        Set<Product> productKey = productIntegerMap.keySet();
        Iterator<Product> productIterator = productKey.iterator();
        while (productIterator.hasNext()) {
            Product product = productIterator.next();
            Integer unit = productIntegerMap.get(product);
            sb.append(String.format("%d of %s: $%s \n"
                    , unit
                    , product.getProductName()
                    , product.getUnitPrice().multiply(new BigDecimal(unit))));
        }
        sb.append(String.format("\t\t\t Total Price: $%s\n", totalPriceRounded));
        System.out.println(sb);
    }

}
