package com.how2java.tmall_springboot.comparator;

import com.how2java.tmall_springboot.pojo.Product;

import java.util.Comparator;

//价格升序排列
public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPromotePrice()-o2.getPromotePrice());
    }
}
