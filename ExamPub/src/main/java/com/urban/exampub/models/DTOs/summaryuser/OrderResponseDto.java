package com.urban.exampub.models.DTOs.summaryuser;

public class OrderResponseDto {
  private String product;
  private double price;

  public OrderResponseDto() {}

  public OrderResponseDto(String product, double price) {
    this.product = product;
    this.price = price;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
