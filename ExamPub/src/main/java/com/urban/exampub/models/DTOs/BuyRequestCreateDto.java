package com.urban.exampub.models.DTOs;

public class BuyRequestCreateDto {
    private Long userId;
    private Long productId;
    private int amount;
    private Double price;

    public BuyRequestCreateDto() {
    }

    public BuyRequestCreateDto(Long userId, Long productId, Double price) {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
    }

    public BuyRequestCreateDto(Long userId, Long productId, int amount, Double price) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
