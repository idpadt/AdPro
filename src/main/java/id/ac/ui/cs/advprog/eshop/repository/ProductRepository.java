package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface ProductRepository {
    public Product create(Product product);
    public Product update(Product product);
    public Product delete(Product product);
    public Product getById(String id);
    public Iterator<Product> getAll();
}
