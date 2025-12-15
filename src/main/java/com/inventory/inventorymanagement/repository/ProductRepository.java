package com.inventory.inventorymanagement.repository;

import com.inventory.inventorymanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.quantity < p.minStock")
    List<Product> findLowStockProducts();

    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryIgnoreCase(String category);

    @Query("SELECT SUM(p.quantity) FROM Product p")
    Integer totalStock();


}
