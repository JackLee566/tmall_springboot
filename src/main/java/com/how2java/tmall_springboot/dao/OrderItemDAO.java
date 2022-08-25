package com.how2java.tmall_springboot.dao;

import com.how2java.tmall_springboot.pojo.Order;
import com.how2java.tmall_springboot.pojo.OrderItem;
import com.how2java.tmall_springboot.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
    public List<OrderItem> findByOrOrderOrderByIdDesc(Order order);

    //通过产品获得对应订单项列表
    public List<OrderItem> findByProduct(Product product);
}
