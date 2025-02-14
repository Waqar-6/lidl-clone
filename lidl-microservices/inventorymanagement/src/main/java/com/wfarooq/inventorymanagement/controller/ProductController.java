package com.wfarooq.inventorymanagement.controller;

import com.wfarooq.inventorymanagement.constants.ProductConstants;
import com.wfarooq.inventorymanagement.constants.StatusConstants;
import com.wfarooq.inventorymanagement.dto.ResponseDto;
import com.wfarooq.inventorymanagement.dto.request.ProductRequest;
import com.wfarooq.inventorymanagement.dto.response.ProductResponse;
import com.wfarooq.inventorymanagement.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final IProductService productService;

    @PostMapping
    public ResponseEntity<ResponseDto> createProduct (@RequestBody ProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(ProductConstants.MESSAGE_201, StatusConstants.STATUS_201));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> fetchAllProducts () {
        List<ProductResponse> products = productService.fetchAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/department")
    public ResponseEntity<List<ProductResponse>> fetchProductsByDepartment (@RequestParam String department) {
        List<ProductResponse> products = productService.fetchProductByDepartment(department);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }


    @GetMapping("/category")
    public ResponseEntity<List<ProductResponse>> fetchProductsByCategory (@RequestParam String category) {
        List<ProductResponse> products = productService.fetchProductsByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponse> fetchProductBySku (@PathVariable String sku) {
        ProductResponse productResponse = productService.fetchProductBySku(sku);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @PutMapping("/update/sku")
    public ResponseEntity<ResponseDto> updateProductBySku (@RequestBody ProductRequest request, @RequestParam String sku) {
        boolean isUpdated = productService.updateProductBySku(request, sku);
        return isUpdated ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.MESSAGE_200_UPDATE, StatusConstants.STATUS_200)) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(ProductConstants.MESSAGE_417_UPDATE, StatusConstants.STATUS_417));

    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<ResponseDto> deleteProductBySku (@PathVariable String sku) {
        boolean isDeleted = productService.deleteProductBySku(sku);
        return isDeleted ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(ProductConstants.MESSAGE_200_DELETE, StatusConstants.STATUS_200))
                :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(ProductConstants.MESSAGE_417_DELETE, StatusConstants.STATUS_417));
    }





}
