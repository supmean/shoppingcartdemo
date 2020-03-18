package com.shoppingcartdemo.demo.service;

import com.shoppingcartdemo.demo.entity.Product;

public interface ICart {
    void add(Product product);

    void remove(Product product);
}
