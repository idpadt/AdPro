package id.ac.ui.cs.advprog.eshop.controller;


import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ProductService productService;

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
    void testCreateProductPage() throws Exception {
        mvc.perform(get("/product/create"))
                .andExpectAll(status().isOk(),
                        view().name("createProduct"),
                        model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        when(productService.create(product1)).thenReturn(product1);
        mvc.perform(post("/product/create").flashAttr("product", product1))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(productService).create(product1);
    }

    @Test
    void testCreateProductPostIfNotValid() throws Exception {
        mvc.perform(post("/product/create")
                .param("productName", "A")
                .param("productQuantity", ""))
                .andExpectAll(status().isOk(),
                        view().name("createProduct"));
    }

    @Test
    void testCreateProductPostIfEmptyForm() throws Exception {
        mvc.perform(post("/product/create"))
                .andExpectAll(status().isOk(),
                        view().name("createProduct"));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.findAll()).thenReturn(products);

        mvc.perform(get("/product/list"))
                .andExpectAll(status().isOk(),
                        view().name("productList"),
                        model().attributeExists("products"),
                        model().attribute("products", hasSize(2)),
                        model().attribute("products", hasItem(product1)),
                        model().attribute("products", hasItem(product2)));

        verify(productService).findAll();
    }

    @Test
    void testProductListPageIfEmpty() throws Exception {
        List<Product> products = new ArrayList<>();
        when(productService.findAll()).thenReturn(products);

        mvc.perform(get("/product/list"))
                .andExpectAll(status().isOk(),
                        view().name("productList"),
                        model().attribute("products", hasSize(0)));

        verify(productService).findAll();
    }

    @Test
    void testUpdateProductPage() throws Exception {
        when(productService.findProductById(product1.getProductId())).thenReturn(product1);
        mvc.perform(get("/product/update/" + product1.getProductId()))
                .andExpectAll(status().isOk(),
                        view().name("updateProduct"),
                        model().attributeExists("product"));

        verify(productService).findProductById(product1.getProductId());
    }

    @Test
    void testUpdateProductPageNotFound() throws Exception {
        when(productService.findProductById(product1.getProductId())).thenThrow(ProductNotFoundException.class);
        mvc.perform(get("/product/update/" + product1.getProductId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(productService).findProductById(product1.getProductId());
    }

    @Test
    void testUpdateProductPost() throws Exception {
        when(productService.update(product1)).thenReturn(product1);
        mvc.perform(post("/product/update").flashAttr("product", product1))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(productService).update(product1);
    }

    @Test
    void testUpdateProductPostIfNotFound() throws Exception {
        when(productService.update(product1)).thenThrow(ProductNotFoundException.class);
        mvc.perform(post("/product/update").flashAttr("product", product1))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(productService).update(product1);
    }

    @Test
    void testUpdateProductPostIfNotValid() throws Exception {
        mvc.perform(post("/product/update"))
                .andExpectAll(status().isOk(),
                        view().name("updateProduct"));
    }

    @Test
    void testDeleteProductPost() throws Exception {
        when(productService.findProductById(product1.getProductId())).thenReturn(product1);
        when(productService.delete(product1)).thenReturn(product1);

        mvc.perform(get("/product/delete/" + product1.getProductId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("/product/list"));

        verify(productService).delete(product1);
        verify(productService).findProductById(product1.getProductId());
    }

    @Test
    void testDeleteProductPostIfIdNotFound() throws Exception {
        when(productService.findProductById(product1.getProductId())).thenThrow(ProductNotFoundException.class);

        mvc.perform(get("/product/delete/" + product1.getProductId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));

        verify(productService).findProductById(product1.getProductId());
    }

    @Test
    void testDeleteProductPostIfNotValid() throws Exception {
        when(productService.findProductById(product1.getProductId())).thenReturn(product1);
        when(productService.delete(product1)).thenThrow(ProductNotFoundException.class);

        mvc.perform(get("/product/delete/" + product1.getProductId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));

        verify(productService).findProductById(product1.getProductId());
        verify(productService).delete(product1);
    }
}
