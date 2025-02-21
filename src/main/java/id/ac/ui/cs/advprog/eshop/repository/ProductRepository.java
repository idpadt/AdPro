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
        int index = findIndexByProduct(product);
        productData.set(index, product);
        return product;
    }

    public int findIndexById(String id){
        for(int ii = 0; ii < productData.size(); ii++){
            if(productData.get(ii).getProductId().equals(id)){
                return ii;
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found");
    }

    public int findIndexByProduct(Product product){
        return findIndexById(product.getProductId());
    }

    public Product findProductById(String id){
        int index = findIndexById(id);
        return productData.get(index);
    }

    public Product delete(Product product){
        int index = findIndexByProduct(product);
        productData.remove(index);
        return product;
    }
}
