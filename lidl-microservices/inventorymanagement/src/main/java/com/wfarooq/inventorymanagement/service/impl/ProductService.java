package com.wfarooq.inventorymanagement.service.impl;

import com.wfarooq.inventorymanagement.dto.request.ProductRequest;
import com.wfarooq.inventorymanagement.dto.response.ProductResponse;
import com.wfarooq.inventorymanagement.entity.Product;
import com.wfarooq.inventorymanagement.exception.AlreadyExistsException;
import com.wfarooq.inventorymanagement.exception.ResourceNotFoundException;
import com.wfarooq.inventorymanagement.mapper.ProductMapper;
import com.wfarooq.inventorymanagement.repository.ProductRepository;
import com.wfarooq.inventorymanagement.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    /**
     * @param request for new product
     * @return boolean based on creation
     */
    @Override
    public void createProduct(ProductRequest request) {
        if (productRepository.existsByBarcode(request.getBarcode())) throw new AlreadyExistsException("Product", "barcode", request.getBarcode());
        if (productRepository.existsBySku(request.getSku())) throw new AlreadyExistsException("Product", "barcode", request.getSku());
        Product newProduct = ProductMapper.mapProductRequestToProduct(request, new Product());
        productRepository.save(newProduct);
    }

    /**
     * @param department that is requesting products
     * @return list of products that belong to that department
     */
    @Override
    public List<ProductResponse> fetchProductByDepartment(String department) {
        List<Product> products = productRepository.findByDepartment(department);
        return products.stream().map(product -> ProductMapper.mapProductToProductResponse(product, new ProductResponse())).toList();
    }

    /**
     * @return list of all products
     */
    @Override
    public List<ProductResponse> fetchAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductMapper.mapProductToProductResponse(product, new ProductResponse())).toList();
    }

    /**
     * @param category that products belong to
     * @return list of products that belong to that category
     */
    @Override
    public List<ProductResponse> fetchProductsByCategory(String category) {

        List<Product> products = productRepository.findByCategory(category);
        return products.stream().map(product -> ProductMapper.mapProductToProductResponse(product, new ProductResponse())).toList();
    }

    /**
     * @param sku of the product
     * @return product response
     */
    @Override
    public ProductResponse fetchProductBySku(String sku) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new ResourceNotFoundException("Product", "sku", sku));
        return ProductMapper.mapProductToProductResponse(product, new ProductResponse());
    }

    /**
     * @param sku of the product being deleted
     * @return true or false based on deletion
     */
    @Override
    public boolean deleteProductBySku(String sku) {
        Product productToDelete = productRepository.findBySku(sku).orElseThrow(() -> new ResourceNotFoundException("Product", "sku", sku));
        if (productRepository.existsBySku(sku)) {
            productRepository.delete(productToDelete);
            return true;
        }else {
            return false;
        }
    }

    /**
     * @param request updated product
     * @param sku     of the product being updated
     * @return true or false based on update
     */
    @Override
    public boolean updateProductBySku(ProductRequest request, String sku) {
        Product product = productRepository.findBySku(sku).orElseThrow(() -> new ResourceNotFoundException("Product", "sku", sku));

        if (productRepository.existsBySku(sku)) {
            ProductMapper.mapProductRequestToProduct(request, product);
            productRepository.save(product);
            return true;
        }

        return false;
    }
}
