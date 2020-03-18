package com.shoppingcartdemo.demo.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author
 */
@Data
public class Product {

    private String productName;
    private BigDecimal unitPrice;

    public static final class ProductBuilder {
        private String productName;
        private BigDecimal unitPrice;

        public ProductBuilder() {
        }

        public static ProductBuilder aProduct() {
            return new ProductBuilder();
        }

        public ProductBuilder withProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public ProductBuilder withUnitPrice(String unitPriceString) {

            this.unitPrice = new BigDecimal(unitPriceString);
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setProductName(productName);
            product.setUnitPrice(unitPrice);
            return product;
        }
    }
}
