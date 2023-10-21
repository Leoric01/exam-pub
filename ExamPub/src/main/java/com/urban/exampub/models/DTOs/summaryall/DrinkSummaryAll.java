package com.urban.exampub.models.DTOs.summaryall;

import com.urban.exampub.models.DTOs.OrderDto;

import java.util.List;

public class DrinkSummaryAll {
    private String product;
    private int amount;
    private double unitPrice;
    private double summaryPrice;
    private List<OrderDto> orders;

    public DrinkSummaryAll() {
    }

    public DrinkSummaryAll(String product, int amount, double unitPrice, double summaryPrice) {
        this.product = product;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.summaryPrice = summaryPrice;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }
}

