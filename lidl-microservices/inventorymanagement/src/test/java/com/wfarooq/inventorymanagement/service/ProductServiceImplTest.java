package com.wfarooq.inventorymanagement.service;

import com.wfarooq.inventorymanagement.dto.request.ProductRequest;
import com.wfarooq.inventorymanagement.dto.response.ProductResponse;
import com.wfarooq.inventorymanagement.entity.Product;
import com.wfarooq.inventorymanagement.exception.AlreadyExistsException;
import com.wfarooq.inventorymanagement.exception.ResourceNotFoundException;
import com.wfarooq.inventorymanagement.repository.ProductRepository;
import com.wfarooq.inventorymanagement.service.impl.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @DisplayName(value = "create product test")
    @Test
    void testCreateProduct_whenGivenProductRequest_shouldSaveProduct() {

        ProductRequest productRequest = new ProductRequest(
                "TEST-SKU-001",
                "Bananas Yellow Test",
                "Test description for bananas",
                "FRUIT&VEG",
                "FRUIT",
                "BANANAS",
                new BigDecimal("1.99"),
                "CASE",
                "5029123456789",
                30,
                18.5,
                true
        );


        when(productRepository.existsByBarcode(productRequest.getBarcode())).thenReturn(false);
        when(productRepository.existsBySku(productRequest.getSku())).thenReturn(false);

        productService.createProduct(productRequest);

        verify(productRepository, times(1)).save(any(Product.class));


    }

    @DisplayName(value = "create product with existing sku")
    @Test
    void testCreateProduct_whenGivenExistingSku_shouldThrowAlreadyExistsException() {
        ProductRequest productRequest = new ProductRequest(
                "TEST-SKU-001",
                "Bananas Yellow Test",
                "Test description for bananas",
                "FRUIT&VEG",
                "FRUIT",
                "BANANAS",
                new BigDecimal("1.99"),
                "CASE",
                "5029123456789",
                30,
                18.5,
                true
        );

        when(productRepository.existsBySku(productRequest.getSku())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> productService.createProduct(productRequest));
    }

    @DisplayName(value = "create product with existing barcode")
    @Test
    void testCreateProduct_whenGivenExistingBarcode_shouldThrowAlreadyExistsException() {
        ProductRequest productRequest = new ProductRequest(
                "TEST-SKU-001",
                "Bananas Yellow Test",
                "Test description for bananas",
                "FRUIT&VEG",
                "FRUIT",
                "BANANAS",
                new BigDecimal("1.99"),
                "CASE",
                "5029123456789",
                30,
                18.5,
                true
        );

        when(productRepository.existsByBarcode(productRequest.getBarcode())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> productService.createProduct(productRequest));
    }

    @Test
    void testFetchAllProducts_ShouldReturnListOfProductResponses() {
        List<Product> products = Arrays.asList(
                createTestProduct("TEST-SKU-001", "Bananas", "5029123456789"),
                createTestProduct("TEST-SKU-002", "Apples", "5029123456790")
        );

        when(productRepository.findAll()).thenReturn(products);
        List<ProductResponse> productResponses = productService.fetchAllProducts();

        verify(productRepository, times(1)).findAll();
        assertNotNull(productResponses);
        assertEquals(2, productResponses.size());
        assertEquals("TEST-SKU-001", productResponses.get(0).getSku());
        assertEquals("Bananas", productResponses.get(0).getName());
        assertEquals("TEST-SKU-002", productResponses.get(1).getSku());
        assertEquals("Apples", productResponses.get(1).getName());
    }

    @DisplayName(value = "Fetch product by SKU should return ProductResponse")
    @Test
    void testFetchProductBySku_whenGivenProductSku_shouldReturnProductResponse() {
        Product product = createTestProduct("TEST-SKU-001", "Bananas", "5029123456789");

        when(productRepository.findBySku("TEST-SKU-001")).thenReturn(Optional.of(product));

        ProductResponse productResponse = productService.fetchProductBySku("TEST-SKU-001");

        verify(productRepository, times(1)).findBySku("TEST-SKU-001");
        assertNotNull(productResponse);
        assertEquals("TEST-SKU-001", productResponse.getSku());
        assertEquals("Bananas", productResponse.getName());
    }

    @DisplayName(value = "Fetch product by SKU should throw ResourceNotFoundException")
    @Test
    void testFetchProductBySku_whenProductNotFound_shouldThrowResourceNotFoundException() {
        when(productRepository.findBySku("TEST-SKU-001")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.fetchProductBySku("TEST-SKU-001"));
    }

   

    private Product createTestProduct(String sku, String name, String barcode) {
        Product product = new Product();
        product.setId(UUID.randomUUID());  // Add UUID
        product.setSku(sku);
        product.setName(name);
        product.setBarcode(barcode);
        product.setDepartment("FRUIT&VEG");
        product.setCategory("FRUIT");
        product.setBasePrice(new BigDecimal("1.99"));
        product.setUnit("CASE");
        product.setCasesPerPallet(30);
        product.setWeightPerCase(18.5);
        product.setActive(true);
        return product;
    }

}
