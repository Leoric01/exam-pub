package com.urban.exampub.models.DTOs.summaryproduct;

import java.util.List;

public class ProductAllOrdersDto {
    private String product;
    private List<ProductSpecificSummary> specifics;

    public ProductAllOrdersDto() {
    }

    public ProductAllOrdersDto(String product, List<ProductSpecificSummary> specifics) {
        this.product = product;
        this.specifics = specifics;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public List<ProductSpecificSummary> getSpecifics() {
        return specifics;
    }

    public void setSpecifics(List<ProductSpecificSummary> specifics) {
        this.specifics = specifics;
    }
}
