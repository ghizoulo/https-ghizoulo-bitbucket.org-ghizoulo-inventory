package inventory.service.product;

import inventory.model.Product;
import inventory.dao.ProductDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("simpleProductManager")
public class SimpleProductManager implements ProductManager {

	private static final long serialVersionUID = 1L;
	@Autowired
    private ProductDao productDao;
  
    @Override
    public List<Product> listProducts() throws Exception{
        return productDao.list() ;
    }

    @Override
    public void addProduct(Product p) throws Exception{
        productDao.add(p);
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(int id) throws Exception{
        productDao.delete(id);
    }
    
    @Override
    public List<Product> findProducts(String key) throws Exception{
        return productDao.find(key);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

	public ProductDao getProductDao() {
		return productDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Product getProductById(int id) throws Exception {
		return productDao.getProductById(id);
	}
    
}