package com.wfarooq.inventorymanagement.service;

import com.wfarooq.inventorymanagement.dto.request.ProductRequest;
import com.wfarooq.inventorymanagement.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {

    /**
     *
     * @param request for new product
     */
    void createProduct (ProductRequest request);

    /**
     *
     * @param department that is requesting products
     * @return list of products that belong to that department
     */
    List<ProductResponse> fetchProductByDepartment(String department);

    /**
     *
     * @return list of all products
     */
    List<ProductResponse> fetchAllProducts();

    /**
     *
     * @param category that products belong to
     * @return list of products that belong to that category
     */
    List<ProductResponse> fetchProductsByCategory(String category);

    /**
     *
     * @param sku of the product
     * @return product response
     */
    ProductResponse fetchProductBySku(String sku);

    /**
     *
     * @param sku of the product being deleted
     * @return true or false based on deletion
     */
    boolean deleteProductBySku(String sku);


    /**
     *
     * @param request updated product
     * @param sku of the product being updated
     * @return true or false based on update
     */
    boolean updateProductBySku(ProductRequest request, String sku);


}
