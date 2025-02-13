package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {

    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product update(Product product){
        int index = indexByProduct(product);
        productData.set(index, product);
        return product;
    }

    public int indexByProduct(Product product){
        for(Product currentProduct : productData){
            if(currentProduct.getProductId().equals(product.getProductId())){
                return productData.indexOf(currentProduct);
            }
        }
        throw new ProductNotFoundException("Product with ID " + product.getProductId() + " not found.");
    }

    public Product findById(int id){
        for(Product currentProduct : productData){
            if(currentProduct.getProductId().equals(Integer.toString(id))){
                return currentProduct;
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found.");
    }

    public Product delete(Product product){
        int index = indexByProduct(product);
        productData.remove(index);
        return product;
    }
}
