package com.how2java.tmall_springboot.dao;

import com.how2java.tmall_springboot.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {
}
