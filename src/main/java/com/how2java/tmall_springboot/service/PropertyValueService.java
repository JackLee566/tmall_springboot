package com.how2java.tmall_springboot.service;

import java.util.List;

import com.how2java.tmall_springboot.dao.PropertyValueDAO;
import com.how2java.tmall_springboot.pojo.Product;
import com.how2java.tmall_springboot.pojo.Property;
import com.how2java.tmall_springboot.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PropertyValueService  {

    @Autowired PropertyValueDAO propertyValueDAO;
    @Autowired PropertyService propertyService;

    public void update(PropertyValue bean) {
        propertyValueDAO.save(bean);
    }

    public void init(Product product) {
        List<Property> propertys= propertyService.listByCategory(product.getCategory());
        for (Property property: propertys) {
            PropertyValue propertyValue = getByPropertyAndProduct(product, property);
            if(null==propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setProduct(product);
                propertyValue.setProperty(property);
                propertyValueDAO.save(propertyValue);
            }
        }
    }

    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
        return propertyValueDAO.getByPropertyAndProduct(property,product);
    }

    public List<PropertyValue> list(Product product) {
        return propertyValueDAO.findByProductOrderByIdDesc(product);
    }
}