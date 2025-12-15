package com.inventory.inventorymanagement.controller;

import com.inventory.inventorymanagement.dto.ProductRequest;
import com.inventory.inventorymanagement.dto.StockUpdateRequest;
import com.inventory.inventorymanagement.entity.Product;
import com.inventory.inventorymanagement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Product create(@Valid @RequestBody ProductRequest request) {
        return service.create(request);
    }

    @GetMapping
    public Page<Product> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return service.getAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Product update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/low-stock")
    public List<Product> lowStockProducts() {
        return service.getLowStockProducts();
    }

    @PutMapping("/{id}/stock-in")
    public Product stockIn(
            @PathVariable Long id,
            @RequestBody StockUpdateRequest request) {
        return service.stockIn(id, request.getQuantity());
    }

    @PutMapping("/{id}/stock-out")
    public Product stockOut(
            @PathVariable Long id,
            @RequestBody StockUpdateRequest request) {
        return service.stockOut(id, request.getQuantity());
    }

    @PutMapping("/{id}/deactivate")
    public Product deactivate(@PathVariable Long id) {
        return service.deactivate(id);
    }

    @GetMapping("/summary/total-stock")
    public Integer totalStock() {
        return service.getTotalStock();
    }


}
