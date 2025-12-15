package com.inventory.inventorymanagement.dto;

import jakarta.validation.constraints.Min;

public class StockUpdateRequest {

    @Min(1)
    private int quantity;

    // getter & setter
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
