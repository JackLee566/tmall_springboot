package com.how2java.tmall_springboot.dao;


import java.util.List;

import com.how2java.tmall_springboot.pojo.Product;
import com.how2java.tmall_springboot.pojo.Property;
import com.how2java.tmall_springboot.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer>{
    List<PropertyValue> findByProductOrderByIdDesc(Product product);
    PropertyValue getByPropertyAndProduct(Property property,Product product);
}