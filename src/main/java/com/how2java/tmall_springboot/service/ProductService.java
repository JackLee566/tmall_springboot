package com.how2java.tmall_springboot.service;

import com.how2java.tmall_springboot.dao.ProductDAO;
import com.how2java.tmall_springboot.pojo.Category;
import com.how2java.tmall_springboot.pojo.Product;
import com.how2java.tmall_springboot.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService  {

    @Autowired
    ProductDAO productDAO;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    public void add(Product bean) {
        productDAO.save(bean);
    }

    public void delete(int id) {
        productDAO.delete(id);
    }

    public Product get(int id) {
        return productDAO.findOne(id);
    }

    public void update(Product bean) {
        productDAO.save(bean);
    }

    public Page4Navigator<Product> list(int cid, int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Product> pageFromJPA =productDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public void fill(Category category){
        List<Product> ps = listByCategory(category);
        productImageService.setFirstProductImage(ps);
        category.setProducts(ps);
    }
    public void fill(List<Category> cs){
        for(Category c:cs)
            fill(c);
    }

    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;
        for (Category category : categorys) {
            List<Product> products =  category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size=Math.min(size,products.size());
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    private List<Product> listByCategory(Category category) {
        return productDAO.findByCategoryOrderById(category);
    }

    public void setSaleAndReviewNumber(Product product){
        int saleCount = orderItemService.getSaleCount(product);
        int reviewCount = reviewService.getCount(product);
        product.setReviewCount(reviewCount);
        product.setSaleCount(saleCount);
    }

    public void setSaleAndReviewNumber(List<Product> products){
        for(Product p: products)
            setSaleAndReviewNumber(p);
    }
}
