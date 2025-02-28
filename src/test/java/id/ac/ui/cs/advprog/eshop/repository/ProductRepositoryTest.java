package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepositoryImpl productRepository;

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
    void testCreateAndGet() {
        productRepository.create(product1);

        Iterator<Product> productIterator = productRepository.getAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateIfNullId(){
        product1.setProductId(null);
        productRepository.create(product1);

        Iterator<Product> productIterator = productRepository.getAll();
        Product savedProduct = productIterator.next();
        assertNotNull(savedProduct.getProductId());
    }

    @Test
    void testGetAllIfEmpty() {
            Iterator<Product> productIterator = productRepository.getAll();
            assertFalse(productIterator.hasNext());
    }

    @Test
    void testGetAllIfMoreThanOneProduct() {
        productRepository.create(product1);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.getAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testGetById(){
        productRepository.create(product1);
        productRepository.create(product2);
        assertEquals(product2, productRepository.getById(product2.getProductId()));
        assertEquals(product1, productRepository.getById(product1.getProductId()));
    }

    @Test
    void testGetByIdIfNotFound() {
        assertThrows(ProductNotFoundException.class, () -> productRepository.getById(product1.getProductId()));
    }

    @Test
    void testUpdate(){
        productRepository.create(product1);

        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productRepository.update(product1);

        assertEquals(product1, productRepository.getById(product1.getProductId()));
    }

    @Test
    void testUpdateIfNotFound(){
        productRepository.create(product1);
        assertThrows(ProductNotFoundException.class, () -> productRepository.update(product2));
    }

    @Test
    void testDelete(){
        productRepository.create(product1);
        assertEquals(product1, productRepository.getById(product1.getProductId()));

        productRepository.delete(product1);
        assertThrows(ProductNotFoundException.class, () -> productRepository.getById(product1.getProductId()));
    }

    @Test
    void testDeleteIfNotFound(){
        assertThrows(ProductNotFoundException.class, () -> productRepository.delete(product1));
    }
}