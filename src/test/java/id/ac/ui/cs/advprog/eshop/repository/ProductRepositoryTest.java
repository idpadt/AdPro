package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

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
    void testCreateAndFind() {
        productRepository.create(product1);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product1.getProductName(), savedProduct.getProductName());
        assertEquals(product1.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
            Iterator<Product> productIterator = productRepository.findAll();
            assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        productRepository.create(product1);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindIndexByProduct(){
        productRepository.create(product1);
        assertEquals(0, productRepository.findIndexByProduct(product1));

        productRepository.create(product2);
        assertEquals(1, productRepository.findIndexByProduct(product2));
        assertEquals(0, productRepository.findIndexByProduct(product1));
    }

    @Test
    void testFindIndexByMissingProductId(){
        assertThrows(ProductNotFoundException.class, () -> productRepository.findIndexByProduct(product1));
    }

    @Test
    void testFindById(){
        productRepository.create(product1);
        productRepository.create(product2);
        assertEquals(product2, productRepository.findProductById(product2.getProductId()));
        assertEquals(product1, productRepository.findProductById(product1.getProductId()));
    }

    @Test
    void testUpdate(){
        productRepository.create(product1);

        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productRepository.update(product1);

        assertEquals(product1, productRepository.findProductById(product1.getProductId()));
    }

    @Test
    void testUpdateMissingProduct(){
        productRepository.create(product1);
        assertThrows(ProductNotFoundException.class, () -> productRepository.update(product2));
    }

    @Test
    void testDelete(){
        productRepository.create(product1);
        assertEquals(product1, productRepository.findProductById(product1.getProductId()));

        productRepository.delete(product1);
        assertThrows(ProductNotFoundException.class, () -> productRepository.findProductById(product1.getProductId()));
    }

    @Test
    void testDeleteMissingProduct(){
        assertThrows(ProductNotFoundException.class, () -> productRepository.delete(product1));
    }
}