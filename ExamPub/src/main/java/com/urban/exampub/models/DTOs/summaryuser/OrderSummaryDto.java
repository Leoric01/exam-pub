package com.urban.exampub.models.DTOs.summaryuser;

import java.util.List;

public class OrderSummaryDto {

  private Long userId;

  private List<OrderResponseDto> orderResponseDtos;

  public OrderSummaryDto() {}

  public OrderSummaryDto(Long userId, List<OrderResponseDto> orderResponseDtos) {
    this.userId = userId;
    this.orderResponseDtos = orderResponseDtos;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<OrderResponseDto> getOrderResponseDtos() {
    return orderResponseDtos;
  }

  public void setOrderResponseDtos(List<OrderResponseDto> orderResponseDtos) {
    this.orderResponseDtos = orderResponseDtos;
  }
}
