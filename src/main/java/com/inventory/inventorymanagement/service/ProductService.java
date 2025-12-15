package com.inventory.inventorymanagement.service;

import com.inventory.inventorymanagement.dto.ProductRequest;
import com.inventory.inventorymanagement.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product create(ProductRequest request);

    Page<Product> getAll(int page, int size, String sortBy);

    Product getById(Long id);

    Product update(Long id, ProductRequest request);

    void delete(Long id);


    List<Product> getLowStockProducts();

    List<Product> searchByName(String name);
    List<Product> filterByCategory(String category);

    Product stockIn(Long id, int quantity);
    Product stockOut(Long id, int quantity);

    public Product deactivate(Long id);
    public Integer getTotalStock();


}
