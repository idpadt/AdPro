package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepositoryImpl productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        this.product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        this.product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product1)).thenReturn(product1);
        assertEquals(product1, productService.create(product1));
        verify(productRepository).create(product1);
    }

    @Test
    void testFindAll(){
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(product1);
        mockProducts.add(product2);
        when(productRepository.findAll()).thenReturn(mockProducts.iterator());

        List<Product> productList = productService.findAll();
        assertTrue(productList.contains(product1));
        assertTrue(productList.contains(product2));
        verify(productRepository).findAll();
    }

    @Test
    void testFindAllIfEmpty(){
        List<Product> mockProducts = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(mockProducts.iterator());

        List<Product> productList = productService.findAll();
        assertTrue(productList.isEmpty());
        verify(productRepository).findAll();
    }

    @Test
    void testFindProductById() {
        when(productRepository.findProductById(product1.getProductId())).thenReturn(product1);
        when(productRepository.findProductById(product2.getProductId())).thenReturn(product2);

        assertEquals(product2, productService.findProductById(product2.getProductId()));
        assertEquals(product1, productService.findProductById(product1.getProductId()));

        verify(productRepository).findProductById(product1.getProductId());
        verify(productRepository).findProductById(product2.getProductId());
    }

    @Test
    void testFindProductByIdNotFound() {
        when(productRepository.findProductById(product1.getProductId())).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(product1.getProductId()));
        verify(productRepository).findProductById(product1.getProductId());
    }

    @Test
    void testUpdate(){
        when(productRepository.update(product1)).thenReturn(product1);
        assertEquals(product1, productService.update(product1));
        verify(productRepository).update(product1);
    }

    @Test
    void testUpdateNotFound(){
        when(productRepository.update(product1)).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> productService.update(product1));
        verify(productRepository).update(product1);
    }

    @Test
    void testDelete(){
        when(productRepository.delete(product1)).thenReturn(product1);
        assertEquals(product1, productService.delete(product1));
        verify(productRepository).delete(product1);
    }

    @Test
    void testDeleteNotFound(){
        when(productRepository.delete(product1)).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> productService.delete(product1));
        verify(productRepository).delete(product1);
    }
}
