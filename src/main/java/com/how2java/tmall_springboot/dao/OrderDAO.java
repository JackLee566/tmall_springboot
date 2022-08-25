package com.how2java.tmall_springboot.dao;

import com.how2java.tmall_springboot.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer> {
}
