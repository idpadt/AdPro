package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product update(Product product){
        String id = product.getProductId();
        int index = findIndexById(id);
        productData.set(index, product);
        return product;
    }

    public Product delete(Product product){
        String id = product.getProductId();
        int index = findIndexById(id);
        productData.remove(index);
        return product;
    }

    public Product getById(String id){
        int index = findIndexById(id);
        return productData.get(index);
    }

    public Iterator<Product> getAll() {
        return productData.iterator();
    }

    private int findIndexById(String id){
        for(int ii = 0; ii < productData.size(); ii++){
            if(productData.get(ii).getProductId().equals(id)){
                return ii;
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found");
    }
}
