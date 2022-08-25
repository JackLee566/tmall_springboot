package com.how2java.tmall_springboot.comparator;

import com.how2java.tmall_springboot.pojo.Product;

import java.util.Comparator;

//销量降序排列
public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getSaleCount()-o1.getSaleCount();
    }
}
