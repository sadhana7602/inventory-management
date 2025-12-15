package com.inventory.inventorymanagement.service;

import com.inventory.inventorymanagement.dto.ProductRequest;
import com.inventory.inventorymanagement.entity.Product;
import com.inventory.inventorymanagement.exception.ResourceNotFoundException;
import com.inventory.inventorymanagement.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setMinStock(request.getMinStock());
        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public Page<Product> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repository.findAll(pageable);
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id " + id));
    }

    @Override
    public Product update(Long id, ProductRequest request) {
        Product product = getById(id);
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.delete(getById(id));
    }

    @Override
    public List<Product> getLowStockProducts() {
        return repository.findLowStockProducts();
    }

    @Override
    public List<Product> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> filterByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    @Override
    public Product stockIn(Long id, int quantity) {
        Product product = getById(id);
        product.setQuantity(product.getQuantity() + quantity);
        return repository.save(product);
    }

    @Override
    public Product stockOut(Long id, int quantity) {
        Product product = getById(id);

        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        return repository.save(product);
    }

    @Override
    public Product deactivate(Long id) {
        Product product = getById(id);
        product.setActive(false);
        return repository.save(product);
    }

    @Override
    public Integer getTotalStock() {
        return repository.totalStock();
    }

}
