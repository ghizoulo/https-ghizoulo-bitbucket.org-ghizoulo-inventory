package inventory.dao;


import java.util.List;

import inventory.model.Product;

public interface ProductDao {

    public List<Product> list() throws Exception;
    public void add(Product p) throws Exception;
    public Product getProductById(int id) throws Exception;
    public void update(Product product) throws Exception;
    public void delete(int id) throws Exception;
    public List<Product> find(String key) throws Exception;
}