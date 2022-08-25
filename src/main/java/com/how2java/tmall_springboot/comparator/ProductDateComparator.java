package com.how2java.tmall_springboot.comparator;

import com.how2java.tmall_springboot.pojo.Product;

import java.util.Comparator;

//时间降序排列
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCreateDate().compareTo(o2.getCreateDate());
    }
}
