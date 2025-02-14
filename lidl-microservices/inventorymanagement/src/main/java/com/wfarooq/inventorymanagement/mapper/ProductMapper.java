package com.wfarooq.inventorymanagement.mapper;

import com.wfarooq.inventorymanagement.dto.request.ProductRequest;
import com.wfarooq.inventorymanagement.dto.response.ProductResponse;
import com.wfarooq.inventorymanagement.entity.Product;

public final class ProductMapper {

    private ProductMapper() {}

    public static Product mapProductRequestToProduct(ProductRequest productRequest, Product product) {
        product.setSku(productRequest.getSku());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setDepartment(productRequest.getDepartment());
        product.setCategory(productRequest.getCategory());
        product.setSubcategory(productRequest.getSubcategory());
        product.setBasePrice(productRequest.getBasePrice());
        product.setUnit(productRequest.getUnit());
        product.setBarcode(productRequest.getBarcode());
        product.setCasesPerPallet(productRequest.getCasesPerPallet());
        product.setWeightPerCase(productRequest.getWeightPerCase());
        product.setActive(productRequest.isActive());
        return product;
    }

    public static ProductResponse mapProductToProductResponse(Product product, ProductResponse response) {

        response.setSku(product.getSku());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setDepartment(product.getDepartment());
        response.setCategory(product.getCategory());
        response.setSubcategory(product.getSubcategory());
        response.setBasePrice(product.getBasePrice());
        response.setUnit(product.getUnit());
        response.setBarcode(product.getBarcode());
        response.setCasesPerPallet(product.getCasesPerPallet());
        response.setWeightPerCase(product.getWeightPerCase());
        response.setActive(product.isActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setCreatedBy(product.getCreatedBy());
        response.setUpdatedAt(product.getUpdatedAt());
        response.setUpdatedBy(product.getUpdatedBy());
        return response;
    }
}
