package com.how2java.tmall_springboot.comparator;

import com.how2java.tmall_springboot.pojo.Product;

import java.util.Comparator;

//按照评论数 降序排列
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getReviewCount()-o1.getReviewCount();
    }
}
