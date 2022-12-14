package com.how2java.tmall_springboot.web;

import com.how2java.tmall_springboot.pojo.Order;
import com.how2java.tmall_springboot.service.OrderItemService;
import com.how2java.tmall_springboot.service.OrderService;
import com.how2java.tmall_springboot.util.Page4Navigator;
import com.how2java.tmall_springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @GetMapping("/orders")
    public Page4Navigator<Order> list(@RequestParam(value = "start", defaultValue = "0")int start,
                                      @RequestParam(value = "size",defaultValue = "5")int size){
        start=start<0?0:start;
        Page4Navigator<Order> page = orderService.list(start,size,5);
        orderItemService.fill(page.getContent());
//        orderService.removeOrderFromOrderItem(page.getContent());
        return page;
    }

    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid){
        Order order = orderService.get(oid);
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return Result.success();
    }
}

