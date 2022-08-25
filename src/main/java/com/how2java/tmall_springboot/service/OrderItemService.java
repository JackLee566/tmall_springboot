package com.how2java.tmall_springboot.service;

import com.how2java.tmall_springboot.dao.OrderDAO;
import com.how2java.tmall_springboot.dao.OrderItemDAO;
import com.how2java.tmall_springboot.dao.ProductImageDAO;
import com.how2java.tmall_springboot.pojo.Order;
import com.how2java.tmall_springboot.pojo.OrderItem;
import com.how2java.tmall_springboot.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    OrderItemDAO orderItemDAO;
    @Autowired
    ProductImageService productImageService;

    private List<OrderItem> listByOrder(Order order){
        return orderItemDAO.findByOrOrderOrderByIdDesc(order);
    }

    public void fill(Order order){
        List<OrderItem> orderItems = listByOrder(order);
        float total = 0;
        int totalNumber = 0;
        for(OrderItem orderItem: orderItems){
            total += orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
            totalNumber += orderItem.getNumber();
            productImageService.setFirstProductImage(orderItem.getProduct());
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItems);
    }

    public void fill(List<Order> orders){
        for(Order order: orders){
            fill(order);
        }
    }

    //增加
    public void add(OrderItem orderItem){
        orderItemDAO.save(orderItem);
    }

    //删除
    public void delete(int id){
        orderItemDAO.delete(id);
    }

    //修改
    public void update(OrderItem orderItem){
        orderItemDAO.save(orderItem);
    }
    //获取单个
    public OrderItem get(int id){
        return orderItemDAO.getOne(id);
    }

    //获取订单项数量
    public int getSaleCount(Product product){
        List<OrderItem> orderItems = orderItemDAO.findByProduct(product);
        int saleCount = 0;
        for(OrderItem orderItem : orderItems){
            saleCount+=orderItem.getNumber();
        }
        return saleCount;
    }

}
