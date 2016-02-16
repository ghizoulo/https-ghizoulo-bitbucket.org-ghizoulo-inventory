package inventory.service.product;

import inventory.model.Product;
import java.io.Serializable;
import java.util.List;

public interface ProductManager extends Serializable {
    
    public List<Product> listProducts() throws Exception;
    public void addProduct(Product p) throws Exception;
    public void updateProduct(Product product) throws Exception;
    public void deleteProduct(int id) throws Exception;
    public List<Product> findProducts(String key) throws Exception;
    public Product getProductById(int id)throws Exception;
}