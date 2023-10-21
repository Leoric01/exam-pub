package com.urban.exampub.models.DTOs;

public class BuyRequestDto {
    private Long userId;
    private Long productId;
    private Double price;

    public BuyRequestDto() {
    }

    public BuyRequestDto(Long userId, Long productId, Double price) {
        this.userId = userId;
        this.productId = productId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
